package com.leads.microcube.verifidadmin.account.repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRoleRepository extends JpaRepository<BankUserRoleEntity, String> {

  List<BankUserRoleEntity> findAllByOrderByUserRoleIdAsc();

  Optional<BankUserRoleEntity> findByUserIdIgnoreCase(String userId);

  boolean existsByUserIdIgnoreCase(String userId);

  @Query("select coalesce(max(user.userRoleId), 0) from BankUserRoleEntity user")
  Integer retrieveMaximumUserRoleId();

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select user from BankUserRoleEntity user where upper(user.userId) = upper(:userId)")
  Optional<BankUserRoleEntity> retrieveForUpdate(@Param("userId") String userId);
}
