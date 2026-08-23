package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAdditionalInfoDetailRepository
    extends JpaRepository<CustomerAdditionalInfoDetailEntity, CustomerAdditionalInfoDetailId> {

  /**
   * PARAM_CUS_ADDITIONAL_INFO_DTLS is a legacy Oracle table whose mixed-case quoted
   * identifiers ("TrackingNo", "PropertyName", "PropertyAnswer") must be addressed
   * exactly. A derived JPA query causes the configured physical naming strategy to
   * generate "tracking_no" / "property_name", which Oracle rejects with ORA-00904.
   */
  @Query(
      value = """
          SELECT "PropertyAnswer"
          FROM PARAM_CUS_ADDITIONAL_INFO_DTLS
          WHERE "TrackingNo" = :trackingNo
            AND "PropertyName" = :propertyName
            AND ROWNUM = 1
          """,
      nativeQuery = true)
  Optional<String> findFirstPropertyAnswerByTrackingNoAndPropertyName(
      @Param("trackingNo") Long trackingNo,
      @Param("propertyName") String propertyName);
}
