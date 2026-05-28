# Docker explanations 

## Creating and running the database
### Commands
1. Create a docker-compose.yml file at the root of your project
2. Create the .env file
3. Manage the container :
    - Start it: ```docker compose up -d``` : -d runs in the background.
    - Check it's running: ```docker compose ps```
    - Stop it (without losing data): ```docker compose stop```

### Configuration
A few things to note about the config above:
```yaml
    services:
    db:
        image: postgres:18.4
        container_name: simple_e_commerce_postgres
        environment:
            POSTGRES_USER: ${POSTGRES_USER}
            POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
            POSTGRES_DB: ${POSTGRES_DB}
        ports:
            - "${POSTGRES_PORT:-5432}:5432" # attention peut être erreur sur les appostrophe 
        volumes:
            - simple_e_commerce_pg_data:/var/lib/postgresql
        restart: unless-stopped

    volumes:
    simple_e_commerce_pg_data:
```

- restart: unless-stopped the container automatically restarts if your machine reboots.

- pg_data: formally declares named volume pg_data so Docker manages it.
Never hardcode real passwords like this in production. For a real project, move secrets to a .env file and reference them as ${POSTGRES_PASSWORD}, then add .env to your .gitignore.

- ${POSTGRES_USER} : use environment variables for configuration

- ports : HOST_PORT:CONTAINER_PORT : 2 different apps can't have same host port

- ${POSTGRES_PORT:-5432} means:
Read the POSTGRES_PORT variable from your .env file
If it exists → use that value
If it doesn't exist → fall back to 5432

- volume : where data stored. Managed by docker.

### Verifying the database is created and accessible
- container running : ```docker ps```
- connecting directly to database : ```docker exec -it simple_e_commerce_postgres psql -U simple_e_commerce_user -d simple_e_commerce_db```
   execute the command in the container 'simple_e_commerce_postgres'
- check the port is accessible :  
  ```docker compose ps``` : `0.0.0.0:5432->5432/tcp`
  ```bash psql -h localhost -p 5432 -U simple_e_commerce_user -d simple_e_commerce_db```