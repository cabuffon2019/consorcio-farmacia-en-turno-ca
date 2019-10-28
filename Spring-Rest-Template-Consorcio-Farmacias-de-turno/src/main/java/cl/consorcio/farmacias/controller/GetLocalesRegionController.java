package cl.consorcio.farmacias.controller;


import cl.consorcio.farmacias.dto.FarmaciaResponseResource;
import cl.consorcio.farmacias.services.LocalesRegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class GetLocalesRegionController {

    private final LocalesRegionService localesRegionService;

    static final String API_FARMACIAS = "/api/farmacias";

    /**
     * Method for getting the shift pharmacy available response
     *
     * @param {idRegion}
     * @param {comuna}
     * @param {nombreLocal}
     * @return response
     */
    @GetMapping(value = API_FARMACIAS)
    public List<FarmaciaResponseResource> getFarmacias(
            @Valid @RequestParam(value = "idRegion") String idRegion, @Valid @RequestParam(value = "comuna") String comuna,
            @Valid @RequestParam(value = "nombreLocal") String nombreLocal) {
        log.info("Request Received: {}", idRegion);
        return localesRegionService.farmaciaResponseResource(idRegion, comuna, nombreLocal);
    }
}
