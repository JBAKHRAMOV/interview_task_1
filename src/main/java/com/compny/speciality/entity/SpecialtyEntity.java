package com.compny.speciality.entity;

import com.compny.base.entity.BaseEntity;
import com.compny.clinic.entity.ClinicEntity;
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
@Table(name = "speciality_table")
public class SpecialtyEntity extends BaseEntity {
    private String name;
    private Boolean isDeleted = false;

    @Column(name = "clinic_id")
    private String clinicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", insertable = false, updatable = false)
    private ClinicEntity clinic;
}
