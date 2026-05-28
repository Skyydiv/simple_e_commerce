## What is `application.yml`?

It's the **central configuration file** for your Spring Boot app. It controls everything: database connection, server port, security, logging, and more. It lives at:

```
src/main/resources/application.yml
```

---

## The profiles setup (do this first)

You'll have 3 files total:

```
src/main/resources/
├── application.yml          ← shared config + activates a profile →  activates the "dev" profile
├── application-dev.yml      ← your local machine values → your real local values (never commit)
└── application-prod.yml     ← production values → reads everything from env variables (never commit)
```
2 different files to be able to run a dev environment and a production environment without changing the values.
dev : harcoder les valeurs
prod : utiliser variables d'environnement

**`application.yml`** — just activates the dev profile locally:
```yaml
spring:
  profiles:
    active: dev
```

When you deploy to production, you override this by passing an environment variable:
```bash
SPRING_PROFILES_ACTIVE=prod
```

---

## `application-dev.yml` — full config explained

Here is a complete file with every section you need for your e-commerce project, with every line explained:

```yaml
# ─── SERVER ───────────────────────────────────────────
server:
  port: 8080   # the port your API runs on locally
               # access it at http://localhost:8080

# ─── DATABASE ─────────────────────────────────────────
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce_db
    #         └─ driver    └─ host      └─ port └─ db name
    username: admin             # must match POSTGRES_USER in docker-compose
    password: mysecretpassword  # must match POSTGRES_PASSWORD in docker-compose
    driver-class-name: org.postgresql.Driver  # tells Spring which DB driver to use

  # ─── JPA / HIBERNATE ──────────────────────────────
  jpa:
    hibernate:
      ddl-auto: update
      # Options:
      # create       → drops and recreates all tables on every start (wipes data)
      # create-drop  → same as create but also drops on shutdown
      # update       → adds missing columns/tables, never deletes anything  ← use this in dev
      # validate     → checks schema matches entities but changes nothing   ← use this in prod
      # none         → does nothing at all
    show-sql: true          # prints every SQL query in your console
    properties:
      hibernate:
        format_sql: true    # makes printed SQL readable (indented)
        dialect: org.hibernate.dialect.PostgreSQLDialect  # tells Hibernate you're using PostgreSQL

# ─── LOGGING ──────────────────────────────────────────
logging:
  level:
    root: INFO                              # default log level for everything
    org.springframework.web: DEBUG          # shows incoming HTTP requests
    org.springframework.security: DEBUG     # shows security decisions (useful later)
    com.yourpackage.ecommerce: DEBUG        # your own code — shows everything
```

---

Once your `application-dev.yml` is created with the database values matching your Docker setup, start your Spring Boot app and check the console for the `HikariPool - Start completed` line — that confirms the database connection is working.
