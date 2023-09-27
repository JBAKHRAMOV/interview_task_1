package com.compny.order.entity;

import com.compny.client.entity.ClientEntity;
import com.compny.clinic.entity.ClinicEntity;
import com.compny.doctor.entity.DoctorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "order_table")
public class OrderEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Boolean isDeleted;


    @Column(name = "clinic_id")
    private String clinicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", insertable = false, updatable = false)
    private ClinicEntity clinic;

    @Column(name = "client_id")
    private String clientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientEntity client;

    @Column(name = "doctor_id")
    private String doctorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private DoctorEntity doctor;
}
