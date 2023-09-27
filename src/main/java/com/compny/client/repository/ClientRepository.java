package com.compny.client.repository;

import com.compny.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String>{

    Optional<ClientEntity> findByPhone(String phone);

    Optional<ClientEntity> findByIdAndClinicId(String id, String clinicId);

    List<ClientEntity> findAllByClinicId(String clinicId);

    @Query(
            value = "SELECT * FROM client_table " +
                    "WHERE clinicId = :clinicId AND (" +
                    "    fullName LIKE %:searchTerm% " +
                    "    OR phone LIKE %:searchTerm% " +
                    "    OR otherInfo LIKE %:searchTerm% )",
            nativeQuery = true
    )
    List<ClientEntity> search(@Param("clinicId") String clinicId, @Param("searchTerm") String searchTerm);


}
