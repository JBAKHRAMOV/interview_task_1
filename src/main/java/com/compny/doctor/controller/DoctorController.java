package com.compny.doctor.controller;

import com.compny.component.ResDTO;
import com.compny.doctor.dto.DoctorReqDto;
import com.compny.doctor.dto.DoctorResDto;
import com.compny.doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    @Qualifier("doctor-service")
    private final DoctorService doctorService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResDTO> createDoctor(@RequestBody @Valid DoctorReqDto dto, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(doctorService.createDoctor(dto, file));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DoctorResDto> getDoctorById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }


    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DoctorResDto>> getAllDoctorsByClinicId() {
        return ResponseEntity.ok(doctorService.getAllDoctorsByClinicId());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResDTO> updateDoctor(@RequestBody @Valid DoctorReqDto dto, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(doctorService.updateDoctor(dto, file));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResDTO> deleteDoctor(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(doctorService.deleteDoctor(id));
    }



}
