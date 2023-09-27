package com.compny.clinic.service;

import com.compny.admin.subadmin.service.AdminService;
import com.compny.base.enums.Status;
import com.compny.clinic.dto.ClinicChangeStatusDto;
import com.compny.clinic.dto.ClinicReqDto;
import com.compny.clinic.dto.ClinicResDto;
import com.compny.clinic.dto.ClinicUpdDto;
import com.compny.clinic.entity.ClinicEntity;
import com.compny.clinic.repository.ClinicRepository;
import com.compny.component.ResDTO;
import com.compny.sms.service.SmsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("clinic-service")
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;
    private final AdminService adminService;
    private final SmsService smsService;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResDTO create(ClinicReqDto dto) {

        clinicRepository.findByIpAddress(dto.getIpAddress())
                .ifPresent(clinicEntity -> {
                    throw new RuntimeException("Clinic already exists");
                });

        ClinicEntity clinicEntity = new ClinicEntity();

        clinicEntity.setIpAddress(dto.getIpAddress());
        clinicEntity.setName(dto.getName());
        clinicEntity.setEmail(dto.getEmail());
        clinicEntity.setStatus(Status.ACTIVE);

        clinicRepository.save(clinicEntity);

        String password = "generatedPassword";

        adminService.createAdmin(dto.getFullName(), dto.getEmail(), password, clinicEntity.getId());

        String text = "Your password is: " + password;

        smsService.sendSmsToEmail(dto.getEmail(), text);

        return new ResDTO("Clinic created");
    }

    @Override
    public ClinicResDto getById(String id) {

        return entityToDto(getClinic(id));
    }

    @Override
    public PageImpl<ClinicResDto> getAllByPagination(int page, int size) {

        Page<ClinicEntity> pagination = clinicRepository.findAll(PageRequest.of(page, size));

        return new PageImpl<>(pagination
                .stream()
                .map(this::entityToDto)
                .toList(),
                pagination.getPageable(),
                pagination.getTotalElements()
        );
    }

    @Override
    public ResDTO updateClinic(ClinicUpdDto dto) {

        ClinicEntity clinicEntity = getClinic(dto.getId());

        String message = "";

        if (!clinicEntity.getName().equals(dto.getName())) {
            message += "Clinic name updated: " + clinicEntity.getName() + " -> " + dto.getName() + "\n";
        }

        if (!clinicEntity.getIpAddress().equals(dto.getIpAddress())) {
            message += "Clinic ip address updated: " + clinicEntity.getIpAddress() + " -> " + dto.getIpAddress() + "\n";
        }

        clinicEntity.setName(dto.getName());
        clinicEntity.setIpAddress(dto.getIpAddress());

        adminService.sendMessageAllAdminOfClinic(clinicEntity.getId(), message);

        clinicRepository.save(clinicEntity);

        return new ResDTO("Clinic updated");
    }

    @Override
    public ResDTO changeClinicStatus(ClinicChangeStatusDto dto) {

        ClinicEntity clinicEntity = getClinic(dto.getId());

        clinicEntity.setStatus(dto.getStatus());

        clinicRepository.save(clinicEntity);

        adminService.changeAdminStatus(clinicEntity.getId(), dto.getStatus());

        String message = "Clinic status changed: " + dto.getStatus().name() + "\n";

        if(dto.getStatus().equals(Status.BLOCK)){
            message += " So your account is blocked yet";
        } else if (dto.getStatus().equals(Status.ACTIVE )) {
            message += " So your account status has also been reset";
        }

        adminService.sendMessageAllAdminOfClinic(clinicEntity.getId(), message );

        return new ResDTO("Clinic status changed");
    }

    @Override
    public ClinicEntity getClinicById(String id) {
        return getClinic(id);
    }

    private ClinicEntity getClinic(String id) {
        return clinicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinic not found"));
    }

    private ClinicResDto entityToDto(ClinicEntity clinicEntity) {
        return new ClinicResDto(
                clinicEntity.getId(),
                clinicEntity.getName(),
                clinicEntity.getIpAddress(),
                clinicEntity.getStatus()
        );
    }
}
