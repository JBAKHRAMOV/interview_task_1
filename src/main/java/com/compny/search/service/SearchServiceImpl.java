package com.compny.search.service;

import com.compny.admin.subadmin.repository.AdminRepository;
import com.compny.admin.subadmin.service.AdminService;
import com.compny.client.repository.ClientRepository;
import com.compny.client.service.ClientService;
import com.compny.config.details.EntityDetails;
import com.compny.doctor.repository.DoctorRepository;
import com.compny.order.repository.OrderRepository;
import com.compny.search.dto.SearchResDto;
import com.compny.speciality.repository.SpecialtyRepository;
import com.compny.workplace.repository.WorkPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service("search-service")
public class SearchServiceImpl implements SearchService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final DoctorRepository doctorRepository;
    private final OrderRepository orderRepository;
    private final SpecialtyRepository specialtyRepository;
    private final WorkPlaceRepository workPlaceRepository;

    private final String  DOMAIN ="localhost:8080/api/v1/";


    @Override
    public List<SearchResDto> search(String query) {
        String clinicId = EntityDetails.getProfile().getClinicId();
        List<SearchResDto> searchResList = new LinkedList<>();

        searchResList.addAll(getAdminsByQuery(query, clinicId));
        searchResList.addAll(getClientsByQuery(query, clinicId));
        searchResList.addAll(getDoctorsByQuery(query, clinicId));
        searchResList.addAll(getOrdersByQuery(query, clinicId));
        searchResList.addAll(getSpecialtiesByQuery(query, clinicId));
        searchResList.addAll(getWorkPlacesByQuery(query, clinicId));

        return searchResList;
    }

    private List<SearchResDto> getAdminsByQuery(String query, String clinicId) {
        return adminRepository.search(clinicId, query)
                .stream()
                .map(entity -> new SearchResDto(entity.getId(), entity.getFullName(), DOMAIN + "admins/" ))
                .toList();
    }

    private List<SearchResDto> getClientsByQuery(String query, String clinicId) {
        return clientRepository.search(query, clinicId)
                .stream()
                .map(entity -> new SearchResDto(entity.getId(), entity.getFullName(), DOMAIN + "clients/" ))
                .toList();
    }

    private List<SearchResDto> getDoctorsByQuery(String query, String clinicId) {
        return doctorRepository.search(query, clinicId)
                .stream()
                .map(entity -> new SearchResDto(entity.getId(), entity.getName(), DOMAIN + "doctors/" ))
                .toList();
    }

    private List<SearchResDto> getOrdersByQuery(String query, String clinicId) {
        return orderRepository.search(query,    clinicId)
                .stream()
                .map(entity -> new SearchResDto(String.valueOf(entity.getId()), "Order", DOMAIN + "orders/" ))
                .toList();
    }

    private List<SearchResDto> getSpecialtiesByQuery(String query, String clinicId) {
        return specialtyRepository.search(query, clinicId)
                .stream()
                .map(entity -> new SearchResDto(entity.getId(), entity.getName(), DOMAIN + "specialties/" ))
                .toList();
    }

    private List<SearchResDto> getWorkPlacesByQuery(String query, String clinicId) {
        return workPlaceRepository.search(query, clinicId)
                .stream()
                .map(entity -> new SearchResDto(entity.getId(), entity.getName(), DOMAIN + "work-places/" ))
                .toList();
    }
}
