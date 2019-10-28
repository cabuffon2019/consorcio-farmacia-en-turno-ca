package cl.consorcio.farmacias.client;

import cl.consorcio.farmacias.model.LocalesRegion;

import java.util.List;

public interface GetLocalesRegionClient {

    List<LocalesRegion> getLocalRegionResponse(String idRegion);
}

