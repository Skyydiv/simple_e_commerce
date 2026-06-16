# Project choices

## Convention
### Naming conventions
#### Database names
- underscore separated : `user_id`
- for attributes susceptible to have the same name in different tables: `table_name`


## Modeling choices
### Database
#### Cart
- We want to keep the cart between sessions
- When an item is added to the cart, it is reserved for some time
  - when stock modified reset expires time -> field `expires_at`
  - need max time to not reserve infinitly -> `created_at` + now + reservation time
  - need max quantity to reserve


## Technologies
### Database
- PostgreSQL

### Frontend
- Angular

### Backend
- java Spring


## Package structure

| Package      | Contains | Example                                        |
|--------------|---|------------------------------------------------|
| `config`     | Classes that configure Spring behavior | Database config, CORS settings, Security config |
| `controller` | Classes that receive HTTP requests and return responses | `ProductController`, `UserController`          |
| `service`    | Classes that contain business logic | Stock check before order, price calculation    |
| `repository` | Interfaces that talk to the database | `findByEmail()`, `findByCategory()`            |
| `entity`     | Classes that map to database tables | `User`, `Product`, `Order`                     |
| `dao`        | Interface that encapsulate the acces to the data and manage CRUD operations | `UserDAO`, `UserDaoImpl`                       |
| `security`   | JWT logic, auth filters, password handling | `JwtUtil`, `JwtAuthFilter`                     |
| `util`       | Small reusable helpers | Date formatter, price calculator               |

[//]: # (| `exception` | Custom exceptions and the global error handler | `ResourceNotFoundException`, `GlobalExceptionHandler` |)

### In term of layers
- `presentation` : controller (peu-être pas considérer comme présentation mais plus lien entre présentation et business)
- `business` : service 
- `data` : repository + entity, dto
- `other` : config, security, util

### Idea of a information flow
```Markdown
HTTP Request
│       ^
▼       | HTTP 200 + JSON body 
┌─────────────────────────────────────────────┐
│  PRESENTATION   ProductController           │
│                 - receives GET /products/1  │
│                 - calls productService      │
└──────────────────────┬──────────────────────┘
│ calls getProductById(1)                   ^
▼                                           | ProductResponse DTO
┌─────────────────────────────────────────────┐
│  BUSINESS       ProductService              │
│                 - checks product is active  │
│                 - converts entity → DTO     │
│                 - calls productRepository   │
└──────────────────────┬──────────────────────┘
│ calls findById(1)                         ^
▼                                           | Product entity
┌─────────────────────────────────────────────┐
│  DATA           ProductRepository           │
│                 - generates SQL query       │
│                 - returns Product entity    │
└──────────────────────┬──────────────────────┘
│ SELECT * FROM products WHERE id=1         ^
▼                                           | raw data
┌─────────────────────────────────────────────┐
│  DATABASE       PostgreSQL                  │
│                 - returns raw data          │
└──────────────────────┬──────────────────────┘
```
