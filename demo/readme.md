# Demo Spring Boot API

## 🚀 Requisitos
- Java 17+
- Maven 3.9+
- Oracle JDBC configurado en `application.properties`
- (Opcional) `oraclepki` si usas Wallet/TCPS

---

## ⚙️ Configuración rápida
```properties
spring.datasource.url=jdbc:oracle:thin:@//HOST:PORT/SERVICE
spring.datasource.username=USUARIO
spring.datasource.password=CLAVE
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
---

## ▶️ Ejecutar
```bash
# compilar y correr
mvn spring-boot:run

# o empaquetar y ejecutar jar
mvn -DskipTests package
java -jar target/demo-*.jar
```

---

## 📡 Endpoints (CRUD Película)

### Listar
```bash
curl -i http://localhost:8080/api/peliculas
```

### Obtener por ID
```bash
curl -i http://localhost:8080/api/peliculas/1
```

### Crear
```bash
curl -i -X POST http://localhost:8080/api/peliculas   -H "Content-Type: application/json"   -d '{
    "titulo": "Matrix",
    "director": "The Wachowskis",
    "genero": "Ciencia ficción",
    "anio": 1999,
    "sinopsis": "Un hacker descubre la realidad simulada."
  }'
```

### Eliminar
```bash
curl -i -X DELETE http://localhost:8080/api/peliculas/1
```

---
