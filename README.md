# Voting Panel

Simple REST application built with **Spring Boot** that allows registering votes in multiple elections.

The application supports managing voters, creating elections with choices, and casting votes with the rule that a voter can vote **only once per election**.

## Features

### Voters
- Add new voters
- Block / unblock voters
- Find voter by personal number

### Elections
- Create election instances (e.g. *"Mayor Election 2025"*)
- Define voting options (candidates)
- Show election results

### Voting


# Tech Stack

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Flyway** (database migrations)
- **Testcontainers**
- **REST Assured** (API tests)
- **Swagger** 
---

# Running the application

Before launching the application, start Docker Compose, which starts the required PostgreSQL database.
