package com.leads.microcube.verifidadmin.ecverification;

import com.leads.microcube.verifidadmin.ecverification.exception.EcVerificationException;
import com.leads.microcube.verifidadmin.ecverification.query.EcAddressOptionResponse;
import com.leads.microcube.verifidadmin.ecverification.repository.EcAddressLookupRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EcVerificationQueryServiceImpl
        implements EcVerificationQueryService {

    private final EcAddressLookupRepository ecAddressLookupRepository;

    @Override
    public List<EcAddressOptionResponse> retrieveDivisions() {
        try {
            return ecAddressLookupRepository.retrieveDivisions();
        } catch (Exception exception) {
            log.error("Unable to retrieve EC divisions.", exception);
            throw new EcVerificationException(
                    "Unable to retrieve divisions.",
                    exception);
        }
    }

    @Override
    public List<EcAddressOptionResponse> retrieveDistricts(
            Integer divisionId) {

        Integer id = requirePositiveId(divisionId, "division");

        try {
            return ecAddressLookupRepository.retrieveDistricts(id);
        } catch (Exception exception) {
            log.error(
                    "Unable to retrieve EC districts for division {}.",
                    id,
                    exception);

            throw new EcVerificationException(
                    "Unable to retrieve districts.",
                    exception);
        }
    }

    @Override
    public List<EcAddressOptionResponse> retrieveUpazilas(
            Integer districtId) {

        Integer id = requirePositiveId(districtId, "district");

        try {
            return ecAddressLookupRepository.retrieveUpazilas(id);
        } catch (Exception exception) {
            log.error(
                    "Unable to retrieve EC upazilas for district {}.",
                    id,
                    exception);

            throw new EcVerificationException(
                    "Unable to retrieve upazilas.",
                    exception);
        }
    }

    @Override
    public List<EcAddressOptionResponse> retrievePostOffices(
            Integer districtId,
            Integer upazilaId) {

        Integer requiredDistrictId =
                requirePositiveId(districtId, "district");

        Integer requiredUpazilaId =
                requirePositiveId(upazilaId, "upazila");

        try {
            return ecAddressLookupRepository.retrievePostOffices(
                    requiredDistrictId,
                    requiredUpazilaId);

        } catch (Exception exception) {
            log.error(
                    "Unable to retrieve EC post offices for district {} and upazila {}.",
                    requiredDistrictId,
                    requiredUpazilaId,
                    exception);

            throw new EcVerificationException(
                    "Unable to retrieve post offices.",
                    exception);
        }
    }

    private Integer requirePositiveId(Integer id, String label) {
        if (id == null || id <= 0) {
            throw new EcVerificationException(
                    "Invalid " + label + " id.");
        }

        return id;
    }
}