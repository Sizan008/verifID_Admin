package com.leads.microcube.verifidadmin.account.client;

import com.leads.microcube.verifidadmin.account.client.dto.SecurityApiResponse;
import com.leads.microcube.verifidadmin.account.command.LoginAccount;
import com.leads.microcube.verifidadmin.account.command.LoginContext;

public interface SecurityApiGateway {

  SecurityApiResponse login(LoginAccount command, LoginContext context);

  SecurityApiResponse logout(String userId);
}
