# Proyecto Microservicios - Spring Boot & Docker

Este proyecto contiene dos microservicios desarrollados en **Spring Boot** que gestionan clientes, cuentas y movimientos bancarios. Los servicios están configurados para ejecutarse en contenedores Docker y utilizan **MySQL** como base de datos.

---

## 📌 **1. Requisitos Previos**
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- **Java 17** (JDK 17)
- **Maven 3.x**
- **Docker** y **Docker Compose**
- **Git** (opcional, para clonar el repositorio)

---

## 📌 **2. Clonar el Proyecto**
Si aún no tienes el código en tu máquina, clónalo con:

```sh
git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio
```

## 📌 **3. Compilar el proyecto**
```shell
mvn clean package -DskipTests
```

## 📌 **4. Construir y Levantar los Servicios**
Para desplegar los servicios con Docker, usa:

``` sh
docker-compose up --build -d
```

## 📌 **5. bajar los servicios**
Para desplegar los servicios con Docker, usa:

``` sh
docker-compose down --rmi all --volumes --remove-orphans
```
