package com.devsu.hackerearth.backend.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.*;

import com.devsu.hackerearth.backend.client.model.Client;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "isActive", source = "active")
    ClientDto entityToClientDto(Client client);

    @Mapping(target = "active", source = "active")
    @Mapping(target = "id", source = "id")
    Client clientDtoToEntity(ClientDto clientDto);

}
