# Step 27: repository

## Goal

Saving and loading orders is always the same: save, find all, find one, delete. Spring Data writes that code for you if you only say which entity and which type of id you use. (Slide: Spring Boot 7/14, repository.)

## Tasks

- [ ] Replace the class `OrderRepository` with an interface `OrderRepository extends JpaRepository<Order, Long>`. It stays in `tn.jibli.repository`.
- [ ] Do not write any method, nothing else is needed.
- [ ] Look at `save`, `findAll`, `findById` and `deleteById` in your IDE's autocomplete. You never wrote them.

## Hints

1. `JpaRepository<T, ID>` takes the entity type and the type of its `@Id` field.
2. Remove `@Repository` and the class body. An interface has no body for these methods, Spring generates the implementation at startup.
3. `findById` returns an `Optional<Order>`, because the id may not exist.

## Check yourself

```bash
./mvnw -q test -Dtest='Step27*'
```

Green means you can save, list, find and delete orders through your interface.

## Stretch

- Try adding `List<Order> findByRestaurant(String restaurant);` and write a quick test. You will need it in step 34.
