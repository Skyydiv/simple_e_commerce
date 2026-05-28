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