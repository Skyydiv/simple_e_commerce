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

| Package | Contains | Example |
|---|---|---|
| `config` | Classes that configure Spring behavior | Database config, CORS settings, Security config |
| `controller` | Classes that receive HTTP requests and return responses | `ProductController`, `UserController` |
| `service` | Classes that contain business logic | Stock check before order, price calculation |
| `repository` | Interfaces that talk to the database | `findByEmail()`, `findByCategory()` |
| `entity` | Classes that map to database tables | `User`, `Product`, `Order` |
| `dto` | Objects that carry data between layers — never expose raw entities | `UserRegisterRequest`, `ProductResponse` |
| `security` | JWT logic, auth filters, password handling | `JwtUtil`, `JwtAuthFilter` |
| `util` | Small reusable helpers | Date formatter, price calculator |

[//]: # (| `exception` | Custom exceptions and the global error handler | `ResourceNotFoundException`, `GlobalExceptionHandler` |)

### In term of layers
- `presentation` : controller + dto
- `business` : service 
- `data` : repository + entity
- `other` : config, security, util

### Idea of a information flow
```Markdown
HTTP Request
│
▼
┌─────────────────────────────────────────────┐
│  PRESENTATION   ProductController           │
│                 - receives GET /products/1  │
│                 - calls productService      │
└──────────────────────┬──────────────────────┘
│ calls getProductById(1)
▼
┌─────────────────────────────────────────────┐
│  BUSINESS       ProductService              │
│                 - checks product is active  │
│                 - converts entity → DTO     │
│                 - calls productRepository   │
└──────────────────────┬──────────────────────┘
│ calls findById(1)
▼
┌─────────────────────────────────────────────┐
│  DATA           ProductRepository           │
│                 - generates SQL query       │
│                 - returns Product entity    │
└──────────────────────┬──────────────────────┘
│ SELECT * FROM products WHERE id=1
▼
┌─────────────────────────────────────────────┐
│  DATABASE       PostgreSQL                  │
│                 - returns raw data          │
└──────────────────────┬──────────────────────┘
│ Product entity
▼
ProductService
converts to ProductResponse DTO
│
▼
ProductController
returns HTTP 200 + JSON body
```