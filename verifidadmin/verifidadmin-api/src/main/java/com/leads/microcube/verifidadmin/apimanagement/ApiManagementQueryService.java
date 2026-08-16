package com.leads.microcube.verifidadmin.apimanagement;

import com.leads.microcube.verifidadmin.apimanagement.query.ApiConnectionDetails;
import com.leads.microcube.verifidadmin.apimanagement.query.ApiConnectionResponse;
import java.util.List;

public interface ApiManagementQueryService {

  List<ApiConnectionResponse> retrieveApiConnections();

  ApiConnectionResponse retrieveApiConnection(ApiConnectionDetails query);
}
