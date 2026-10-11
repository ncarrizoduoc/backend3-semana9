# Proyecto Spring Batch - Banco XYZ
El objetivo de este proyecto es crear un entorno **Backend** capaz de realizar operaciones sobre un conjunto de datos procesados por **Spring Batch**. El proyecto está conformado por las siguientes aplicaciones:
- El microservicio **ms-banco** procesa transacciones bancarias y genera estados de cuenta.
- El microservicio **ms-cuentas** permite obtener información de cuentas bancarias y gestionarlas.
- El microservicio **ms_correo** recibe notificaciones de transacciones completadas e imprime la información de cada transacción en consola.
- Se utiliza **Config Server** para configurar los microservicios de manera centralizada a través de archivos de configuración.
- Se utiliza **Eureka Server** para registrar el microservicio de banco, para facilitar su comunicación con otras aplicaciones más adelante.
- Se crea un **Auth Server** para gestionar la conexión por OAuth2, a través de GitHub, generando JWTs que deberán ser usados en solicitudes HTTP dirigidas al microservicio banco. 
- Se implementan también **BFFs** para entornos web, móvil y cajeros automáticos. Los BFFs presentan diferenciación en funcionalidades y detalle en respuestas.

## Requisitos previos
- **Java 21**: Asegúrate de tener instalado JDK 21.
- **Maven 3.9.x** o superior: Para compilar y ejecutar el proyecto.

## Tecnologías utilizadas
- **Java 21**
- **Spring Boot**
- **Maven**

## Estructura del proyecto
El proyecto se divide en dos subcarpetas: `batch` y `backend`.

La carpeta `batch` contiene la aplicación de Spring Batch que procesará los datos bancarios contenidos en archivos CSV y guardará los datos resultantes en tablas en base de datos (para esta implementación se utiliza una base de datos local MySQL).

La carpeta `backend` incluye nueve sub-proyectos, cada uno corresponde a una aplicación diferente.
Cada aplicación tiene su propio `Dockerfile`, los cuales proveen instrucciones para la construcción de las imágenes de su respectiva aplicación.
El directorio raíz contiene un archivo `docker-compose.yml`.

# Como utilizar
En primer lugar, se debe ejecutar el proceso `Batch` para procesar los datos y poblar las tablas en base de datos. Tras descargar el proyecto, debe modifcar los parámetros de conexión a la base de datos para ajustarlos a su base de datos. Luego, puede ejecutar la aplicación usando un IDE (como Visual Studio Code) y ejecutando la clase principal `BancoApplication`.

Dentro de la carpeta backend, se utiliza un archivo `docker-compose.yml` para desplegar las aplicaciones en contenedores Docker (también despliega **ActiveMQ** usando su imagen).

Antes de ejecutar `docker-compose.yml` se debe crear un archivo `.env` en la carpeta `./auth_server` que contenga las variables de entorno **GITHUB_CLIENT_ID** y **GITHUB_CLIENT_SECRET**. Estas variables serán usadas por el **Auth Server** para conectarse a la aplicación OAuth en GitHub y solicitar acceso al usuario. Las variables anteriores deben obtenerse desde la aplicación OAuth creada en GitHub.

También se deben modificar los parámetros de conexión de **ms-banco** y **ms-cuentas** a la base de datos en el **Config Server** para ajustarla a la base de datos de quien ejecute este proyecto. 

Con el archivo `.env` creado para el **Auth Server**, se debe ejecutar el archivo `docker-compose.yml` usando el comando: `docker-compose up -d --build`