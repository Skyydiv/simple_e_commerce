# Spring component tutorial

## DTO
### Principe
A DTO is related to an entity in the database.
It knows the structure of the entity table.
Is responsible to abstract the entity from the database and map it into java objects for the application.
The database changes only the DTO change.
It is the communication between the application layer and the persistence layer (database).

### Implementation
- define interface dao for later implementation changes
- define DTO implementation -> need way talk to database
- need do integration test 