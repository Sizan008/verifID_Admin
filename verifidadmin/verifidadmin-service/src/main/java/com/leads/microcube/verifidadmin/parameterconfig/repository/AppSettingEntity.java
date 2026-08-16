package com.leads.microcube.verifidadmin.parameterconfig.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Maps an application setting stored in {@code APP_SETTINGS}. */
@Entity
@Table(name = "APP_SETTINGS")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AppSettingEntity {

  @Id
  @Column(name = "KEY", nullable = false, length = 450)
  private String key;

  @Column(name = "VALUE", nullable = false, length = 2000)
  private String value;

  @Column(name = "DESCRIPTION", length = 2000)
  private String description;

  @Column(name = "PRIVACYLEVEL", length = 2000)
  private String privacyLevel;
}
