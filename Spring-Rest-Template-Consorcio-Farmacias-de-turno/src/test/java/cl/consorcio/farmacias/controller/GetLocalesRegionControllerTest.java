package cl.consorcio.farmacias.controller;

import cl.consorcio.farmacias.dto.FarmaciaResponseResource;
import cl.consorcio.farmacias.services.LocalesRegionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GetLocalesRegionControllerTest {

    private static String idRegion = "7";
    private static String comuna = "MELIPILLA";
    private static String nombreLocal = "MELIPILLA";
    private static String errorCode="0";
    private static String errorDesc="";
    private static String direccion="PLAZA DE ARMAS 537";
    private static String telefono="+560226313070";
    private static String latitud="-33.686092";
    private static String longitud="-71.213890";

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private GetLocalesRegionController getLocalesRegionController;
    @Mock
    private LocalesRegionService localesRegionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getLocalesRegionController = new GetLocalesRegionController(localesRegionService);
        mockMvc = MockMvcBuilders.standaloneSetup(getLocalesRegionController).setControllerAdvice(new GetLocalesRegionControllerAdvice()).build();
    }

    @Test
    public void testGetFarmacias_NotNull() {
        when(localesRegionService.farmaciaResponseResource(anyString(),anyString(),anyString())).thenReturn(getFarmaciaResponseResource());
        List<FarmaciaResponseResource> responseDTO = getLocalesRegionController.getFarmacias(idRegion, comuna, nombreLocal);
        assertNotNull(responseDTO);
    }

    @Test
    public void testGetFarmacias_200_OK() throws Exception {
        when(localesRegionService.farmaciaResponseResource(anyString(),anyString(),anyString())).thenReturn(getFarmaciaResponseResource());
        this.mockMvc.perform(get(GetLocalesRegionController.API_FARMACIAS).with(request -> {
            request.setParameter("idRegion", idRegion);
            request.setParameter("comuna", comuna);
            request.setParameter("nombreLocal", nombreLocal);
            return request;
        })).andExpect(status().isOk());
    }


    @Test
    public void testGetFarmacias_ClientException() throws Exception {
        when(localesRegionService.farmaciaResponseResource(anyString(),anyString(),anyString())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        this.mockMvc.perform(get(GetLocalesRegionController.API_FARMACIAS).with(request -> {
            request.setParameter("idRegion", idRegion);
            request.setParameter("comuna", comuna);
            request.setParameter("nombreLocal", nombreLocal);
            return request;
        })).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetFarmacias_ServerException() throws Exception {
        when(localesRegionService.farmaciaResponseResource(anyString(),anyString(),anyString())).thenThrow(new HttpServerErrorException(HttpStatus.BAD_GATEWAY));
        this.mockMvc.perform(get(GetLocalesRegionController.API_FARMACIAS).with(request -> {
            request.setParameter("idRegion", idRegion);
            request.setParameter("comuna", comuna);
            request.setParameter("nombreLocal", nombreLocal);
            return request;
        })).andExpect(status().isBadGateway());
    }

    private List<FarmaciaResponseResource> getFarmaciaResponseResource() {
        List<FarmaciaResponseResource> farmaciaResponseResource = new ArrayList<>();
        FarmaciaResponseResource messageHomeResponseDTo = FarmaciaResponseResource.builder().direccion(direccion).errorCode(errorCode)
                .errorDesc(errorDesc).nombreLocal(nombreLocal).telefono(telefono).latitud(latitud).longitud(longitud).build();

        farmaciaResponseResource.add(messageHomeResponseDTo);
        return farmaciaResponseResource;
    }

}
