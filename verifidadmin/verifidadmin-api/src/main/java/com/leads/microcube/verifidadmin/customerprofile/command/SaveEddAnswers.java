package com.leads.microcube.verifidadmin.customerprofile.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
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
public class SaveEddAnswers {

  @NotNull
  @JsonProperty("TrackingNo")
  private Long trackingNo;

  @Valid
  @NotEmpty
  @Builder.Default
  @JsonProperty("Answers")
  private List<EddAnswer> answers = new ArrayList<>();
}
