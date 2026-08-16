package com.leads.microcube.verifidadmin.common.security;

public interface CurrentUserProvider {

  CurrentUser getCurrentUser();

  void setCurrentUser(CurrentUser currentUser);

  void clear();
}
