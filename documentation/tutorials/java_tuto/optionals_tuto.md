## Java `Optional` – Compact Recap

`Optional<T>` is a container that either contains a value (`T`) or is empty. It helps avoid explicit `null` checks and makes the absence of a value explicit.

### Creating an `Optional`

| Method | When to use | Behavior if value is `null` |
|--------|-------------|-----------------------------|
| `Optional.of(value)` | Value is guaranteed non-null | Throws `NullPointerException` |
| `Optional.ofNullable(value)` | Value may be `null` | Returns `Optional.empty()` |
| `Optional.empty()` | No value | Creates an empty `Optional` |

> **If the value can be `null`, always use `Optional.ofNullable()`.**

---

### Common Methods

| Method | Description |
|--------|-------------|
| `isPresent()` | Returns `true` if a value exists. |
| `isEmpty()` | Returns `true` if no value exists (Java 11+). |
| `get()` | Returns the value; throws if empty (avoid when possible). |
| `orElse(default)` | Returns the value or a default. |
| `orElseGet(supplier)` | Lazily computes a default only if empty. |
| `orElseThrow()` | Returns the value or throws an exception. |
| `ifPresent(action)` | Executes an action if a value is present. |
| `map(f)` | Transforms the value and wraps the result in an `Optional`. |
| `flatMap(f)` | Like `map`, but for functions returning an `Optional`. |
| `filter(predicate)` | Keeps the value only if it matches a condition. |

---

### Examples

```java
Optional<String> name = Optional.of("Alice");

Optional<String> name2 = Optional.ofNullable(getName()); // getName() may return null

String result = name2.orElse("Unknown");

name2.ifPresent(System.out::println);

Optional<Integer> length = name.map(String::length);

Optional<String> longName = name.filter(n -> n.length() > 3);
```

---

### Best Practices

- ✅ Use `Optional` as a **method return type** to represent an optional result.
- ✅ Use `Optional.ofNullable()` when wrapping values that may be `null`.
- ❌ Avoid calling `get()` without first ensuring a value is present.
- ❌ Avoid using `Optional` for fields, method parameters, or collections unless you have a specific reason.