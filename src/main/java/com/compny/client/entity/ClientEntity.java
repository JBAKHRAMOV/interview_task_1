package com.compny.client.entity;

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
@Table(name = "client_table")
public class ClientEntity extends BaseEntity {
    private String fullName;
    private String phone;
    private String otherInfo;
    private Boolean isDeleted;


    @Column(name = "clinic_id")
    private String clinicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", insertable = false, updatable = false)
    private ClinicEntity clinic;
}
