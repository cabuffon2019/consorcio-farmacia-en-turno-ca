package cl.consorcio.farmacias.services;

import cl.consorcio.farmacias.client.impl.GetLocalesRegionClientImpl;
import cl.consorcio.farmacias.dto.FarmaciaResponseResource;
import cl.consorcio.farmacias.mapper.FarmaciasResponseMapper;
import cl.consorcio.farmacias.model.LocalesRegion;
import cl.consorcio.farmacias.services.impl.LocalesRegionServiceImpl;
import cl.consorcio.farmacias.utils.FilterUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocalesRegionServiceImplTest {

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

    @Mock
    private FarmaciasResponseMapper farmaciasResponseMapper;

    @Mock
    private FilterUtils filterUtils;

    @Mock
    private GetLocalesRegionClientImpl getLocalesRegionClientImpl;

    @InjectMocks
    private LocalesRegionServiceImpl localesRegionServiceImpl;

    @Before
    public void init() throws IOException {
        when(getLocalesRegionClientImpl.getLocalRegionResponse(anyString())).thenReturn(getLocalesRegion());
        when(farmaciasResponseMapper.convertModelToDTO(anyObject(),anyString(),anyString())).thenReturn(getFarmaciaResponseResource());
        //when(filterUtils.getListaFiltraComunaLocal(anyObject(),anyString(),anyString())).thenReturn(getLocalesRegion());
    }


    @Test
    public void farmaciaResponseResourceTest() {
        List<FarmaciaResponseResource> farmaciaResponseResource;
        when(getLocalesRegionClientImpl.getLocalRegionResponse(anyString())).thenReturn(getLocalesRegion());
        when(farmaciasResponseMapper.convertModelToDTO(anyObject(),anyString(),anyString())).thenReturn(getFarmaciaResponseResource());
        farmaciaResponseResource = localesRegionServiceImpl.farmaciaResponseResource(idRegion, comuna, nombreLocal);
        assertNotNull(farmaciaResponseResource);

    }

    @Test
    public void farmaciaResponseResourceNotFoundException() {
        List<FarmaciaResponseResource> farmaciaResponseResource;
       // when(farmaciasResponseMapper.convertModelToDTO(anyObject(),anyString(),anyString())).thenReturn(getFarmaciaResponseResource());
        when(getLocalesRegionClientImpl.getLocalRegionResponse(anyString())).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        farmaciaResponseResource = localesRegionServiceImpl.farmaciaResponseResource(idRegion, comuna, nombreLocal);
        assertNotNull(farmaciaResponseResource);
        //assertTrue(farmaciaResponseResource.get(0).getErrorCode().equals(ApplicationConstants.ERROR_LOCALES_REGION_CODE));
    }

    @Test(expected = HttpClientErrorException.class)
    public void farmaciaResponseResourceClientException() {
        List<FarmaciaResponseResource> farmaciaResponseResource;
       // when(farmaciasResponseMapper.convertModelToDTO(anyObject(),anyString(),anyString())).thenReturn(getFarmaciaResponseResource());
        when(getLocalesRegionClientImpl.getLocalRegionResponse(anyString())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        localesRegionServiceImpl.farmaciaResponseResource(idRegion, comuna, nombreLocal);
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
