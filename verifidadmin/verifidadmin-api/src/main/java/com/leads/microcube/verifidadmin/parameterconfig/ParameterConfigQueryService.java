package com.leads.microcube.verifidadmin.parameterconfig;

import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterDetails;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterResponse;
import java.util.List;

/** Defines parameter configuration read operations. */
public interface ParameterConfigQueryService {

  List<ParameterResponse> retrieveParameters();

  ParameterResponse retrieveParameter(ParameterDetails query);
}
