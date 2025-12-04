APP_NAME=fx-deals-warehouse

# Build Java project
build:
	mvn clean install

# Run Spring Boot without Docker
run:
	mvn spring-boot:run

# Run only tests
test:
	mvn test

# Build and run with Docker Compose
up:
	docker-compose up --build -d

# Stop containers
down:
	docker-compose down

# Show logs from the spring boot container
logs:
	docker logs fx_deals_app -f

# Clean Maven + remove target folder
clean:
	mvn clean
