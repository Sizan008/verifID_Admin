package com.leads.microcube.verifidadmin.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents the common response contract of VerifID Admin APIs. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

  @JsonProperty("Status")
  private String status = "FAILED";

  @JsonProperty("Message")
  private String message;

  @JsonProperty("Result")
  private T result;

  public static <T> ApiResponse<T> success(String message, T result) {
    return new ApiResponse<>("OK", message, result);
  }

  public static <T> ApiResponse<T> success(T result) {
    return new ApiResponse<>("OK", null, result);
  }

  public static <T> ApiResponse<T> failure(String message) {
    return new ApiResponse<>("FAILED", message, null);
  }
}
