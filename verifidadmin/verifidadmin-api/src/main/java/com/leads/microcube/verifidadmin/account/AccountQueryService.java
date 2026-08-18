package com.leads.microcube.verifidadmin.account;

import com.leads.microcube.verifidadmin.account.query.AccountLoginConfigurationResponse;
import com.leads.microcube.verifidadmin.account.query.AccountUser;
import com.leads.microcube.verifidadmin.account.query.AccountUserResponse;
import com.leads.microcube.verifidadmin.account.query.CurrentAccountUserResponse;
import java.util.List;

public interface AccountQueryService {

  AccountLoginConfigurationResponse retrieveLoginConfiguration();

  CurrentAccountUserResponse retrieveCurrentUser();

  List<AccountUserResponse> retrieveUsers();

  AccountUserResponse retrieveUser(AccountUser query);

  List<AccountUserResponse> retrieveUnauthorizedUsers();
}
