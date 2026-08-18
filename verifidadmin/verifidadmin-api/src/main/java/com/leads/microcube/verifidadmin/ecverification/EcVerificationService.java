package com.leads.microcube.verifidadmin.ecverification;

import com.leads.microcube.verifidadmin.ecverification.command.VerifyEcNid;
import com.leads.microcube.verifidadmin.ecverification.query.EcVerificationResponse;

public interface EcVerificationService {

  EcVerificationResponse process(VerifyEcNid command);
}
