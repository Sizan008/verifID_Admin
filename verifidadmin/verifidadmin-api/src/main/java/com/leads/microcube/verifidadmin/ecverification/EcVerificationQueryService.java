package com.leads.microcube.verifidadmin.ecverification;

import com.leads.microcube.verifidadmin.ecverification.query.EcAddressOptionResponse;
import java.util.List;

public interface EcVerificationQueryService {

    List<EcAddressOptionResponse> retrieveDivisions();

    List<EcAddressOptionResponse> retrieveDistricts(Integer divisionId);

    List<EcAddressOptionResponse> retrieveUpazilas(Integer districtId);

    List<EcAddressOptionResponse> retrievePostOffices(
            Integer districtId,
            Integer upazilaId);
}