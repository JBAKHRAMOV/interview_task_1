package com.compny.admin.superadmin.service;

import com.compny.admin.superadmin.dto.SuperAdminReqDto;
import com.compny.admin.superadmin.entity.SuperAdminEntity;
import com.compny.admin.superadmin.repository.SuperAdminRepository;
import com.compny.base.enums.Role;
import com.compny.component.ResDTO;
import com.compny.config.details.CurrentUser;
import com.compny.config.details.EntityDetails;
import com.compny.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service(value = "super-admin-service")
public class SuperAdminServiceImpl implements SuperAdminService {

    private final SuperAdminRepository superAdminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResDTO createSuperAdmin(SuperAdminReqDto dto) {
        superAdminRepository.findByEmail(dto.getEmail()).ifPresent(superAdminEntity -> {
            throw new RuntimeException("Email already exists");
        });

        SuperAdminEntity superAdminEntity = new SuperAdminEntity(
                dto.getFullName(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                Role.ROLE_SUPER_ADMIN
        );

        superAdminRepository.save(superAdminEntity);

        return new ResDTO("Super Admin created");
    }

    @Override
    public ResDTO updateSuperAdmin(SuperAdminReqDto dto) {

        CurrentUser profile = EntityDetails.getProfile();

        SuperAdminEntity superAdminEntity = getSuperAdmin(profile.getId());

        superAdminEntity.setFullName(dto.getFullName());
        superAdminEntity.setEmail(dto.getEmail());
        superAdminEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

        superAdminRepository.save(superAdminEntity);

        return new ResDTO("Super Admin updated");

    }

    @Override
    public ResDTO deleteSuperAdmin(String id) {

        SuperAdminEntity superAdminEntity = getSuperAdmin(id);

        superAdminRepository.delete(superAdminEntity);

        return new ResDTO("Super Admin deleted");
    }

    private SuperAdminEntity getSuperAdmin(String id) {
        return superAdminRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Super Admin not found"));
    }
}
