package com.compny.order.repository;

import com.compny.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    List<OrderEntity> findByClinicIdAndDoctorIdAndDate(String clinicId, String doctorId, LocalDate date);

    @Query(value = "SELECT COUNT(*) FROM order_table " +
            "WHERE doctor_id = :doctorId " +
            "AND clinic_id = :clinicId " +
            "AND date = :date " +
            "AND ((start_time >= :startTime AND start_time < :endTime) " +
            "OR (end_time > :startTime AND end_time <= :endTime))", nativeQuery = true)
    int checkIfDoctorIsFree(
            @Param("doctorId") String doctorId,
            @Param("clinicId") String clinicId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    Optional<OrderEntity> findByIdAndClinicId(Long id, String clinicId);

    List<OrderEntity> findAllByClinicIdAndClientIdAndDate(String clinicId, String clientId, LocalDate date);

    @Query(
            value = "SELECT * FROM order_table " +
                    "WHERE clinicId = :clinicId AND (" +
                    "    description LIKE %:searchTerm% " +
                    ")",
            nativeQuery = true
    )
    List<OrderEntity> search(@Param("clinicId") String clinicId, @Param("searchTerm") String searchTerm);


}
