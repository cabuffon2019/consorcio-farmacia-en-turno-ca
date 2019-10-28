package cl.consorcio.farmacias.mapper;

import cl.consorcio.farmacias.dto.FarmaciaResponseResource;
import cl.consorcio.farmacias.model.LocalesRegion;
import cl.consorcio.farmacias.utils.FilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class FarmaciasResponseMapper {

    /**
     * Method for converting Model to DTO
     *
     * @param localesRegion
     * @param comuna
     * @param nombrelocal
     * @return farmaciaResponseResource
     */
    public List<FarmaciaResponseResource> convertModelToDTO(List<LocalesRegion> localesRegion, String comuna, String nombrelocal) {

        FilterUtils filterUtils = new FilterUtils();
        FarmaciaResponseResource farmacia = new FarmaciaResponseResource();
        List<FarmaciaResponseResource> farmaciaResponseResource = new ArrayList<>();
        ;

        List<LocalesRegion> result = filterUtils.getListaFiltraComunaLocal(localesRegion, comuna, nombrelocal);

        for (LocalesRegion r : result) {
            farmacia.setNombreLocal(r.getLocalidad_nombre());
            farmacia.setDireccion(r.getLocal_direccion());
            farmacia.setTelefono(r.getLocal_telefono());
            farmacia.setLatitud(r.getLocal_lat());
            farmacia.setLongitud(r.getLocal_lng());
            farmacia.setErrorCode("0");
            farmacia.setErrorDesc("");

            farmaciaResponseResource.add(farmacia);

        }

        return farmaciaResponseResource;
    }
}
