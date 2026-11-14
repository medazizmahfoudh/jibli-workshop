# Step 31: REST and CRUD

## Goal

An API that only creates orders is not much use. Dhia must also see his orders, change one, and cancel one. That is CRUD: create, read, update, delete. Each operation uses a different HTTP method and a meaningful status code. (Slide: Spring Boot 11/14, REST and CRUD.)

## Tasks

Service (`OrderService`, stubs are already there):

- [ ] `findAll()`, `findById(Long id)` (an `Optional`), `update(Long id, Order newData)` and `delete(Long id)` (returns whether something was deleted). `update` copies the fields of `newData` into the stored order and applies the same rules as `place`.

Controller (`OrderController`):

- [ ] `POST /orders` answers `201 Created`.
- [ ] `GET /orders` returns the list of orders.
- [ ] `GET /orders/{id}` returns one order, or `404` when it does not exist.
- [ ] `PUT /orders/{id}` updates and returns the order, or `404`.
- [ ] `DELETE /orders/{id}` answers `204 No Content`, or `404`.

## Hints

1. `@ResponseStatus(HttpStatus.CREATED)` sets the success status of a method.
2. `@PathVariable Long id` reads the `{id}` of the URL.
3. To answer 404, throw `new ResponseStatusException(HttpStatus.NOT_FOUND, "Order " + id + " not found")`.
4. `Optional.orElseThrow(() -> ...)` turns an empty result into an exception in one line.
5. `repo.existsById(id)` and `repo.deleteById(id)` are enough for the delete.

## Check yourself

```bash
./mvnw -q test -Dtest='Step31*'
```

Green means the five operations work end to end against the real H2 database, including the 404 cases.

## Stretch

- Try it for real with curl: create an order, list it, change its total, delete it.
