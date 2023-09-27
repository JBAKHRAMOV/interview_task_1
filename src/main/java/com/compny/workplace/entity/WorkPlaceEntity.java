package com.compny.workplace.entity;

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
@Table(name = "work_place_table")
public class WorkPlaceEntity extends BaseEntity {
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Boolean isDeleted = false;

    @Column(name = "clinic_id")
    private String clinicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", insertable = false, updatable = false)
    private ClinicEntity clinic;
}
