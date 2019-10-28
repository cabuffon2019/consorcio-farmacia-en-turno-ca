
# Spring Boot Rest Api RestFull de Farmacia en Turno Publico

Tecnologias usadas
  <li>Spring 5
  <li>Spring Boot 2 
  <li>restTemplate
  <li>Spring Boot DevTools 
  <li>Project Lombok
  <li>WebJars
  <li>cucumber-junit
  <li>mockito
  <li>java 8
 

### Servicio que retorna las farmacias que se encuentran de turno de acuerdo a los siguientes filtros: Comuna y Nombre de local.

Por problemas con la url https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion se crea la misma estructura de array json desde un mock desplegado con Mockoon con la siguiente información:
  <li>url: http://localhost:8099/mockito/api/region
  <li>HttpMethod: GET
    
### Información del proyecto 

<li>En archivo de propiedades del proyecto se define la Url a consumir (resource/application.properties)
<li>La infomación que contendra el mock esta definida en el archivo /data.json  



 
