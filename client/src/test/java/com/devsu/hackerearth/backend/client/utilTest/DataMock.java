package com.devsu.hackerearth.backend.client.utilTest;

import java.util.List;

import com.devsu.hackerearth.backend.client.model.Client;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;

public class DataMock {

    public static List<ClientDto> clientsDtoList(){
        return List.of(ClientDto.builder()
        .address("carrera")
        .name("test maria")
        .age(26)
        .dni("1036681035")
        .gender("female")
        .isActive(true)
        .id(1L)
        .build());
    }

    public static ClientDto clientDto(){
        return ClientDto.builder()
        .address("carrera")
        .name("test maria")
        .age(26)
        .dni("1036681035")
        .gender("female")
        .isActive(true)
        .id(1L)
        .build();
    }

    public static List<Client> clients(){
        return List.of(Client.builder()
        .address("carrera")
        .name("test maria")
        .age(26)
        .dni("1036681035")
        .gender("female")
        .active(true)
        .id(1L)
        .build());
    }
    
}
