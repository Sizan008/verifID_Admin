package com.leads.microcube.verifidadmin.customerprofile.client;

import com.leads.microcube.verifidadmin.customerprofile.client.dto.RemoteDocumentResponse;
import com.leads.microcube.verifidadmin.customerprofile.client.dto.RemoteStatusResponse;

public interface CustomerProfileReportGateway {

  RemoteStatusResponse retrieveReport(Long trackingNo);

  RemoteDocumentResponse retrieveDocuments(Long trackingNo);
}
