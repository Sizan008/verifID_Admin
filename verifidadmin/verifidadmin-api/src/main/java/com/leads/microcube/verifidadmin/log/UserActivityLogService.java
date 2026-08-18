package com.leads.microcube.verifidadmin.log;

import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import com.leads.microcube.verifidadmin.log.command.RecordUserActivity;

public interface UserActivityLogService {

  boolean process(RecordUserActivity command);

  boolean process(RecordCurrentUserActivity command);
}
