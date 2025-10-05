# Prerequisites

- Java 21

- Maven

- `tailwindcss` standalone binary added to path (link: [https://github.com/tailwindlabs/tailwindcss/releases/](https://github.com/tailwindlabs/tailwindcss/releases/))

# Running:

1. `git clone ssh://git@pray.ddns.net:6122/UNEB/engsoft.git`

2. `cp env.example .env`

3. Fill in `.env` data

4. `mvn spring-boot:run`

5. App will be listening on port 8080

# Dependencies

- Spring dotenv: for loading environment variables: username and password

- SQLite JDBC: for connecting to SQLite

- Spring Session JDBC: for database table cookie storage and session persistence

- Spring JDBC: for JDBC API

- Spring Security: for authentication and authorization

    - Automatically added: Thymeleaf extras for Spring Security (variables for session data in templates)

- Thymeleaf: server HTML templating

- Validation API

- Spring Web

- Flyway: Migrations

- Maven exec plugin: for running Tailwind as prerequisite task
