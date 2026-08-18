package com.leads.microcube.verifidadmin.assistedekyc;

import com.leads.microcube.verifidadmin.assistedekyc.exception.AssistedEkycConfigurationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AssistedEkycSettings {

  private final String url;

  public AssistedEkycSettings(
      @Value("${verifidadmin.assisted-ekyc.url:}") String url) {
    this.url = url;
  }

  public String getUrl() {
    if (!StringUtils.hasText(url)) {
      throw new AssistedEkycConfigurationException(
          "Assisted EKYC URL is not configured.");
    }
    return url.trim();
  }
}
