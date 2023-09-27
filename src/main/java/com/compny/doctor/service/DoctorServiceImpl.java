package com.compny.doctor.service;

import com.compny.attach.dto.AttachUplRes;
import com.compny.attach.service.AttachService;
import com.compny.clinic.entity.ClinicEntity;
import com.compny.clinic.service.ClinicService;
import com.compny.component.ResDTO;
import com.compny.config.details.EntityDetails;
import com.compny.doctor.dto.DoctorReqDto;
import com.compny.doctor.dto.DoctorResDto;
import com.compny.doctor.entity.DoctorEntity;
import com.compny.doctor.repository.DoctorRepository;
import com.compny.doctor.work_time.service.WorkTimeService;
import com.compny.exception.ItemNotFoundException;
import com.compny.speciality.entity.SpecialtyEntity;
import com.compny.speciality.service.SpecialtyService;
import com.compny.workplace.entity.WorkPlaceEntity;
import com.compny.workplace.service.WorkPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("doctor-service")
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ClinicService clinicService;
    private final SpecialtyService specalityService;
    private final WorkPlaceService workPlaceService;
    private final WorkTimeService workTimeService;
    private final AttachService attachService;

    @Override
    public ResDTO createDoctor(DoctorReqDto dto, MultipartFile file) {

        ClinicEntity clinicEntity = clinicService.getClinicById(EntityDetails.getProfile().getClinicId());
        SpecialtyEntity specialtyEntity = specalityService.getById(dto.getSpecialtyId());
        WorkPlaceEntity workPlaceEntity = workPlaceService.getById(dto.getWorkPlaceId());

        AttachUplRes uploadAttach = attachService.upload(file);

        DoctorEntity entity = new DoctorEntity();

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setExperience(dto.getExperience());
        entity.setPrice(dto.getPrice());
        entity.setSpecialty(specialtyEntity);
        entity.setWorkPlace(workPlaceEntity);
        entity.setClinic(clinicEntity);
        entity.setPhotoId(uploadAttach.getId());

        workTimeService.createWorkTime(dto.getTimeList(), entity);

        doctorRepository.save(entity);

        return new ResDTO("Doctor created");
    }

    @Override
    public DoctorResDto getDoctorById(String id) {
        return entityToDto(getById(id));
    }

    @Override
    public List<DoctorResDto> getAllDoctorsByClinicId() {
        String clinicId = EntityDetails.getProfile().getClinicId();
        return doctorRepository.findAllByClinicId(clinicId)
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public ResDTO updateDoctor(DoctorReqDto dto, MultipartFile file) {
        String id = EntityDetails.getProfile().getId();

        DoctorEntity entity = getById(id);

        ClinicEntity clinicEntity = clinicService.getClinicById(EntityDetails.getProfile().getClinicId());
        SpecialtyEntity specialtyEntity = specalityService.getById(dto.getSpecialtyId());
        WorkPlaceEntity workPlaceEntity = workPlaceService.getById(dto.getWorkPlaceId());

        attachService.delete(entity.getPhotoId());

        AttachUplRes uploadAttach = attachService.upload(file);

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setExperience(dto.getExperience());
        entity.setPrice(dto.getPrice());
        entity.setSpecialty(specialtyEntity);
        entity.setWorkPlace(workPlaceEntity);
        entity.setClinic(clinicEntity);
        entity.setPhotoId(uploadAttach.getId());

        workTimeService.updateWorkTime(dto.getTimeList(), entity);

        doctorRepository.save(entity);

        return new ResDTO("Doctor updated");
    }

    @Override
    public ResDTO deleteDoctor(String id) {
        DoctorEntity entity = getById(id);

        entity.setIsDeleted(true);

        doctorRepository.save(entity);

        return new ResDTO("Doctor deleted");
    }

    @Override
    public DoctorEntity getDoctorEntityById(String id) {
        return getById(id);
    }

    private DoctorResDto entityToDto(DoctorEntity entity) {
        return new DoctorResDto(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getExperience(),
                entity.getPrice(),
                entity.getSpecialty().getId(),
                entity.getWorkPlace().getId()
        );
    }

    private DoctorEntity getById(String id) {
        String clinicId = EntityDetails.getProfile().getClinicId();
        return doctorRepository.findByIdAndIsDeletedAndClinicId(id, false, clinicId)
                .orElseThrow(() -> new ItemNotFoundException("Doctor not found"));
    }
}
