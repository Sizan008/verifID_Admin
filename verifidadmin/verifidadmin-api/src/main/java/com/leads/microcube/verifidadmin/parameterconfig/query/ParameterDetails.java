package com.leads.microcube.verifidadmin.parameterconfig.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Identifies a parameter configuration to retrieve. */
@Getter
@RequiredArgsConstructor
public class ParameterDetails {

  private final String paramName;
}
