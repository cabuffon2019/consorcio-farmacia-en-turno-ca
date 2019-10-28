package cl.consorcio.farmacias.services;

import cl.consorcio.farmacias.dto.FarmaciaResponseResource;
import cl.consorcio.farmacias.model.LocalesRegion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocalesRegionService {
    List<FarmaciaResponseResource> farmaciaResponseResource(String idRegion, String comuna, String nombrelocal);
}
