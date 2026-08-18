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
public class EddQuestionResponse {

  @JsonProperty("TrackingNo") private Long trackingNo;
  @JsonProperty("CRG_EDD_ID") private Integer crgEddId;
  @JsonProperty("Question") private String question;
  @JsonProperty("QuestionType") private String questionType;
  @JsonProperty("LabelEN") private String labelEn;
  @JsonProperty("RequiredFlag") private Integer requiredFlag;
  @JsonProperty("PlaceholderText") private String placeholderText;
  @JsonProperty("MinLength") private Integer minLength;
  @JsonProperty("MaxLength") private Integer maxLength;
}
