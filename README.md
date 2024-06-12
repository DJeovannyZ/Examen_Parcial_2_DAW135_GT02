> [!NOTE]
> _La resolución del parcial se realizó utilizando el IDE NetBeans._

# Parcial 2 - DAW135

Este proyecto tiene como finalidad desarrollar un sistema de gestión de base de datos orientado a la administración integral de alumnos y la gestión de inscripciones de materias.

> Tabla de Contenido
> - [Integrantes](#integrantes)
> - [Datos del servidor](#datos-del-servidor)

## Integrantes

- Alfredo Alexander Lara Guerra (LG21028)
- Cristian Armando Navarro Aguilar (NA21005)
- Darwin Geovanny Zaldaña Ávila (ZA20003)

## Datos del Servidor

Para poder correr la aplicacion se necesitara crear un pull de conexion con los siguientes datos.

- **user:** postgres
- **password:** PD0xT2FaXZvBGcS_yepXcA
- **databaseName:** parcial2
- **serverName:** parcial2-daw135-14983.7tt.aws-us-east-1.cockroachlabs.cloud
- **portNumber:** 26257
- **UseSSL:** false

## Conexion por Docker
Alternativamente se puede crear un contenedor de docker en el cual se tiene toda la configuracion necesaria para levantar el servidor de payara con la aplicacion incluyendo el pool de conexiones y el datasource para la conexion con la base de datos.

Para Linux se puede ejecutar el script `startup.sh`:
```bash
chmod +x startup.sh; 
./startup.sh
```

Para Windows se puede ejecutar el script `startup.bat`:
```bash
./startup.bat
```


