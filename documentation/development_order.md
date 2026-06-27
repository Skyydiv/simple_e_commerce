## Database design

## Project configuration

## DAO implementation

1 first complete DAO to learn, User.
Always make unit test for DAO, I make test-driven development. Test purpose is checking if jdbc template generates correct sql.
We do an integration test with an h2 database to see if functionalities are working with a real database. One by DAO

### Lists methods to implement
1. Optional<User> find(Long id) -> find One
2. findMany