package com.leads.microcube.verifidadmin.account;

import com.leads.microcube.verifidadmin.account.command.AuthorizeAccountUser;
import com.leads.microcube.verifidadmin.account.command.CreateAccountUser;
import com.leads.microcube.verifidadmin.account.command.DeclineAccountUser;
import com.leads.microcube.verifidadmin.account.command.DeleteAccountUser;
import com.leads.microcube.verifidadmin.account.command.LoginAccount;
import com.leads.microcube.verifidadmin.account.command.LoginContext;
import com.leads.microcube.verifidadmin.account.command.UpdateAccountUser;
import com.leads.microcube.verifidadmin.account.query.AccountLoginResponse;

public interface AccountService {

  AccountLoginResponse process(LoginAccount command, LoginContext context);

  void process();

  void process(CreateAccountUser command);

  void process(UpdateAccountUser command);

  void process(DeleteAccountUser command);

  void process(AuthorizeAccountUser command);

  void process(DeclineAccountUser command);
}
