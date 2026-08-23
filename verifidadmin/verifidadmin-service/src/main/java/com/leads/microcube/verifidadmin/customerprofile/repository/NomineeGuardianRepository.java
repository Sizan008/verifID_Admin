package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NomineeGuardianRepository
    extends JpaRepository<NomineeGuardianEntity, NomineeGuardianId> {

  @Query(
      value = """
          SELECT
            TRACKING_NO AS "trackingNo",
            REFERENCE_NO AS "nomineeNo",
            GUARDIAN_NO AS "guardianNo",
            GUARDIAN_NAME AS "guardianName",
            GUARDIAN_ID_TYPE AS "guardianIdType",
            "Guardian_ID_NO" AS "guardianIdNo",
            BIRTHDATE AS "birthdate",
            GENDER AS "gender",
            RELIGION AS "religion",
            RELATION AS "relation",
            AGE AS "age",
            SHAREPERCENT AS "sharePercent",
            MOTHERNAMEEN AS "motherNameEn",
            MOTHERNAMEBN AS "motherNameBn",
            FATHERNAMEEN AS "fatherNameEn",
            FATHERNAMEBN AS "fatherNameBn",
            PRESENTADDRESSEN AS "presentAddressEn",
            PRESENTADDRESSBN AS "presentAddressBn",
            PERMANENTADDRESS AS "permanentAddress",
            COUNTRY AS "country",
            DIVISION AS "division",
            DISTRICT AS "district",
            SUBDISTRICT AS "subDistrict",
            THANA AS "thana",
            ZIP_CODE AS "zipCode",
            OTHER_INFO AS "otherInfo",
            STATUS AS "status"
          FROM NOMINEE_GUARDIAN
          WHERE TRACKING_NO = :trackingNo
            AND REFERENCE_NO = :nomineeNo
          ORDER BY GUARDIAN_NO
          FETCH FIRST 1 ROWS ONLY
          """,
      nativeQuery = true)
  Optional<NomineeGuardianProjection> findFirstProjectionByTrackingNoAndNomineeNo(
      @Param("trackingNo") Long trackingNo, @Param("nomineeNo") Integer nomineeNo);

  @Query(
      value = """
          SELECT
            TRACKING_NO AS "trackingNo",
            REFERENCE_NO AS "nomineeNo",
            GUARDIAN_NO AS "guardianNo",
            GUARDIAN_NAME AS "guardianName",
            GUARDIAN_ID_TYPE AS "guardianIdType",
            "Guardian_ID_NO" AS "guardianIdNo",
            BIRTHDATE AS "birthdate",
            GENDER AS "gender",
            RELIGION AS "religion",
            RELATION AS "relation",
            AGE AS "age",
            SHAREPERCENT AS "sharePercent",
            MOTHERNAMEEN AS "motherNameEn",
            MOTHERNAMEBN AS "motherNameBn",
            FATHERNAMEEN AS "fatherNameEn",
            FATHERNAMEBN AS "fatherNameBn",
            PRESENTADDRESSEN AS "presentAddressEn",
            PRESENTADDRESSBN AS "presentAddressBn",
            PERMANENTADDRESS AS "permanentAddress",
            COUNTRY AS "country",
            DIVISION AS "division",
            DISTRICT AS "district",
            SUBDISTRICT AS "subDistrict",
            THANA AS "thana",
            ZIP_CODE AS "zipCode",
            OTHER_INFO AS "otherInfo",
            STATUS AS "status"
          FROM NOMINEE_GUARDIAN
          WHERE TRACKING_NO = :trackingNo
          ORDER BY REFERENCE_NO, GUARDIAN_NO
          """,
      nativeQuery = true)
  List<NomineeGuardianProjection> findAllProjectionsByTrackingNo(
      @Param("trackingNo") Long trackingNo);

  long countByTrackingNo(Long trackingNo);
}
