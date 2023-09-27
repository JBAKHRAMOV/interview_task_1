package com.compny.admin.subadmin.entity;

import com.compny.base.entity.BaseEntity;
import com.compny.base.enums.Role;
import com.compny.base.enums.Status;
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
@Table(name = "admin_table")
public class AdminEntity extends BaseEntity {

    private String fullName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Status status;
    private Status oldStatus;

    @Column(name = "clinic_id")
    private String clinicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", insertable = false, updatable = false)
    private ClinicEntity clinic;
}