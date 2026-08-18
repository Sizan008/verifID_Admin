package com.leads.microcube.verifidadmin.customerprofile;

import com.leads.microcube.verifidadmin.customerprofile.command.CheckCustomerByBranchAdmin;
import com.leads.microcube.verifidadmin.customerprofile.command.DeclineCustomer;
import com.leads.microcube.verifidadmin.customerprofile.command.EnableCashTransaction;
import com.leads.microcube.verifidadmin.customerprofile.command.OpenCustomerAccount;
import com.leads.microcube.verifidadmin.customerprofile.command.RecordLoanBoAcceptReason;
import com.leads.microcube.verifidadmin.customerprofile.command.ReturnCustomer;
import com.leads.microcube.verifidadmin.customerprofile.command.SaveEddAnswers;
import com.leads.microcube.verifidadmin.customerprofile.command.SaveRiskGrading;
import com.leads.microcube.verifidadmin.customerprofile.command.UpdateCustomerServices;
import com.leads.microcube.verifidadmin.customerprofile.command.WithdrawDebitRestriction;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActionResponse;

public interface CustomerProfileService {

  void process(DeclineCustomer command);

  void process(ReturnCustomer command);

  void process(RecordLoanBoAcceptReason command);

  CustomerActionResponse process(CheckCustomerByBranchAdmin command);

  CustomerActionResponse process(OpenCustomerAccount command);

  CustomerActionResponse process(WithdrawDebitRestriction command);

  CustomerActionResponse process(EnableCashTransaction command);

  CustomerActionResponse process(UpdateCustomerServices command);

  void process(SaveRiskGrading command);

  void process(SaveEddAnswers command);
}
