# Order Service

## Ansvar

Order Service är en mikrotjänst i BlackMarqet-systemet som ansvarar för hantering av order.
Tjänsten minskar lagersaldot via product-service, beräknar totalpriset och sparar ordern i databasen.
Orderbekräftelser skickas via RabbitMQ till email-service. Anrop från frontend går via gatewayn
och kräver JWT med rollen USER eller ADMIN.

## Tekniker

- Java 17
- Spring Boot v4
- Spring Security
- JWT
- Maven
- PostgreSQL
- RabbitMQ

## Databas

Tjänsten använder PostgreSQL.

## Miljövariabler

Miljövariabler måste vara satta för att tjänsten ska fungera korrekt.

- `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` – JDBC-adress och inloggning till PostgreSQL.
- `JWT_PUBLIC_URL` – Auth-services JWK-set, normalt `http://localhost:8080/auth/jwks`.
- `PRODUCT_SERVICE_URL` – Product-services adress, normalt `http://localhost:8081`.
- `ALLOWED_ORIGINS` – Tillåtna CORS-origins, kommaseparerade, normalt `http://localhost:5173`.
- `RABBITMQ_HOST`, `RABBITMQ_PORT` – RabbitMQ-serverns adress och port.
- `RABBITMQ_USERNAME`, `RABBITMQ_PASSWORD`, `RABBITMQ_VHOST` – Anslutningsuppgifter för RabbitMQ.
- `RABBITMQ_ORDER_CONFIRMATION_QUEUE` – Samma könamn som i email-service.

RabbitMQ är konfigurerat med TLS, så anslutningen behöver använda serverns TLS-port.

## Starta applikationen lokalt

Kräver Java 17 eller senare, Maven Wrapper samt tillgång till PostgreSQL och RabbitMQ.

1. Klona projektet.
2. Sätt miljövariablerna.
3. Starta auth-service och product-service för orderflödet.
4. Starta applikationen från projektmappen:

**macOS/Linux:**

```bash
./mvnw spring-boot:run
```

**Windows PowerShell:**

```powershell
.\mvnw.cmd spring-boot:run
```

Tjänsten är tillgänglig på `http://localhost:8082`.

För hela webbshoppen startas även email-service och gateway. Frontend startas i sitt
repository med `npm install` följt av `npm run dev`, med dess miljövariabler konfigurerade.

## Relaterade repositories

1. [Gateway](https://github.com/AnwarAsry/gateway-service)
2. [Auth Service](https://github.com/MartinGruter/auth-server-projekt2)
3. [Product Service](https://github.com/MartinGruter/product-service-projekt2)
4. [Email Service](https://github.com/MartinGruter/email-service-projekt2)
5. [Frontend](https://github.com/MartinGruter/micro-services)
