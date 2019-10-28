package cl.consorcio.farmacias.client.impl;

import cl.consorcio.farmacias.client.GetLocalesRegionClient;
import cl.consorcio.farmacias.model.IdRegionRequest;
import cl.consorcio.farmacias.model.LocalesRegion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


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
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(idRegionReq, headers);

        ResponseEntity<List<LocalesRegion>> responseResponseEntity =
                restTemplate.exchange(getLocalesRegionUri + "?id_region=" + idRegionReq,
                        HttpMethod.GET, entity, new ParameterizedTypeReference<List<LocalesRegion>>() {
                        });

        List<LocalesRegion> localesRegion = responseResponseEntity.getBody();

        return localesRegion;

    }
}