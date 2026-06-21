

## H2 database
In RAM SQL database used for testing and learning.
No persistent data.
No installation.
Each test is run on a new database, independently.

## Mockito
Testing library to create mock objects (fake versions of classes/dependencies) to test a class in isolation.

### Main Concepts to Remember:
- Mock → fake dependency
- when(...).thenReturn(...) → define behavior
- verify(...) → check a certain method was called on a mocked object with the correct arguments
- @Mock → create mock automatically
- @InjectMocks → inject mocks into tested class -> created at each test
- Matchers (any(), anyLong(), etc.) → flexible arguments


## Lombok 
Generate boilerplate code like getters, setters, constructors...
@AllArgsConstructor : create a constructor with all fields passed as arguments.
@Builer: Can use the builder pattern to create objects.