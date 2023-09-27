package com.compny.admin.subadmin.repository;

import com.compny.admin.subadmin.entity.AdminEntity;
import com.compny.clinic.entity.ClinicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String> {

    Optional<AdminEntity> findByEmail(String email);

    Optional<AdminEntity> findByIdAndClinicId(String id, String clinicId);

    Optional<AdminEntity> findByPasswordAndClinicId(String password, String clinicId);

    Page<AdminEntity> findAllByClinicId(String clinicId, Pageable pageable);
    List<AdminEntity> findAllByClinicId(String clinicId);

    @Query(
            value = "SELECT * FROM admin_table " +
                    "WHERE clinicId = :clinicId AND (fullName LIKE %:searchTerm% OR email LIKE %:searchTerm%)",
            nativeQuery = true
    )
    List<AdminEntity> search(@Param("clinicId") String clinicId, @Param("searchTerm") String searchTerm);





}
