# Swagger
http://localhost:9999/mytutorial/swagger-ui.html

# Database
This project use H2 database integrated. (Added as dependency)

You might also find it helpful to dig around in the database. Because you’re using H2 as your embedded database, and because you have Spring Boot DevTools in
place, you should be able to point your browser to ![H2 Console](http://localhost:9999/mytutorial/h2-console) to see the H2 Console. The default credentials should get you in, although you’ll
need to be sure that the JDBC URL field is set to jdbc:h2:mem:testdb . 