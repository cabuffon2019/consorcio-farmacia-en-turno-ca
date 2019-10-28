package cl.consorcio.farmacias.client;

import cl.consorcio.farmacias.client.impl.GetLocalesRegionClientImpl;
import cl.consorcio.farmacias.model.LocalesRegion;
import org.springframework.core.ParameterizedTypeReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class GetLocalesRegionClientImplTest {

    @InjectMocks
    private GetLocalesRegionClientImpl getLocalesRegionClientImpl;

    @Mock
    private RestTemplate restTemplate;

    private static final String URL_GET_LOCALES_REGION = "https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion";

    private List<LocalesRegion> localesRegion;
    private List<LocalesRegion> localesRegionMs;

    private static String fecha="25-10-2019";
    private static String local_id="1283";
    private static String local_nombre="AHUMADA";
    private static String comuna_nombre="MELIPILLA";
    private static String localidad_nombre="MELIPILLA";
    private static String local_direccion="PLAZA DE ARMAS 537";
    private static String funcionamiento_hora_apertura="08:30 hrs.";
    private static String funcionamiento_hora_cierre="22:00 hrs.";
    private static String local_telefono="+560226313070";
    private static String local_lat="-33.686092";
    private static String local_lng="-71.213890";
    private static String funcionamiento_dia="viernes";
    private static String fk_region="7";
    private static String fk_comuna="109";

    private static String idRegion = "7";

    @Before
    public void init() {
        localesRegion = getLocalesRegion();

        getLocalesRegionClientImpl = new GetLocalesRegionClientImpl(restTemplate);
        ReflectionTestUtils.setField(getLocalesRegionClientImpl, "getLocalesRegionUri", URL_GET_LOCALES_REGION);
    }


    @Test
    public void GetLocalesRegionClientImplTest(){
        //Arrange
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(anyObject()),
                new ParameterizedTypeReference<List<LocalesRegion>>() {
        })).thenReturn(new ResponseEntity<>(getLocalesRegion(), HttpStatus.OK));

        //ACT
        List<LocalesRegion> localesRegionResponse = getLocalesRegionClientImpl.getLocalRegionResponse(idRegion);


        //ASSERT
        assertThat(localesRegionResponse).isEqualTo(localesRegion);
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