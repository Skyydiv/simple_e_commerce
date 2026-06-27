

## Junit 
### Basic Structure

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @BeforeEach
    void setUp() {
        // executed before every test. EX: Initializing mock objects
    }

    @AfterEach
    void tearDown() {
        // executed after every test
    }

    @BeforeAll
    static void init() {
        //Runs once for the whole class. Ex: initializing external resources.
    }
    
    @AfterAll
    static void cleanup() {
        //Runs once for the whole class. Ex: Freeing external resources...
    }

    // Helper methods
    private User createUser() {
        return new User("John");
    }

    @Test
    void shouldCreateUser() {
        // Arrange
        // Act
        // Assert
        
        //assertTrue(condition);  boolean checks
        //assertEquals(expected, actual, message);  equality checks
        //assertNotNull(value);
    }
}
```

---

### Useful Mockito + JUnit Annotations

```java
@Mock
UserRepository repository;

@InjectMocks
UserService service;
```

and

```java
@ExtendWith(MockitoExtension.class)
```

to initialize Mockito automatically.



## SpringBoot integration test
Integration test to see if all parts are working together.

### @SpringBootTest
- How multiple Spring components work together (services, repositories, controllers, configuration, etc.).
- Starts the full Spring context.
- Beans are injected with @Autowired.
```java
    @SpringBootTest
    class UserServiceTest {
        //...
    }
```