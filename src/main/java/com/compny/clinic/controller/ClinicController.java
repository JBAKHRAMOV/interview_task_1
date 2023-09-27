package com.compny.clinic.controller;

import com.compny.clinic.dto.ClinicChangeStatusDto;
import com.compny.clinic.dto.ClinicReqDto;
import com.compny.clinic.dto.ClinicResDto;
import com.compny.clinic.dto.ClinicUpdDto;
import com.compny.clinic.service.ClinicService;
import com.compny.component.ResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clinics")
public class ClinicController {

    @Qualifier("clinic-service")
    private final ClinicService clinicService;

    @PostMapping("/create")
    public ResponseEntity<ResDTO> create(@RequestBody @Valid ClinicReqDto dto) {
        return ResponseEntity.ok(clinicService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicResDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(clinicService.getById(id));
    }

    @GetMapping("/page")
    public ResponseEntity<?> getAllByPagination(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(clinicService.getAllByPagination(page, size));
    }

    @PutMapping("/update")
    public ResponseEntity<ResDTO> updateClinic(@RequestBody @Valid ClinicUpdDto dto) {
        return ResponseEntity.ok(clinicService.updateClinic(dto));
    }

    @PutMapping("/change-status")
    public ResponseEntity<ResDTO> changeClinicStatus(@RequestBody @Valid ClinicChangeStatusDto dto) {
        return ResponseEntity.ok(clinicService.changeClinicStatus(dto));
    }


}
