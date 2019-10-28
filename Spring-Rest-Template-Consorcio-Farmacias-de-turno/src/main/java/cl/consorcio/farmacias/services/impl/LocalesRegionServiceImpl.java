package cl.consorcio.farmacias.services.impl;

import cl.consorcio.farmacias.client.impl.GetLocalesRegionClientImpl;
import cl.consorcio.farmacias.dto.FarmaciaResponseResource;
import cl.consorcio.farmacias.exception.ConsorcioPharmacyException;
import cl.consorcio.farmacias.mapper.FarmaciasResponseMapper;
import cl.consorcio.farmacias.model.LocalesRegion;
import cl.consorcio.farmacias.services.LocalesRegionService;
import cl.consorcio.farmacias.utils.ApplicationConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class LocalesRegionServiceImpl implements LocalesRegionService {

    private final GetLocalesRegionClientImpl getLocalesRegionClient;
    private final FarmaciasResponseMapper farmaciasResponseMapper;


    /**
     * Method to obtain shift pharmacies by region
     * filtered by comuna and local.
     *
     * @param idRegion
     * @param comuna
     * @param nombreLocal
     * @return farmaciaResponseResource
     * @throws ConsorcioPharmacyException
     */
    @Override
    public List<FarmaciaResponseResource> farmaciaResponseResource(String idRegion, String comuna, String nombreLocal) {

        List<FarmaciaResponseResource> farmaciaResponseResource = new ArrayList<>();
        FarmaciaResponseResource farmaciaError = new FarmaciaResponseResource();
        try {

            List<LocalesRegion> localRegionResponse = getLocalesRegionClient.getLocalRegionResponse(idRegion);
            farmaciaResponseResource = farmaciasResponseMapper.convertModelToDTO(localRegionResponse, comuna, nombreLocal);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.error("No call getLocalRegion for idRegion {}", idRegion);
                farmaciaError.setErrorCode(ApplicationConstants.ERROR_LOCALES_REGION_CODE);
                farmaciaError.setErrorDesc(ApplicationConstants.ERROR_LOCALES_REGION_MESSAGE);
            } else {
                throw e;
            }
        }

        return farmaciaResponseResource;
    }

}



