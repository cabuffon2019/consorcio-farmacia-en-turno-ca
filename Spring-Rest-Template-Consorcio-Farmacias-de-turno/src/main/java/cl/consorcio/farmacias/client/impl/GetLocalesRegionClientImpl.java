package cl.farmacias.client.impl;

import cl.farmacias.client.GetLocalesRegionClient;
import cl.farmacias.model.IdRegionRequest;
import cl.farmacias.model.LocalesRegion;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.internal.LinkedTreeMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class GetLocalesRegionClientImpl implements GetLocalesRegionClient {

    private final RestTemplate restTemplate;

    @Value("${get-locales-region.uri}")
    private String getLocalesRegionUri;

    @Override
    public List<LocalesRegion> getLocalRegionResponse(String idRegion) {

        IdRegionRequest idRegionReq = new IdRegionRequest();
        idRegionReq.setIdRegion(idRegion);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);


        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(getLocalesRegionUri)
                .queryParam("id_region", idRegion);
        log.debug("Call local region URL {}", uriComponentsBuilder.toUriString());
        ResponseEntity<String> responseResponseEntity = restTemplate
                .exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET,
                        new HttpEntity<>(headers), String.class);

        String localesRegion = responseResponseEntity.getBody();

        System.out.println(localesRegion);
        Gson gson = new Gson();
        List<LinkedTreeMap> list = gson.fromJson(localesRegion, List.class);
        return list.stream()
                .map(map -> {
                    JsonObject jsonObject = gson.toJsonTree(map).getAsJsonObject();
                    return gson.fromJson(jsonObject, LocalesRegion.class);
                }).collect(Collectors.toList());
    }
}



/*
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(getLocalesRegionUri)
                .queryParam("id_region", idRegion);
        log.debug("Call accept URL {}", uriComponentsBuilder.toUriString());
        ResponseEntity<List<LocalesRegion>> responseResponseEntity = restTemplate
                .exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET,
                        new HttpEntity<>(headers), new ParameterizedTypeReference<List<LocalesRegion>>() {
                        });

       List<LocalesRegion> localesRegion = responseResponseEntity.getBody();


       ResponseEntity<List<LocalesRegion>> responseResponseEntity =
                restTemplate.exchange(getLocalesRegionUri + "?id_region=" + idRegionReq,
                        HttpMethod.GET, entity, new ParameterizedTypeReference<List<LocalesRegion>>() {
                        });

        */
