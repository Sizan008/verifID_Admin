package com.leads.microcube.verifidadmin.ecverification.repository;

import com.leads.microcube.verifidadmin.ecverification.query.EcAddressOptionResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EcAddressLookupRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<EcAddressOptionResponse> retrieveDivisions() {
        return jdbcTemplate.query(
                """
                SELECT TO_CHAR(DIVISION_ID) AS ID,
                       TRIM(DIVISION_NM) AS NAME
                  FROM PARAM_ADR_DIVISION
                 ORDER BY DIVISION_NM
                """,
                this::mapOption);
    }

    public List<EcAddressOptionResponse> retrieveDistricts(
            Integer divisionId) {

        return jdbcTemplate.query(
                """
                SELECT TO_CHAR(DISTRICT_ID) AS ID,
                       TRIM(DISTRICT_NM) AS NAME
                  FROM PARAM_ADR_DISTRICT
                 WHERE DIVISION_ID = ?
                 ORDER BY DISTRICT_NM
                """,
                this::mapOption,
                divisionId);
    }

    public List<EcAddressOptionResponse> retrieveUpazilas(
            Integer districtId) {

        return jdbcTemplate.query(
                """
                SELECT TO_CHAR(THANA_ID) AS ID,
                       TRIM(THANA_NM) AS NAME
                  FROM PARAM_ADR_THANA
                 WHERE DISTRICT_ID = ?
                 ORDER BY THANA_NM
                """,
                this::mapOption,
                districtId);
    }

    public List<EcAddressOptionResponse> retrievePostOffices(
            Integer districtId,
            Integer upazilaId) {

        return jdbcTemplate.query(
                """
                SELECT TO_CHAR(PO_ID) AS ID,
                       TRIM(PO_NM) AS NAME
                  FROM PARAM_ADR_POST_OFFICE
                 WHERE DISTRICT_ID = ?
                   AND UPAZILA_ID = ?
                 ORDER BY PO_NM
                """,
                this::mapOption,
                districtId,
                upazilaId);
    }

    private EcAddressOptionResponse mapOption(
            ResultSet resultSet,
            int rowNumber) throws SQLException {

        return EcAddressOptionResponse.builder()
                .id(resultSet.getString("ID"))
                .name(resultSet.getString("NAME"))
                .build();
    }
}