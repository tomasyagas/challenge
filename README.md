# Readme

### Acciona backend challenge
Comentarios respecto a la solución desarrollada:

* Todos los parametros que se solicitan como configurables, se configuran en resources/application.properties
* Los parametros de configuración para la conexión con Twitter están en resources/twitter4j.properties
* Para simplificar la solución utilicé un solo modelo de entidades que actúa como DTO y DAO, creo que por la complejidad era suficiente
* Para activar la recepción de tweets también hay un parametro configurable "twitter.stream.enabled" y su ejecución se lanza al terminar de levantar la app
* La lógica al recibir los streams de tweets es evaluar si corresponde persistirlos según las condiciones configuradas, y transformarlos a DAO para persistir los tweets en una tabla y los hashtags en otra. Elegí que fueran dos tablas distintas ya que las consultas requeridas se dividen por entidad y no se relacionan entre sí.
* El caso puntual de los hashtags analicé opciones de persistencia y elegí grabarlos con un atributo que indique su cantidad de operaciones. Con las ventajas de que la consulta de hashtags más utilizados es muy simple y poco costosa para la bd y que la cantidad de registros es menor en la tabla que si grabara todos los hashtags (con repetidos). Teniendo en cuenta la desventeja de mi opción: la operación de grabado es más costosa que un simple INSERT, ya que evalúa primero si existe o no el hashtag y en base a esa decisión hace un INSERT o un UPDATE.

Sobre las tecnologías utilizadas:

* Utilicé Springboot Web + JPA + lombok + swagger para [documentar la API](http://localhost:8080/swagger-ui.html)
* H2 como base de datos en memoria embebida con su [consola para consultas](http://localhost:8080/h2)
* twitter4j según la recomendación para la integración con Twitter
* los controllers son API REST JSON
* tests unitarios con junit
