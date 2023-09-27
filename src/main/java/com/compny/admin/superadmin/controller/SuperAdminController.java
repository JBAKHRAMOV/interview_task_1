package com.compny.admin.superadmin.controller;

import com.compny.admin.superadmin.dto.SuperAdminReqDto;
import com.compny.admin.superadmin.service.SuperAdminService;
import com.compny.component.ResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/super-admin")
public class SuperAdminController {

    @Qualifier("super-admin-service")
    private final SuperAdminService superAdminService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<ResDTO> createSuperAdmin(@RequestBody @Valid SuperAdminReqDto dto) {
        return ResponseEntity.ok(superAdminService.createSuperAdmin(dto));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<ResDTO> updateSuperAdmin(@RequestBody @Valid SuperAdminReqDto dto) {
        return ResponseEntity.ok(superAdminService.updateSuperAdmin(dto));
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<ResDTO> deleteSuperAdmin(@PathVariable String id) {
        return ResponseEntity.ok(superAdminService.deleteSuperAdmin(id));
    }
}
