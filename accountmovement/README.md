# Microservicio de Cuenta - Movimiento (accountmovement)

Este microservicio ha sido desarrollado utilizando una arquitectura Hexagonal sin capa de aplicacion con Spring Boot. 

El modelo de dominio consta de dos entidades principales: Cuenta (Account) y Movimiento(Movement).

## Requerimientos

Para ejecutar este microservicio, se requiere una base de datos MySQL. Se sugiere utilizar un contenedor Docker para facilitar la configuración. Puedes iniciar un contenedor MySQL con la siguiente línea de comando:

docker run --detach --env MYSQL_ROOT_PASSWORD=challengepassword --env MYSQL_USER=devsu_user --env MYSQL_PASSWORD=challengepassword --env MYSQL_DATABASE=devsu_challenge --name mysql --publish 3306:3306 mysql:8-oracle
## Configuración

La capa de persistencia utiliza Spring Data JPA para interactuar con la base de datos. Se han creado repositorios para cada una de las entidades del modelo de dominio.

## Modelo de Dominio

![Diagrama de dominio]
+-----------------+         +----------------+
|   account       |         |  movement      |
+-----------------+         +----------------+
| id (PK)         |         | id (PK)        |
| account_balance |         | account_number |
| account_number  |         | account_type   |
| account_type    |         | date_movement  |
| id_client       |         | final_balance  |
| identification  |         | initial_balance|
| name            |         | movement_detail|
| status          |         | status         |
+-----------------+         +----------------+

## Despliegue
Para levantar el microservicio contenerizado se deben ejecutar los siguientes comandos:

docker-compose -p devsu_challenge up --build -d
Este comando crea un proyecto docker donde levanta 2 contenedores 1 con el mysql y otro con el microservicio accountmovement, para generar la persistencia del servicio

Tenga en cuenta que en la logica de negocio para las Cuentas, el microservicio accountmovement consume el microservicio clientperson el cual esta expuesto por el puerto 4001

Importante!!!!
CLIENT_API_URL: http://apiClientPerson

En la linea 31 del docker-compose.yaml que esta en la ruta accountmovement/docker-compose.yaml se parametriza
la url del apiClientPerson Esta es la Url del servicio de clientperson el cual se encuentra en el puerto 4001 se debe de cambiar a (localhost si el servicio clientperson no esta en el mismo proyecto del contenedor de accountmovement) o apiClientPerson(si esta en el mismo proyecto)
      

 