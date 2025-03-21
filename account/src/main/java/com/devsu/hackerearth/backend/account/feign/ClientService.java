package com.devsu.hackerearth.backend.account.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsu.hackerearth.backend.account.model.dto.ClientDto;
import com.devsu.hackerearth.backend.account.model.dto.GenericResponseDto;

@FeignClient(name = "ClientService", url = "${client.service.url}")
public interface ClientService {

    @GetMapping("/{id}")
    GenericResponseDto<ClientDto> getClientById(@PathVariable("id") Long id);

}
