package com.compny.doctor.repository;

import com.compny.doctor.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, String> {
    Optional<DoctorEntity> findByIdAndIsDeletedAndClinicId(String id, Boolean deleted, String clinicId);

    List<DoctorEntity> findAllByClinicId(String clinicId);

    @Query(
            value = "SELECT * FROM doctor_table " +
                    "WHERE clinicId = :clinicId AND ( " +
                    "    name LIKE %:searchTerm% " +
                    "    OR surname LIKE %:searchTerm% " +
                    "    OR experience LIKE %:searchTerm% " +
                    "    OR CAST(price AS CHAR) LIKE %:searchTerm% " +
                    " ) ",
            nativeQuery = true
    )
    List<DoctorEntity> search(@Param("searchTerm") String searchTerm, @Param("clinicId") String clinicId);
}
