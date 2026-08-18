package com.leads.microcube.verifidadmin.customerprofile.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PARAM_CRG_EDD")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CrgEddEntity {

  @Id
  @Column(name = "CRG_EDD_ID", nullable = false)
  private Integer crgEddId;

  @Column(name = "LABELEN", length = 200)
  private String labelEn;

  @Column(name = "LABELBN", length = 200)
  private String labelBn;

  @Column(name = "QUESTION", nullable = false, length = 1000)
  private String question;

  @Column(name = "QUESTIONTYPE", nullable = false, length = 50)
  private String questionType;

  @Column(name = "BANKSHNAME", length = 50)
  private String bankShortName;

  @Column(name = "REQUIREDFLAG")
  private Integer requiredFlag;

  @Column(name = "PLACEHOLDERTEXT", length = 500)
  private String placeholderText;

  @Column(name = "REQUESTCHANNEL", length = 100)
  private String requestChannel;

  @Column(name = "EDD_INFO", length = 500)
  private String eddInfo;

  @Column(name = "MINLENGTH")
  private Integer minLength;

  @Column(name = "MAXLENGTH")
  private Integer maxLength;

  @Column(name = "MAKEBY", nullable = false, length = 30)
  private String makeBy;

  @Column(name = "MAKEDT", nullable = false)
  private LocalDateTime makeDt;

  @Column(name = "CHECKBY", length = 30)
  private String checkBy;

  @Column(name = "CHECKDT")
  private LocalDateTime checkDt;

  @Column(name = "VERIFYBY", length = 30)
  private String verifyBy;

  @Column(name = "VERIFYDT")
  private LocalDateTime verifyDt;

  @Column(name = "AUTHBY", length = 30)
  private String authBy;

  @Column(name = "AUTHDT")
  private LocalDateTime authDt;

  @Column(name = "UPDATEBY", length = 30)
  private String updateBy;

  @Column(name = "UPDATEDT")
  private LocalDateTime updateDt;

  @Column(name = "AUTHSTATUS", length = 1)
  private String authStatus;

}
