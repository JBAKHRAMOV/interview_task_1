package com.compny.doctor.work_time.repository;

import com.compny.doctor.work_time.entity.WorkTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTimeEntity, String> {

    List<WorkTimeEntity> findAllByDoctorId(String doctorId);

    Optional<WorkTimeEntity> findByDoctorIdAndDateOfWeek(String doctorId, Integer day);
}
