# greenDAO Query Module - File Descriptions

## 1. AbstractQuery.java

### Description
`AbstractQuery<T>` is an abstract base class that provides common functionality for all query classes in the greenDAO query module. It stores shared query information such as the SQL statement, DAO reference, query parameters, and the owner thread. It also provides methods for parameter conversion, parameter updates, and thread validation to ensure queries are executed safely.

### Main Methods
- `toStringArray(Object[] values)` – Converts query parameters into a String array.
- `setParameter(int index, Object parameter)` – Updates query parameters dynamically.
- `setParameter(int index, Date parameter)` – Sets Date parameters.
- `setParameter(int index, Boolean parameter)` – Sets Boolean parameters.
- `checkThread()` – Verifies that the query is executed on the owner thread.

### SOLID Principles to Apply

**Single Responsibility Principle (SRP):**
The class currently manages both query parameter handling and thread validation. Thread validation can be extracted into a separate `ThreadValidator` class so that `AbstractQuery` focuses only on query management.

**Open/Closed Principle (OCP):**
The class currently requires modification whenever a new parameter type needs to be supported. Introducing a `ParameterConverter` interface would allow adding new parameter types without modifying the existing class.

**Dependency Inversion Principle (DIP):**
Instead of directly creating an `InternalQueryDaoAccess` object, the dependency can be injected through an abstraction, reducing coupling and improving testability.

---

## 2. Query.java

### Description
`Query<T>` represents a SELECT query that retrieves entity objects from the database. It supports retrieving complete lists, lazy-loaded lists, iterators, unique results, and reactive (RxJava) query execution. The class extends `AbstractQueryWithLimit` and provides different execution options for database queries.

### Main Methods
- `list()`
- `listLazy()`
- `listLazyUncached()`
- `listIterator()`
- `unique()`
- `uniqueOrThrow()`
- `forCurrentThread()`
- `__internalRxPlain()`
- `__InternalRx()`

### SOLID Principles to Apply

**Single Responsibility Principle (SRP):**
The class handles query execution, lazy loading, iterator creation, and reactive query support. These responsibilities can be separated into dedicated services such as `LazyLoadingService` and `ReactiveQueryService`.

**Open/Closed Principle (OCP):**
Different query execution strategies should be implemented using a `QueryExecutor` strategy interface instead of modifying the existing class.

**Dependency Inversion Principle (DIP):**
The class directly depends on `RxQuery`. Using an abstraction such as `ReactiveQuery` would reduce dependency on concrete implementations.

---

## 3. QueryBuilder.java

### Description
`QueryBuilder<T>` is the central class responsible for dynamically constructing SQL queries. It provides a fluent API for creating complex SQL statements by supporting filtering, joins, ordering, pagination, and finally generating executable query objects such as `Query`, `CountQuery`, `DeleteQuery`, and `CursorQuery`.

### Main Methods
- `where()`
- `whereOr()`
- `join()`
- `orderAsc()`
- `orderDesc()`
- `orderCustom()`
- `orderRaw()`
- `limit()`
- `offset()`
- `build()`
- `buildCount()`
- `buildDelete()`
- `buildCursor()`
- `list()`
- `unique()`
- `count()`

### SOLID Principles to Apply

**Single Responsibility Principle (SRP):**
`QueryBuilder` performs multiple responsibilities including building SQL statements, handling joins, sorting, pagination, and creating query objects. These responsibilities should be separated into components such as `WhereBuilder`, `JoinBuilder`, `OrderBuilder`, `PaginationBuilder`, and `QueryFactory`.

**Open/Closed Principle (OCP):**
Whenever a new query type is introduced, the class must be modified by adding another `build...()` method. Implementing the Factory Pattern would allow new query types to be added without changing the existing code.

**Dependency Inversion Principle (DIP):**
The class directly depends on concrete query classes (`Query`, `DeleteQuery`, `CountQuery`, and `CursorQuery`). Depending on interfaces or abstract factories would reduce coupling and improve flexibility.

---

## 4. CountQuery.java

### Description
`CountQuery<T>` executes SQL COUNT queries and returns the total number of records that satisfy the specified conditions. It extends `AbstractQuery` and provides functionality dedicated to counting query results.

### Main Methods
- `count()`
- `forCurrentThread()`
- `setParameter()`

### SOLID Principles to Apply

**Single Responsibility Principle (SRP):**
The class performs both count execution and cursor result validation. Validation logic can be extracted into a dedicated validator class.

**Liskov Substitution Principle (LSP):**
`CountQuery` correctly extends `AbstractQuery` without changing its expected behavior, making it a valid substitute for its parent class.

**Dependency Inversion Principle (DIP):**
Instead of directly calling `dao.getDatabase()`, the class should depend on a database abstraction to reduce coupling.

---

## 5. CursorQuery.java

### Description
`CursorQuery<T>` executes SQL queries and returns a raw Android `Cursor` instead of mapped entity objects. It is mainly intended for compatibility with applications that still rely on Cursor-based database operations.

### Main Methods
- `query()`
- `forCurrentThread()`
- `setParameter()`

### SOLID Principles to Apply

**Single Responsibility Principle (SRP):**
The class is responsible for both executing cursor queries and ensuring thread safety. Thread validation can be delegated to a reusable component.

**Open/Closed Principle (OCP):**
Future cursor processing strategies can be added through extension instead of modifying the existing implementation.

**Dependency Inversion Principle (DIP):**
The class should depend on a database abstraction rather than directly accessing the DAO's database object.

---

## 6. DeleteQuery.java

### Description
`DeleteQuery<T>` executes SQL DELETE statements to remove records from the database. It also manages database transactions to ensure deletion operations are performed safely and consistently.

### Main Methods
- `executeDeleteWithoutDetachingEntities()`
- `forCurrentThread()`
- `setParameter()`

### SOLID Principles to Apply

**Single Responsibility Principle (SRP):**
The class currently performs both delete operations and transaction management. Transaction handling can be extracted into a dedicated `TransactionManager`.

**Open/Closed Principle (OCP):**
Different deletion strategies can be implemented using the Strategy Pattern instead of modifying the class.

**Dependency Inversion Principle (DIP):**
The class should depend on a transaction abstraction rather than interacting directly with the concrete `Database` implementation.

---

## 7. LazyList.java

### Description
`LazyList<E>` is a read-only list implementation that loads database entities only when they are accessed. It supports lazy loading, optional caching, automatic cursor management, and thread-safe entity loading using `ReentrantLock`, making query execution more memory-efficient.

### Main Methods
- `get()`
- `loadEntity()`
- `loadRemaining()`
- `peek()`
- `close()`
- `isLoadedCompletely()`
- `listIteratorAutoClose()`

### SOLID Principles to Apply

**Single Responsibility Principle (SRP):**
The class manages lazy loading, cache management, cursor handling, synchronization, and iterator creation. These responsibilities should be separated into dedicated components such as `CacheManager`, `CursorManager`, `LazyLoader`, and `IteratorProvider`.

**Open/Closed Principle (OCP):**
Introducing a `CacheStrategy` interface would allow different caching mechanisms to be added without modifying the existing class.

**Interface Segregation Principle (ISP):**
The class implements the full `List` interface even though modification methods such as `add()`, `remove()`, and `clear()` are unsupported. A dedicated read-only collection interface would be more appropriate.

**Liskov Substitution Principle (LSP):**
Since `LazyList` does not support all `List` operations, substituting it for a general mutable `List` may lead to runtime exceptions. Implementing a read-only interface would better satisfy LSP.

**Dependency Inversion Principle (DIP):**
The class directly depends on `Cursor` and `InternalQueryDaoAccess`. Depending on abstractions such as `CursorProvider` and `EntityLoader` would reduce coupling and improve testability.