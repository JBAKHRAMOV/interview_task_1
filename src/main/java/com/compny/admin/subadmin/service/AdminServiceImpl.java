package com.compny.admin.subadmin.service;

import com.compny.admin.subadmin.dto.AdminReqDto;
import com.compny.admin.subadmin.dto.AdminResDto;
import com.compny.admin.subadmin.entity.AdminEntity;
import com.compny.admin.subadmin.repository.AdminRepository;
import com.compny.base.enums.Role;
import com.compny.base.enums.Status;
import com.compny.clinic.entity.ClinicEntity;
import com.compny.clinic.service.ClinicService;
import com.compny.component.ResDTO;
import com.compny.config.details.CurrentUser;
import com.compny.config.details.EntityDetails;
import com.compny.exception.ItemAlreadyExistsException;
import com.compny.exception.ItemNotFoundException;
import com.compny.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service(value = "admin-service")
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final SmsService smsService;

    @Override
    public ResDTO createAdmin(AdminReqDto dto) {

        CurrentUser profile = EntityDetails.getProfile();

        adminRepository.findByEmail(dto.getEmail()).ifPresent(adminEntity -> {
            throw new RuntimeException("Email already exists");
        });


        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setClinicId(profile.getClinicId());
        adminEntity.setEmail(dto.getEmail());
        adminEntity.setFullName(dto.getFullName());
        adminEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        adminEntity.setRole(Role.ROLE_ADMIN);

        adminRepository.save(adminEntity);

        return new ResDTO("Admin created");
    }

    @Override
    public AdminResDto getAdmin(String id) {

        String clinicId = EntityDetails.getProfile().getClinicId();

        return entityToDto(getByIdAndClinicId(id, clinicId));
    }

    @Override
    public PageImpl<AdminResDto> getAllByPagination(int page, int size) {

        String clinicId = EntityDetails.getProfile().getClinicId();

        Page<AdminEntity> pagination = adminRepository.findAllByClinicId(clinicId, PageRequest.of(page, size));

        return new PageImpl<>(
                pagination.getContent().stream()
                        .map(this::entityToDto)
                        .toList(),
                pagination.getPageable(),
                pagination.getTotalElements()
        );
    }

    @Override
    public ResDTO updateAdmin(AdminReqDto dto) {

        String id = EntityDetails.getProfile().getId();

        AdminEntity admin = getById(id);

        admin.setFullName(dto.getFullName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));

        adminRepository.save(admin);

        return new ResDTO("Admin updated");
    }

    @Override
    public ResDTO logout() {

        String id = EntityDetails.getProfile().getId();

        AdminEntity admin = getById(id);

        adminRepository.delete(admin);

        return new ResDTO("Admin deleted");
    }

    @Override
    public void createAdmin(String fullName, String email, String password, String clinicId) {

        adminRepository.findByEmail(email).ifPresent(adminEntity -> {
            throw new ItemAlreadyExistsException("Email already exists");
        });



        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setClinicId(EntityDetails.getProfile().getClinicId());
        adminEntity.setEmail(email);
        adminEntity.setFullName(fullName);
        adminEntity.setPassword(passwordEncoder.encode(password));
        adminEntity.setRole(Role.ROLE_ADMIN);

        adminRepository.save(adminEntity);

    }

    @Override
    public void changeAdminStatus(String clinicId, Status status) {
        if (status.equals(Status.ACTIVE)) {
            adminRepository.findAllByClinicId(clinicId).forEach(adminEntity -> {
                adminEntity.setStatus(adminEntity.getOldStatus());
                adminRepository.save(adminEntity);
            });
        } else {
            adminRepository.findAllByClinicId(clinicId).forEach(adminEntity -> {
                adminEntity.setOldStatus(adminEntity.getStatus());
                adminEntity.setStatus(Status.BLOCK);
                adminRepository.save(adminEntity);
            });
        }
    }

    @Override
    public void sendMessageAllAdminOfClinic(String clinicId, String message) {
        adminRepository.findAllByClinicId(clinicId).forEach(adminEntity -> {
            smsService.sendSmsToEmail(adminEntity.getEmail(), message);
        });
    }

    private AdminResDto entityToDto(AdminEntity entity) {
        return new AdminResDto(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getClinic().getId()
        );
    }

    private AdminEntity getById(String id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Admin not found"));
    }

    private AdminEntity getByIdAndClinicId(String id, String clinicId) {
        return adminRepository.findByIdAndClinicId(id, clinicId)
                .orElseThrow(() -> new ItemNotFoundException("Admin not found"));
    }
}
