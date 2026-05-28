## Essential PostgreSQL Command-Line Commands

### Connecting
```bash
psql -U username -d dbname          # Connect to a database
psql -U username -h host -p 5432 -d dbname  # Connect with host/port
psql -U username                    # Connect (will prompt for password)
\password username                  # Change a user's password
\q  or  \quit                       # Exit psql
```

### Navigating Databases & Tables
```sql
\l                  -- List all databases
\c dbname           -- Switch to a database
\dt                 -- List tables in current database
\dt schema.*        -- List tables in a specific schema
\dn                 -- List schemas
\d tablename        -- Describe a table (columns, types, constraints)
\d+ tablename       -- Describe with extra detail (storage, comments)
\di                 -- List indexes
\ds                 -- List sequences
\dv                 -- List views
```

### Users & Permissions
```sql
\du                             -- List users/roles
CREATE USER name WITH PASSWORD 'pw';
GRANT ALL PRIVILEGES ON DATABASE dbname TO username;
```

### Running Queries
```sql
SELECT * FROM tablename;
SELECT * FROM tablename LIMIT 10;
\x                  -- Toggle expanded/vertical output (great for wide tables)
\timing             -- Toggle query execution time display
\e                  -- Open last query in your $EDITOR
```

### Import / Export
```sql
\i /path/to/file.sql            -- Execute a SQL file
\copy table FROM 'file.csv' CSV HEADER   -- Import CSV
\copy table TO 'file.csv' CSV HEADER     -- Export CSV
```

```bash
# From the shell (not inside psql):
pg_dump -U user dbname > backup.sql         # Dump a database
pg_restore -U user -d dbname backup.dump    # Restore a dump
psql -U user -d dbname < backup.sql         # Restore a plain SQL dump
```

### Miscellaneous
```sql
\?                  -- Help for psql backslash commands
\h SELECT           -- SQL syntax help for a specific command
\conninfo           -- Show current connection info
\set                -- List psql variables
\! ls               -- Run a shell command without leaving psql
```

### A Few Handy Query Patterns
```sql
-- Find large tables
SELECT relname, pg_size_pretty(pg_total_relation_size(relid))
FROM pg_stat_user_tables ORDER BY pg_total_relation_size(relid) DESC;

-- List active connections
SELECT pid, usename, datname, state, query FROM pg_stat_activity;

-- Kill a query
SELECT pg_terminate_backend(pid);
```

**Tip:** Commands starting with `\` are psql meta-commands (no semicolon needed). Everything else is SQL and needs a `;` to execute.