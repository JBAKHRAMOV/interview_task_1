package com.compny.workplace.repository;

import com.compny.workplace.entity.WorkPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface WorkPlaceRepository extends JpaRepository<WorkPlaceEntity, String> {
    Optional<WorkPlaceEntity> findByIdAndIsDeletedAndClinicId(String id, Boolean b, String clinicId);

    Optional<WorkPlaceEntity> findByName(String name);

    List<WorkPlaceEntity> findAllByClinicId(String clinicId);

    @Query(
            value = "SELECT * FROM work_place_table " +
                    "WHERE clinicId = :clinicId AND (" +
                    "    name LIKE %:searchTerm% " +
                    "    OR address LIKE %:searchTerm%" +
                    ")",
            nativeQuery = true
    )
    List<WorkPlaceEntity> search(@Param("searchTerm") String searchTerm, @Param("clinicId") String clinicId);

}
