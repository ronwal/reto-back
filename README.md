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
git clone https://github.com/ronwal/reto-back.git
cd reto-back
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

## 📌 **5. probar los servicios**
Curls de los servicios:
* Crear Cliente:
``` sh
curl --location 'http://127.0.0.1:8081/reto-persona/clientes' \
--header 'Content-Type: application/json' \
--data '{
"nombre": "Carlos García",
"clienteId": "C456",
"password": "abcd",
"estado": true
}'
```
* Crear Cuenta:
``` sh
curl --location 'http://127.0.0.1:8082/reto-cuenta/cuentas' \
--header 'Content-Type: application/json' \
--header 'X-API-Key: {{token}}' \
--data '{
  "numeroCuenta": "123456",
  "tipoCuenta": "Ahorro",
  "saldoInicial": 1000.0,
  "estado": true,
  "clienteId": 1
}'
```
* Registra Movimiento:
``` sh
curl --location 'http://127.0.0.1:8082/reto-cuenta/movimientos/1' \
--header 'Content-Type: application/json' \
--header 'X-API-Key: {{token}}' \
--data '{
    "tipoMovimiento": "Depósito",
    "valor": 3.0
}'
```


## 📌 **6. bajar los servicios**
Para desplegar los servicios con Docker, usa:

``` sh
docker-compose down --rmi all --volumes --remove-orphans
```
