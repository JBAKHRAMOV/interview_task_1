package com.compny.admin.superadmin.repository;

import com.compny.admin.superadmin.entity.SuperAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdminEntity, String> {

    Optional<SuperAdminEntity> findByEmail(String email);
}
