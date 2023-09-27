package com.compny.client.service;

import com.compny.client.dto.ClientReqDto;
import com.compny.client.dto.ClientResDto;
import com.compny.client.dto.ClientUpdDto;
import com.compny.client.entity.ClientEntity;
import com.compny.client.repository.ClientRepository;
import com.compny.component.ResDTO;
import com.compny.config.details.EntityDetails;
import com.compny.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("client-service")
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;


    @Override
    public ClientResDto createClient(ClientReqDto dto) {

        ClientEntity entity = new ClientEntity();
        entity.setFullName(dto.getFullName());
        entity.setPhone(dto.getPhone());
        entity.setOtherInfo(dto.getOtherInfo());
        entity.setClinicId(EntityDetails.getProfile().getClinicId());

        return entityToDto(clientRepository.save(entity));

    }

    @Override
    public ClientResDto getClientById(String id) {
        return entityToDto(getClient(id));
    }

    @Override
    public List<ClientResDto> getAllClientsByClinicId(String clinicId) {
        return clientRepository.findAllByClinicId(clinicId)
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public ResDTO updateClient(ClientUpdDto dto) {

        ClientEntity entity = getClient(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setPhone(dto.getPhone());
        entity.setOtherInfo(dto.getOtherInfo());

        clientRepository.save(entity);

        return new ResDTO("Client updated");
    }

    @Override
    public ResDTO deleteClient(String id) {

        ClientEntity client = getClient(id);
        client.setIsDeleted(true);

        clientRepository.save(client);

        return new ResDTO("Client deleted");
    }

    private ClientResDto entityToDto(ClientEntity entity) {
        return ClientResDto.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .phone(entity.getPhone())
                .phone(entity.getPhone())
                .otherInfo(entity.getOtherInfo())
                .build();
    }

    private ClientEntity getClient(String id) {
        String clinicId = EntityDetails.getProfile().getClinicId();
        return clientRepository.findByIdAndClinicId(id, clinicId)
                .orElseThrow(() -> new ItemNotFoundException("Client not found"));
    }
}
