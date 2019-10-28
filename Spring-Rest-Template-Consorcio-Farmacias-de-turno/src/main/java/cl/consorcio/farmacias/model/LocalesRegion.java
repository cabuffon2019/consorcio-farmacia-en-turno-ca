package cl.consorcio.farmacias.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalesRegion {

    @JsonProperty("fecha")
    private String fecha;
    @JsonProperty("local_id")
    private String local_id;
    @JsonProperty("local_nombre")
    private String local_nombre;
    @JsonProperty("comuna_nombre")
    private String comuna_nombre;
    @JsonProperty("localidad_nombre")
    private String localidad_nombre;
    @JsonProperty("local_direccion")
    private String local_direccion;
    @JsonProperty("funcionamiento_hora_apertura")
    private String funcionamiento_hora_apertura;
    @JsonProperty("funcionamiento_hora_cierre")
    private String funcionamiento_hora_cierre;
    @JsonProperty("local_telefono")
    private String local_telefono;
    @JsonProperty("local_lat")
    private String local_lat;
    @JsonProperty("local_lng")
    private String local_lng;
    @JsonProperty("funcionamiento_dia")
    private String funcionamiento_dia;
    @JsonProperty("fk_region")
    private String fk_region;
    @JsonProperty("fk_comuna")
    private String fk_comuna;

}
