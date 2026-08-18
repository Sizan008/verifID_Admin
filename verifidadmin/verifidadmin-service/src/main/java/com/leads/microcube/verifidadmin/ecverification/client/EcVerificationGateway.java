package com.leads.microcube.verifidadmin.ecverification.client;

import com.leads.microcube.verifidadmin.ecverification.client.dto.EcVerificationApiResponse;
import com.leads.microcube.verifidadmin.ecverification.command.VerifyEcNid;

public interface EcVerificationGateway {

  EcVerificationApiResponse verify(VerifyEcNid command);
}
