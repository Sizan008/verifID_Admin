package com.leads.microcube.verifidadmin.parameterconfig;

import com.leads.microcube.verifidadmin.parameterconfig.command.CreateParameter;
import com.leads.microcube.verifidadmin.parameterconfig.command.UpdateParameter;

/** Defines parameter configuration write operations. */
public interface ParameterConfigService {

  void process(CreateParameter command);

  void process(UpdateParameter command);
}
