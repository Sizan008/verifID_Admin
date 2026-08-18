package com.leads.microcube.verifidadmin.ecverification.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyEcNid {

  @NotBlank
  @JsonProperty("NidOrVoterNoOrFormNoOrVoterId")
  private String nidOrVoterNoOrFormNoOrVoterId;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("NameEn")
  private String nameEn;

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonProperty("DateOfBirth")
  private LocalDate dateOfBirth;

  @JsonProperty("Father")
  private String father;

  @JsonProperty("Mother")
  private String mother;

  @JsonProperty("Spouse")
  private String spouse;

  @Valid
  @NotNull
  @JsonProperty("PermanentAddress")
  private EcVerificationAddress permanentAddress;
}
