package com.leads.microcube.verifidadmin.log;

import com.leads.microcube.verifidadmin.log.query.UserActivityLogExportResponse;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogFilter;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogPageResponse;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogResponse;
import java.util.List;

public interface UserActivityLogQueryService {


  UserActivityLogPageResponse retrieveUserActivities(UserActivityLogFilter filter);

  UserActivityLogExportResponse retrieveUserActivityExcel(UserActivityLogFilter filter);
}
