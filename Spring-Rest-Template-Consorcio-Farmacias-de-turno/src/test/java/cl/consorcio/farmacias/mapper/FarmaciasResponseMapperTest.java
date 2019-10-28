package cl.consorcio.farmacias.mapper;

import cl.consorcio.farmacias.dto.FarmaciaResponseResource;
import cl.consorcio.farmacias.model.LocalesRegion;
import cl.consorcio.farmacias.utils.FilterUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FarmaciasResponseMapperTest {

    private List<FarmaciaResponseResource> farmaciaResponseResource;

    private static String idRegion = "7";
    private static String comuna = "MELIPILLA";
    private static String nombreLocal = "MELIPILLA";
    private static String errorCode="0";
    private static String errorDesc="";
    private static String direccion="PLAZA DE ARMAS 537";
    private static String telefono="+560226313070";
    private static String latitud="-33.686092";
    private static String longitud="-71.213890";

    private String fecha="25-10-2019";
    private String local_id="1283";
    private String local_nombre="AHUMADA";
    private String comuna_nombre="MELIPILLA";
    private String localidad_nombre="MELIPILLA";
    private String local_direccion="PLAZA DE ARMAS 537";
    private String funcionamiento_hora_apertura="08:30 hrs.";
    private String funcionamiento_hora_cierre="22:00 hrs.";
    private String local_telefono="+560226313070";
    private String local_lat="-33.686092";
    private String local_lng="-71.213890";
    private String funcionamiento_dia="viernes";
    private String fk_region="7";
    private String fk_comuna="109";

    @InjectMocks
    private FarmaciasResponseMapper farmaciasResponseMapper;

    @Mock
    FilterUtils filterUtils;

    @Before
    public void setup() {
        farmaciaResponseResource = getFarmaciaResponseResource();
        ReflectionTestUtils.setField(farmaciasResponseMapper, "result", getLocalesRegion());
    }

    @Test
    public void farmaciasResponseMapperResponse() {
        when(filterUtils.getListaFiltraComunaLocal(anyObject(),anyString(),anyString())).thenReturn(getLocalesRegion());
        farmaciaResponseResource = farmaciasResponseMapper.convertModelToDTO(getLocalesRegion(), comuna, nombreLocal);
        Assertions.assertThat(farmaciaResponseResource.get(0).getDireccion()).isEqualTo(direccion);


    }

    private List<FarmaciaResponseResource> getFarmaciaResponseResource() {
        List<FarmaciaResponseResource> farmaciaResponseResource = new ArrayList<>();
        FarmaciaResponseResource messageHomeResponseDTo = FarmaciaResponseResource.builder().direccion(direccion).errorCode(errorCode)
                .errorDesc(errorDesc).nombreLocal(nombreLocal).telefono(telefono).latitud(latitud).longitud(longitud).build();

        farmaciaResponseResource.add(messageHomeResponseDTo);
        return farmaciaResponseResource;
    }

    private List<LocalesRegion> getLocalesRegion() {
        List<LocalesRegion> localesRegion = new ArrayList<>();
        LocalesRegion farmaciaResponseModel = LocalesRegion.builder().fecha(fecha).local_id(local_id).local_nombre(local_nombre)
                .comuna_nombre(comuna_nombre).localidad_nombre(localidad_nombre).local_direccion(local_direccion)
                .funcionamiento_hora_apertura(funcionamiento_hora_apertura).funcionamiento_hora_cierre(funcionamiento_hora_cierre)
                .local_telefono(local_telefono).local_lat(local_lat).local_lng(local_lng).funcionamiento_dia(funcionamiento_dia)
                .fk_region(fk_region).fk_comuna(fk_comuna).build();

        localesRegion.add(farmaciaResponseModel);
        return localesRegion;
    }

}