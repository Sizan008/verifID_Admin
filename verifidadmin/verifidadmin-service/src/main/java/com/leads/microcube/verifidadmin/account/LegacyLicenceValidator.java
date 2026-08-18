package com.leads.microcube.verifidadmin.account;

import com.leads.microcube.verifidadmin.account.exception.AccountValidationException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.text.Normalizer;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class LegacyLicenceValidator {

  private static final String FIXED_KEY = "@wf8y6t_*4zkjd78";
  private static final byte[] INITIALIZATION_VECTOR =
      new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
  private static final List<DateTimeFormatter> DATE_FORMATS =
      List.of(
          DateTimeFormatter.ISO_LOCAL_DATE,
          formatter("uuuu-M-d"),
          formatter("uuuu/M/d"),
          formatter("uuuu.M.d"),
          formatter("M/d/uuuu"),
          formatter("M-d-uuuu"),
          formatter("M.d.uuuu"),
          formatter("M d uuuu"),
          formatter("d/M/uuuu"),
          formatter("d-M-uuuu"),
          formatter("d.M.uuuu"),
          formatter("d M uuuu"),
          formatter("M/d/uu"),
          formatter("M-d-uu"),
          formatter("d/M/uu"),
          formatter("d-M-uu"),
          formatter("d-MMM-uuuu"),
          formatter("d MMM uuuu"),
          formatter("d MMMM uuuu"),
          formatter("MMM d uuuu"),
          formatter("MMMM d uuuu"),
          formatter("d-MMM-uu"),
          formatter("d MMM uu"),
          formatter("MMM d uu"));
  private static final List<Pattern> DATE_PATTERNS =
      List.of(
          Pattern.compile("(?<!\\d)\\d{4}[-/.]\\d{1,2}[-/.]\\d{1,2}(?!\\d)"),
          Pattern.compile("(?<!\\d)\\d{1,2}[-/.]\\d{1,2}[-/.]\\d{2,4}(?!\\d)"),
          Pattern.compile(
              "(?i)\\b\\d{1,2}(?:st|nd|rd|th)?[\\s,./-]+[a-z]{3,9}"
                  + "[\\s,./-]+\\d{2,4}\\b"),
          Pattern.compile(
              "(?i)\\b[a-z]{3,9}[\\s,./-]+\\d{1,2}(?:st|nd|rd|th)?"
                  + "[\\s,./-]+\\d{2,4}\\b"));
  private static final Pattern UNICODE_WHITESPACE = Pattern.compile("[\\p{Z}\\s]+");
  private static final Pattern ORDINAL_SUFFIX =
      Pattern.compile("(?i)(?<=\\d)(?:st|nd|rd|th)\\b");
  private static final Pattern TEXT_DATE_SEPARATOR = Pattern.compile("[./-]+");

  private final AccountSettings accountSettings;

  public LegacyLicenceValidator(AccountSettings accountSettings) {
    this.accountSettings = accountSettings;
  }

  public void validate() {
    if (!accountSettings.isLicenceCheckEnabled()) {
      return;
    }
    String encryptedDate = accountSettings.retrieveRequiredSetting("LICENCE_DATE");
    LocalDate licenceDate = parseDate(decrypt(encryptedDate));
    if (LocalDate.now().isAfter(licenceDate)) {
      throw new AccountValidationException("Licence Expired !!!");
    }
  }

  private String decrypt(String encryptedText) {
    if (encryptedText.length() <= 44) {
      throw new AccountValidationException("LICENCE_DATE is invalid.");
    }
    try {
      byte[] fixedKey = FIXED_KEY.getBytes(StandardCharsets.UTF_16LE);
      String randomKey = decryptAes(encryptedText.substring(0, 44), fixedKey);
      byte[] decryptedRandomKey = randomKey.getBytes(StandardCharsets.UTF_16LE);
      return decryptAes(encryptedText.substring(44), decryptedRandomKey);
    } catch (GeneralSecurityException | IllegalArgumentException exception) {
      throw new AccountValidationException("LICENCE_DATE is invalid.", exception);
    }
  }

  private String decryptAes(String encryptedText, byte[] key)
      throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(
        Cipher.DECRYPT_MODE,
        new SecretKeySpec(key, "AES"),
        new IvParameterSpec(INITIALIZATION_VECTOR));
    byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
    return new String(plainText, StandardCharsets.UTF_8);
  }

  private LocalDate parseDate(String value) {
    String normalizedValue = normalize(value);
    List<String> candidates = new ArrayList<>();
    candidates.add(normalizedValue);
    for (Pattern pattern : DATE_PATTERNS) {
      Matcher matcher = pattern.matcher(normalizedValue);
      while (matcher.find()) {
        candidates.add(normalizeCandidate(matcher.group()));
      }
    }

    for (String candidate : candidates) {
      LocalDate parsedDate = tryParse(candidate);
      if (parsedDate != null) {
        return parsedDate;
      }
    }
    throw new AccountValidationException("LICENCE_DATE is invalid.");
  }

  private LocalDate tryParse(String value) {
    for (DateTimeFormatter formatter : DATE_FORMATS) {
      try {
        return LocalDate.parse(value, formatter);
      } catch (DateTimeException ignored) {
      }
    }
    return null;
  }

  private String normalize(String value) {
    if (value == null) {
      return "";
    }
    String normalizedValue =
        Normalizer.normalize(value, Normalizer.Form.NFKC)
            .replace("\u0000", "")
            .replace("\uFEFF", "")
            .strip();
    if (normalizedValue.length() >= 2
        && ((normalizedValue.startsWith("\"") && normalizedValue.endsWith("\""))
            || (normalizedValue.startsWith("'") && normalizedValue.endsWith("'")))) {
      normalizedValue = normalizedValue.substring(1, normalizedValue.length() - 1);
    }
    return UNICODE_WHITESPACE.matcher(normalizedValue).replaceAll(" ").strip();
  }

  private String normalizeCandidate(String value) {
    String candidate = ORDINAL_SUFFIX.matcher(value).replaceAll("");
    candidate = candidate.replace(',', ' ');
    if (candidate.codePoints().anyMatch(Character::isLetter)) {
      candidate = TEXT_DATE_SEPARATOR.matcher(candidate).replaceAll(" ");
    }
    return UNICODE_WHITESPACE.matcher(candidate).replaceAll(" ").strip();
  }

  private static DateTimeFormatter formatter(String pattern) {
    return new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(pattern)
            .toFormatter(Locale.ENGLISH)
            .withResolverStyle(ResolverStyle.STRICT);
  }
}
