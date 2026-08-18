package com.leads.microcube.verifidadmin.customerprofile.client;

import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActionResponse;

public interface CustomerProfileGateway {

  CustomerActionResponse enableCashTransaction(Long trackingNo);

  CustomerActionResponse openAccount(Long trackingNo);

  CustomerActionResponse withdrawDebitRestriction(Long trackingNo);

  CustomerActionResponse requireDebitRestrictionWithdrawal(Long trackingNo);

  CustomerActionResponse checkDebitRestriction(Long trackingNo);

  CustomerActionResponse checkDebitRestrictionDirect(Long trackingNo);
}
