package com.compny.speciality.controller;

import com.compny.component.ResDTO;
import com.compny.speciality.dto.SpecialtyReqDto;
import com.compny.speciality.dto.SpecialtyResDto;
import com.compny.speciality.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @PostMapping("/create")
    public ResponseEntity<ResDTO> createSpecialty(@RequestBody @Valid SpecialtyReqDto dto) {
        return ResponseEntity.ok(specialtyService.createSpecialty(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyResDto> getSpecialtyById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(specialtyService.getSpecialtyById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SpecialtyResDto>> getAllSpecialtiesByClinicId() {
        return ResponseEntity.ok(specialtyService.getAllSpecialtiesByClinicId());
    }

    @PutMapping("/update")
    public ResponseEntity<ResDTO> updateSpecialty(@RequestBody @Valid SpecialtyReqDto dto) {
        return ResponseEntity.ok(specialtyService.updateSpecialty(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResDTO> deleteSpecialty(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(specialtyService.deleteSpecialty(id));
    }
}
