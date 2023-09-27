package com.compny.doctor.work_time.entity;
import com.compny.admin.superadmin.entity.SuperAdminEntity;
import com.compny.base.entity.BaseEntity;
import com.compny.doctor.entity.DoctorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;


@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "work_time_table")
public class WorkTimeEntity extends BaseEntity {

    private LocalTime startTime;
    private LocalTime endTime;
    private Integer dateOfWeek;

    @Column(name = "doctor_id")
    private String doctorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private DoctorEntity doctor;
}
