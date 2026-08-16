package com.leads.microcube.verifidadmin.apimanagement;

import com.leads.microcube.verifidadmin.apimanagement.command.CreateApiConnection;
import com.leads.microcube.verifidadmin.apimanagement.command.UpdateApiConnection;

public interface ApiManagementService {

  void process(CreateApiConnection command);

  void process(UpdateApiConnection command);
}
