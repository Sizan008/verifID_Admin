package com.leads.microcube.verifidadmin.home;

import com.leads.microcube.verifidadmin.home.query.DashboardResponse;
import com.leads.microcube.verifidadmin.home.query.SubBranchResponse;

public interface HomeQueryService {

  DashboardResponse retrieveDashboard();

  SubBranchResponse retrieveSubBranchStatus();

  String retrieveHeadOfficeBranchId();
}
