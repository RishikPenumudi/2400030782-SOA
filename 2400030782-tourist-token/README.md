# 2400030782 Tourist Token Validation

Simple Spring Boot REST API with PostgreSQL and custom token validation.

## Structure
- `TouristTokenValidationApplication.java` — main class
- `TouristController.java` — API endpoints, token check, and simple model
- `application.properties` — database settings
- `database.sql` — database/table setup
- `pom.xml` — Maven dependencies

## Run in STS
1. Import as File → Import → Maven → Existing Maven Projects.
2. Create `touristdb` in PostgreSQL and run the table statement in `database.sql` while connected to it.
3. Run `TouristTokenValidationApplication.java` as Spring Boot App.

## API
Add header `Token: valid123` to `/tourists` requests.

POST `http://localhost:8080/tourists`
JSON: `{"name":"Rishik","location":"Vijayawada","country":"India"}`

GET `http://localhost:8080/tourists`
PUT `http://localhost:8080/tourists/1`
DELETE `http://localhost:8080/tourists/1`

Missing/invalid token returns HTTP 401. Demo token only; do not use for production.
