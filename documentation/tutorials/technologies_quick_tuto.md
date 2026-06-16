

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
- verify(...) → check interactions
- @Mock → create mock automatically
- @InjectMocks → inject mocks into tested class
- Matchers (any(), anyLong(), etc.) → flexible arguments


