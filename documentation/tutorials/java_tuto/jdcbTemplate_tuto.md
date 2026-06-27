# JdbcTemplate — Quick Recap

`JdbcTemplate` is a Spring class that simplifies JDBC operations.

Instead of manually handling:

* Connections
* Statements
* ResultSets
* Resource closing

you write:

```java
jdbcTemplate.query(...);
jdbcTemplate.update(...);
```

and Spring handles the boilerplate.

---

# Main Methods

## 1. `query()`

Use when the SQL returns **multiple rows**.

```java
String sql = "SELECT * FROM users";

List<User> users = jdbcTemplate.query(
    sql,
    (rs, rowNum) -> new User(
        rs.getLong("id"),
        rs.getString("name")
    )
);
```

### Returns

```java
List<User>
```

### Use for

* Find all users
* Search products
* List orders

---

## 2. `queryForObject()`

Use when the SQL should return **exactly one row**.

```java
String sql = "SELECT * FROM users WHERE id = ?";

User user = jdbcTemplate.queryForObject(
    sql,
    (rs, rowNum) -> new User(
        rs.getLong("id"),
        rs.getString("name")
    ),
    id
);
```

### Returns

```java
User
```

### Use for

* Find by ID
* Find by email

### Warning

Throws exception if:

* 0 rows
* more than 1 row

---

## 3. `queryForList()`

Use when you need a list of simple values.

```java
List<String> names =
    jdbcTemplate.queryForList(
        "SELECT name FROM users",
        String.class
    );
```

### Returns

```java
List<String>
```

### Use for

* List of names
* List of IDs

---

## 5. `update()`

Use for:

* INSERT
* UPDATE
* DELETE

```java
jdbcTemplate.update(
    "UPDATE users SET name = ? WHERE id = ?",
    "John",
    1L
);
```

### Returns

```java
int
```

Number of affected rows.

---

# When to Use `update()`

## Insert

```java
jdbcTemplate.update(
    "INSERT INTO users(name,email) VALUES (?,?)",
    name,
    email
);
```

---

## Update

```java
jdbcTemplate.update(
    "UPDATE users SET name=? WHERE id=?",
    name,
    id
);
```

---

## Delete

```java
jdbcTemplate.update(
    "DELETE FROM users WHERE id=?",
    id
);
```

---

# RowMapper

A `RowMapper` converts a database row into a Java object.

Example:

```java
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum)
            throws SQLException {

        return new User(
            rs.getLong("id"),
            rs.getString("name")
        );
    }
}
```

Usage:

```java
jdbcTemplate.query(
    sql,
    new UserRowMapper()
);
```

---

# Prepared Statement Parameters

Never concatenate values into SQL.

Bad:

```java
String sql =
    "SELECT * FROM users WHERE id=" + id;
```

Good:

```java
jdbcTemplate.queryForObject(
    "SELECT * FROM users WHERE id=?",
    mapper,
    id
);
```

Spring creates a prepared statement automatically.

---


# Which Method Should I Use?

| Situation                 | Method                   |
| ------------------------- | ------------------------ |
| SELECT many rows          | `query()`                |
| SELECT one row            | `queryForObject()`       |
| SELECT one column as list | `queryForList()`         |
| INSERT                    | `update()`               |
| UPDATE                    | `update()`               |
| DELETE                    | `update()`               |
| Need generated ID         | `update()` + `KeyHolder` |

---

# Typical Repository Example

```java
@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(Long id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM users WHERE id=?",
            new UserRowMapper(),
            id
        );
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM users",
            new UserRowMapper()
        );
    }

    public void save(User user) {
        jdbcTemplate.update(
            "INSERT INTO users(name,email) VALUES (?,?)",
            user.getName(),
            user.getEmail()
        );
    }
}
```

