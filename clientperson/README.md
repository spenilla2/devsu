# Microservicio de Cliente - Persona (clientperson)

Este microservicio ha sido desarrollado utilizando una arquitectura Hexagonal sin capa de aplicacion con Spring Boot. 

El modelo de dominio consta de dos entidades principales: Cliente(Client) y Persona(Person).

## Requerimientos

Para ejecutar este microservicio, se requiere una base de datos MySQL. Se sugiere utilizar un contenedor Docker para facilitar la configuración. Puedes iniciar un contenedor MySQL con la siguiente línea de comando:

docker run --detach --env MYSQL_ROOT_PASSWORD=challengepassword --env MYSQL_USER=devsu_user --env MYSQL_PASSWORD=challengepassword --env MYSQL_DATABASE=devsu_challenge --name mysql --publish 3306:3306 mysql:8-oracle
## Configuración

La capa de persistencia utiliza Spring Data JPA para interactuar con la base de datos. Se han creado repositorios para cada una de las entidades del modelo de dominio.

## Modelo de Dominio

![Diagrama de dominio]
+----------------+         +-------------+
|   person       |         |    client   |
+----------------+         +-------------+
| id (PK)        | <------ | id (PK, FK) |
| address        |         | client_id   |
| age            |         | password    |
| gender         |         | status      |
| identification |         +-------------+
| name           |
| phone          |
+----------------+

## Despliegue
Para levantar el microservicio contenerizado se deben ejecutar los siguientes comandos:

docker-compose -p devsu_challenge up --build -d
Este comando crea un proyecto docker donde levanta 2 contenedores 1 con el mysql y otro con el microservicio clienteperson, para generar la persistencia del servicio