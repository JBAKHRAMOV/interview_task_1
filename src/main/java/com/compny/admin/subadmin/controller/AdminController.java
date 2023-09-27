package com.compny.admin.subadmin.controller;

import com.compny.admin.subadmin.dto.AdminReqDto;
import com.compny.admin.subadmin.dto.AdminResDto;
import com.compny.admin.subadmin.service.AdminService;
import com.compny.component.ResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
public class AdminController {

    @Qualifier("admin-service")
    private final AdminService superAdminService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResDTO> createAdmin(@Valid @RequestBody AdminReqDto dto) {
        return ResponseEntity.ok(superAdminService.createAdmin(dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AdminResDto> getAdmin(@PathVariable String id) {
        return ResponseEntity.ok(superAdminService.getAdmin(id));
    }

    @GetMapping("/page")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PageImpl<AdminResDto>> getAllByPagination(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(superAdminService.getAllByPagination(page, size));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResDTO> updateAdmin(@Valid @RequestBody AdminReqDto dto) {
        return ResponseEntity.ok(superAdminService.updateAdmin(dto));
    }

    @PutMapping("/logout")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResDTO> logout() {
        return ResponseEntity.ok(superAdminService.logout());
    }

}
