package com.leads.microcube.verifidadmin.customerprofile;

import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActionResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActivity;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActivityPageResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerAuthTypeResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerBeneficiary;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerBeneficiaryResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDashboard;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDashboardMetrics;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDetails;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDetailsResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDocument;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDocumentResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerEddDetails;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerExport;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerExportResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerFilter;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerGuardian;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerGuardianResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerNominee;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerNomineeResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPageResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPayment;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPaymentResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPhotos;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPhotosResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerProduct;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerProductResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerReport;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerReportResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerRiskScore;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerRiskScoreResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.DebitRestriction;
import com.leads.microcube.verifidadmin.customerprofile.query.EddAnswerResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.EddQuestionResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.EddQuestions;
import com.leads.microcube.verifidadmin.customerprofile.query.RiskGradingDetails;
import com.leads.microcube.verifidadmin.customerprofile.query.RiskGradingFormResponse;
import java.util.List;

public interface CustomerProfileQueryService {

  CustomerPageResponse retrieveCustomers(CustomerFilter filter);

  CustomerDashboardMetrics retrieveDashboardMetrics(CustomerDashboard query);

  CustomerDetailsResponse retrieveCustomer(CustomerDetails query);

  CustomerNomineeResponse retrieveNominee(CustomerNominee query);

  CustomerGuardianResponse retrieveGuardian(CustomerGuardian query);

  CustomerBeneficiaryResponse retrieveBeneficiary(CustomerBeneficiary query);

  CustomerDocumentResponse retrieveDocuments(CustomerDocument query);

  CustomerProductResponse retrieveProduct(CustomerProduct query);

  CustomerPaymentResponse retrievePayment(CustomerPayment query);

  CustomerPhotosResponse retrievePhotos(CustomerPhotos query);

  RiskGradingFormResponse retrieveRiskGrading(RiskGradingDetails query);

  CustomerRiskScoreResponse retrieveRiskScore(CustomerRiskScore query);

  List<EddQuestionResponse> retrieveEddQuestions(EddQuestions query);

  List<EddAnswerResponse> retrieveEddDetails(CustomerEddDetails query);

  CustomerActionResponse retrieveDebitRestriction(DebitRestriction query);

  CustomerAuthTypeResponse retrieveAuthType();

  CustomerActivityPageResponse retrieveActivity(CustomerActivity query);

  CustomerExportResponse retrieveExcel(CustomerExport query);

  CustomerExportResponse retrieveExcelDetails(CustomerExport query);

  CustomerReportResponse retrieveReport(CustomerReport query);
}
