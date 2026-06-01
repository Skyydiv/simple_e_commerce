# SQL Tutorial

## keys
### primary key
- use auto increment for id or ```nextval(...)?```?




## DDL
### create table

```sql
CREATE TABLE table_name (
    column_name data_type [constraints],
    column_name data_type [constraints],
    ...
);
```

---

## Choosing an ID column

**`SERIAL` (classic, simple)**
Auto-incrementing integers. `SERIAL` gives you a 32-bit int (up to ~2 billion rows)

```sql
id SERIAL PRIMARY KEY
id BIGSERIAL PRIMARY KEY
```

**`GENERATED ALWAYS AS IDENTITY` (modern standard, preferred)**
The SQL-standard replacement for `SERIAL`, introduced in PostgreSQL 10. Cleaner and more explicit.

```sql
id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY
```

> **Rule of thumb**: use `BIGINT GENERATED ALWAYS AS IDENTITY` for most apps.

---

## Common data types

| Category | Type | Use for |
|---|---|---|
| Integer | `SMALLINT`, `INT`, `BIGINT` | Whole numbers |
| Decimal | `NUMERIC(p, s)`, `REAL`, `FLOAT` | Money → `NUMERIC`; stats → `FLOAT` |
| Text | `TEXT`, `VARCHAR(n)` | Prefer `TEXT` in Postgres — no performance penalty |
| Boolean | `BOOLEAN` | `TRUE` / `FALSE` |
| Date/Time | `DATE`, `TIME`, `TIMESTAMP`, `TIMESTAMPTZ` | Always prefer `TIMESTAMPTZ` (stores timezone) |
| JSON | `JSON`, `JSONB` | `JSONB` is indexed and faster |
| Array | `INT[]`, `TEXT[]` | Native arrays |
| Enum | `CREATE TYPE mood AS ENUM (...)` | Fixed value sets |

---

## A complete, realistic example

```sql
-- An enum type defined separately
CREATE TYPE order_status AS ENUM ('pending', 'paid', 'shipped', 'cancelled');

-- Users table
CREATE TABLE users (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email       TEXT NOT NULL UNIQUE,
    username    TEXT NOT NULL UNIQUE,
    full_name   TEXT,
    is_active   BOOLEAN NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Products table
CREATE TABLE products (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        TEXT NOT NULL,
    price       NUMERIC(10, 2) NOT NULL CHECK (price > 0),
    stock       INT NOT NULL DEFAULT 0 CHECK (stock >= 0)
);

-- Orders table — references both users and products
CREATE TABLE orders (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
    product_id  BIGINT NOT NULL REFERENCES products(id) ON DELETE RESTRICT,
    quantity    INT NOT NULL CHECK (quantity > 0),
    status      order_status NOT NULL DEFAULT 'pending',
    ordered_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
```


---

## Key things to remember

**`ON DELETE` behavior for foreign keys** — what happens to child rows when the parent is deleted:
- `RESTRICT` / `NO ACTION` — block the deletion (safest, default)
- `CASCADE` — delete child rows automatically
- `SET NULL` — set the foreign key column to `NULL`

**`TEXT` vs `VARCHAR(n)`** — in PostgreSQL, `TEXT` and `VARCHAR` have identical performance. Use `VARCHAR(n)` only when you genuinely need a hard character limit (e.g. a 2-letter country code). Otherwise, just use `TEXT`.

**`TIMESTAMPTZ` over `TIMESTAMP`** — always store timestamps with timezone info. It avoids nasty bugs when your app or server changes timezone.

**Modifying a table after creation** — use `ALTER TABLE`:
```sql
ALTER TABLE users ADD COLUMN phone TEXT;
ALTER TABLE users DROP COLUMN phone;
ALTER TABLE users ALTER COLUMN full_name SET NOT NULL;
```