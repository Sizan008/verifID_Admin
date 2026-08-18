package com.leads.microcube.verifidadmin.account.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginContext {

  private String clientIp;
  private String serverIp;
  private String sessionId;
}
