# 📘 FX Deals Warehouse – Documentation

## 📌 Overview
**FX Deals Warehouse** is a backend application built with **Spring Boot 3** that processes, validates, and stores foreign exchange (FX) deal transactions.

The system provides:

- Robust REST APIs for managing deals
- Validation of incoming requests
- Duplicate detection (no deal imported twice)
- Persistent storage in PostgreSQL
- No rollback behavior (valid rows must always be saved)
- Containerized deployment via Docker

This project demonstrates strong backend engineering practices, showcasing:

- Clean layered architecture (**Controller → Service → Repository**)
- Java 17 best practices
- Object mapping with MapStruct
- Database modeling with PostgreSQL
- Docker-based deployment
- Unit testing with JUnit 5
- API documentation using Swagger/OpenAPI

## 🧩 Tech Stack

| Technology | Usage |
|------------|-------|
| Java 17 | Backend language |
| Spring Boot 3 | REST API development |
| Maven | Build & dependency management |
| PostgreSQL | Primary database |
| Docker & Docker Compose | Containerized deployment |
| MapStruct | DTO ↔ Entity mapping |
| JUnit 5 | Unit testing |
| Lombok | Reduces boilerplate |
| SLF4J / Logback | Logging |

## 📦 Installation & Setup

### 1️⃣ Clone the repository
```bash
git clone https://github.com/Mohsen-Elrhazi/fx-deals-warehouse.git
cd fx-deals-warehouse
2️⃣ Environment Configuration
A pre-filled .env.example file exists in the project. Create your real .env file using:

bash
Copier le code
cp .env.example .env
Your .env file contains:

env
Copier le code
# PostgreSQL Configuration
POSTGRES_DB=fx_deals_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=example_password

# PGAdmin Configuration
PGADMIN_EMAIL=admin@example.com
PGADMIN_PASSWORD=example_password

# Spring Boot Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/fx_deals_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=example_password
SPRING_JPA_HIBERNATE_DDL_AUTO=update
You may adjust credentials if needed.

🐳 Run with Docker (Recommended)
Start the full stack (Spring Boot + PostgreSQL + PGAdmin):

bash
Copier le code
docker-compose up --build -d
Stop containers:

bash
Copier le code
docker-compose down
▶️ Run Without Docker
Build the project:

bash
Copier le code
mvn clean install
Run the application:

bash
Copier le code
mvn spring-boot:run
📘 Swagger – API Documentation
Once the application is running, access the API documentation at: http://localhost:8080/swagger-ui/index.html

🔥 REST API Endpoints
📌 Deals
Method	Endpoint	Description
POST	/api/deals	Create a new FX deal
GET	/api/deals	Retrieve all deals
GET	/api/deals/{dealId}	Retrieve a deal by its unique dealId

📝 Example – Create Deal
Request:

http
Copier le code
POST http://localhost:8080/api/deals
Content-Type: application/json

{
  "dealId": "D004",
  "fromCurrencyIsoCode": "USD",
  "toCurrencyIsoCode": "EUR",
  "dealTimestamp": "2025-12-02T21:20:28",
  "dealAmount": 1000.50
}
Response (201 Created):

json
Copier le code
{
  "status": "success",
  "message": "Deal created successfully",
  "data": {
    "dealId": "D004",
    "fromCurrencyIsoCode": "USD",
    "toCurrencyIsoCode": "EUR",
    "dealTimestamp": "2025-12-02T21:20:28",
    "dealAmount": 1000.5
  }
}
🧪 Running Tests
Run all tests:

bash
Copier le code
mvn test
Test files are located in:

swift
Copier le code
src/test/java/com/fxdealswarehouse/
📂 Project Structure (Simplified)
bash
Copier le code
src/
├── main/java/com/fxdealswarehouse/
│   ├── controller/      → REST controllers
│   ├── service/         → Business logic
│   ├── repository/      → Database access
│   ├── model/           → Entity classes
│   ├── mapper/          → MapStruct mappers
│   ├── dto/             → Request & Response DTOs
│   └── exception/       → Custom exceptions and handlers
└── test/java/...         → Unit tests
🛠 Makefile Commands
This project includes a Makefile to simplify development operations:

bash
Copier le code
make build   # Build the project
make run     # Run Spring Boot locally
make up      # Start Docker containers
make down    # Stop Docker containers
make test    # Run unit tests
make logs    # Show application logs