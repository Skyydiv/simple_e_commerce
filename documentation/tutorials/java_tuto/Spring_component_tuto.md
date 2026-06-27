# Spring component tutorial

## DAO (Data Access Object)
### Principe
A DAO talk to the database to make the translation between database entities and java objects. It abstracts the database.
The database changes only the DTO change.
It is the communication between the application layer and the persistence layer (database).

### Implementation
- define interface dao for later implementation changes, empty
- define DAO implementation -> need way talk to database, jdbc template
- need do integration test -> h2 database configuration, default test passes -> database connected
- need unit test -> package test doa, test each functionality

- Methods return optionals rappers for null values -> more type safe

- implementing :
  - create objects in database
  - read 1 object from database
  - read many 
  - 