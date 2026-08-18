package com.leads.microcube.verifidadmin.report;

import com.leads.microcube.verifidadmin.report.query.MergedCustomerPhoto;
import com.leads.microcube.verifidadmin.report.query.MergedCustomerPhotoResponse;

public interface ReportQueryService {

  MergedCustomerPhotoResponse retrieveMergedCustomerPhoto(MergedCustomerPhoto query);
}
