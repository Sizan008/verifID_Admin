package com.leads.microcube.verifidadmin.customerprofile.query;

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
public class EddAnswerResponse {

  @JsonProperty("TrackingNo") private Long trackingNo;
  @JsonProperty("CRG_EDD_ID") private Integer crgEddId;
  @JsonProperty("Question") private String question;
  @JsonProperty("QuestionAnswer") private String questionAnswer;
}
