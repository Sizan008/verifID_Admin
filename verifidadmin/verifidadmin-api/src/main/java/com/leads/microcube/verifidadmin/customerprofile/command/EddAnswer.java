package com.leads.microcube.verifidadmin.customerprofile.command;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class EddAnswer {

  @JsonProperty("CRG_EDD_ID")
  private Integer crgEddId;

  @JsonProperty("Question")
  private String question;

  @JsonProperty("QuestionAnswer")
  private String questionAnswer;
}
