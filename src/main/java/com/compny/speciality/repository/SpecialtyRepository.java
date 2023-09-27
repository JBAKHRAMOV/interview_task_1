package com.compny.speciality.repository;


import com.compny.speciality.entity.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, String> {

    Optional<SpecialtyEntity> findByIdAndClinicIdAndIsDeleted(String id, String clinicId, Boolean isDeleted);
    Optional<SpecialtyEntity> findByNameAndClinicIdAndIsDeleted(String name, String clinicId, boolean isDeleted);

    List<SpecialtyEntity> findAllByClinicId(String clinicId);

    @Query(
            value = "SELECT * FROM speciality_table " +
                    "WHERE clinicId = :clinicId AND (" +
                    "     name LIKE %:searchTerm% " +
                    ")",
            nativeQuery = true
    )
    List<SpecialtyEntity> search(@Param("searchTerm") String searchTerm, @Param("clinicId") String clinicId);


}
