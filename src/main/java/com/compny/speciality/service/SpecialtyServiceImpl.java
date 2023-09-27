package com.compny.speciality.service;

import com.compny.component.ResDTO;
import com.compny.config.details.EntityDetails;
import com.compny.exception.ItemNotFoundException;
import com.compny.speciality.dto.SpecialtyReqDto;
import com.compny.speciality.dto.SpecialtyResDto;
import com.compny.speciality.entity.SpecialtyEntity;
import com.compny.speciality.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("specialty-service")
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public ResDTO createSpecialty(SpecialtyReqDto dto) {
        specialtyRepository
                .findByNameAndClinicIdAndIsDeleted(dto.getName(), EntityDetails.getProfile().getClinicId(), false)
                .ifPresent(specialtyEntity -> {
                    throw new ItemNotFoundException("Specialty already exists");
                });

        SpecialtyEntity entity = new SpecialtyEntity();
        entity.setName(dto.getName());
        entity.setClinicId(EntityDetails.getProfile().getClinicId());

        specialtyRepository.save(entity);

        return new ResDTO("Specialty created");
    }

    @Override
    public SpecialtyResDto getSpecialtyById(String id) {
        return entityToDto(getSpecialty(id));
    }

    @Override
    public List<SpecialtyResDto> getAllSpecialtiesByClinicId() {
        String clinicId = EntityDetails.getProfile().getClinicId();
        return specialtyRepository.findAllByClinicId(clinicId)
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public ResDTO updateSpecialty(SpecialtyReqDto dto) {

        SpecialtyEntity specialty = getSpecialty(dto.getId());
        specialty.setName(dto.getName());

        specialtyRepository.save(specialty);

        return new ResDTO("Specialty updated");
    }

    @Override
    public ResDTO deleteSpecialty(String id) {

        SpecialtyEntity specialty = getSpecialty(id);

        specialty.setIsDeleted(true);

        specialtyRepository.save(specialty);

        return new ResDTO("Specialty deleted");
    }

    @Override
    public SpecialtyEntity getById(String id) {
        return getSpecialty(id);
    }

    private SpecialtyResDto entityToDto(SpecialtyEntity entity) {
        return new SpecialtyResDto(
                entity.getId(),
                entity.getName()
        );
    }

    private SpecialtyEntity getSpecialty(String id) {
        String clinicId = EntityDetails.getProfile().getClinicId();
        return specialtyRepository.findByIdAndClinicIdAndIsDeleted(id, clinicId, false)
                .orElseThrow(() -> new ItemNotFoundException("Specialty not found"));
    }
}
