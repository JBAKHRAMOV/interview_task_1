package com.compny.clinic.entity;

import com.compny.base.entity.BaseEntity;
import com.compny.base.enums.Role;
import com.compny.base.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "clinic_table")
public class ClinicEntity extends BaseEntity {
    private String name;
    private String ipAddress;
    private String email;
    @Enumerated(EnumType.STRING)
    private Status status;
}
