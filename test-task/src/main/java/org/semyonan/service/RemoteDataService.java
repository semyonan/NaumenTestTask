package org.semyonan.service;

import jakarta.annotation.PostConstruct;
import org.semyonan.dto.RemotePersonDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RemoteDataService {

    @Value("${app.remote-service-url}")
    private String remoteServiceUrl;

    private WebClient webClient;

    @PostConstruct
    private void init() {
        webClient = WebClient.create(remoteServiceUrl);
    }

    public Integer getAgeByName(String name) {

        RemotePersonDto person = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("name", "{name}")
                        .build(name))
                .retrieve()
                .bodyToMono(RemotePersonDto.class)
                .block();

        return person != null ? person.getAge() : null;

    }
}
