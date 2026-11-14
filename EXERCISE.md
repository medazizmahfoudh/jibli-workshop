# Step 26: entity

## Goal

Orders vanish when the app stops. Store them in a database. With JPA you describe the table with a Java class, and Hibernate writes the SQL. We use H2, a database that lives in memory, so there is nothing to install. (Slide: Spring Boot 6/14, entity.)

## Tasks

- [ ] Turn `tn.jibli.model.Order` into a JPA entity: `@Entity`, and `@Table(name = "orders")`.
- [ ] Add a `Long id` field with `@Id` and `@GeneratedValue`, and make `getId()` return it (a stub is already there).
- [ ] Add a `protected Order()` constructor for JPA. It must not increase `createdCount`.
- [ ] Create `src/main/resources/application.properties` with an in-memory H2 database (`jdbc:h2:mem:jibli`), `spring.jpa.show-sql=true` and the H2 console enabled.

## Hints

1. The annotations come from `jakarta.persistence`.
2. `order` is a reserved word in SQL, that is why the table is called `orders`.
3. JPA creates objects by itself when it reads a row. It needs an empty constructor, and it does not go through your counter.
4. The property names are `spring.datasource.url`, `spring.jpa.show-sql` and `spring.h2.console.enabled`.
5. Run the app and open <http://localhost:8080/h2-console> with the same JDBC URL to see the `orders` table.

## Check yourself

```bash
./mvnw -q test -Dtest='Step26*'
```

Green means an order can be saved and read back, the table is called `orders`, JPA does not touch the counter, and the three properties are set.

## Stretch

- Look at the SQL printed in the console when the test runs. Which statements did Hibernate write for you?

## Solution notes

An entity is a normal class with a few labels that tell Hibernate how it maps to a table: the class is the table, each field is a column, each object is a row. `@GeneratedValue` lets the database pick the id, so a new order has `id == null` until it is saved. A common mistake is forgetting the empty constructor, and Hibernate fails with an error about instantiating the entity.
