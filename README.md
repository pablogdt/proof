# Prueba W2M

## Puntos extras hechos

* Utilizar alguna librería que facilite el mantenimiento de los scripts DDL de base de datos. -> **ORM: JPA**
* Implementar una anotación personalizada que sirva para medir cuánto tarda en ejecutarse.
una petición. Se podría anotar alguno o todos los métodos de la API con esa anotación.
Funcionaría de forma similar al @Timed de Spring, pero imprimiendo la duración en un log. -> **AOP, Chronometer annotation**
* Gestión centralizada de excepciones. -> **AppExceptionHandler**
* Test de integración. -> **it/java/es.pablogdt.w2mproof.W2mproofApplicationTests.java**
* Presentar la aplicación dockerizada. -> **Dockerfile en la raiz del proyecto**
* Poder cachear peticiones. -> **Spring cache & Caffeine**
* Documentación de la API. -> **OpenAPI & Swagger**
* Seguridad del API. -> **Spring Security**
