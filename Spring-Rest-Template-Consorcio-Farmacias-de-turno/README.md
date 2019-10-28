
# Spring Boot Rest Api RestFull de Farmacia en Turno Publico

Tecnologias usadas
  <li>Spring 5
  <li>Spring Boot 2 
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
    
###En archivo de propiedades del proyecto se define la Uri    

### I used <a href="http://www.jsonschema2pojo.org/">jsonschema2pojo</a> for creating JSON to POJOs

<hr/>

### Index page 

<img src="https://image.ibb.co/enzoR6/Screen_Shot_2018_02_03_at_19_20_00.png">

## Lists page  
<img src="https://image.ibb.co/n9Jhm6/Screen_Shot_2018_02_03_at_19_20_52.png" height=" " width="">

## In this project, there are 4 tests according to the concept of Test Driven Development.
<img src="https://image.ibb.co/gPTib6/Screen_Shot_2018_02_04_at_01_16_10.png" width="">



[![CircleCI](https://circleci.com/gh/ramazan/Spring-Rest-Template-Example.svg?style=svg)](https://circleci.com/gh/ramazan/Spring-Rest-Template-Example)
## When I push this repo , Circle CI run these all test and they show above badge


 
