package com.compny.doctor.entity;

import com.compny.base.entity.BaseEntity;
import com.compny.clinic.entity.ClinicEntity;
import com.compny.speciality.entity.SpecialtyEntity;
import com.compny.workplace.entity.WorkPlaceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "doctor_table")
public class DoctorEntity extends BaseEntity {
    private String name;
    private String surname;
    private String experience;
    private Double price;
    private String photoId;
    private Boolean isDeleted = false;


    @Column(name = "clinic_id")
    private String clinicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", insertable = false, updatable = false)
    private ClinicEntity clinic;

    @Column(name = "specialty_id")
    private String specialtyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id", insertable = false, updatable = false)
    private SpecialtyEntity specialty;

    @Column(name = "work_place_id")
    private String workPlaceId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_place_id", insertable = false, updatable = false)
    private WorkPlaceEntity workPlace;
}
