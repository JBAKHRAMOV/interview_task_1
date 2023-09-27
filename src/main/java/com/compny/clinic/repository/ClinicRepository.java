package com.compny.clinic.repository;

import com.compny.clinic.entity.ClinicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<ClinicEntity, String> {

    Optional<ClinicEntity> findByIpAddress(String ipAddress);

}
