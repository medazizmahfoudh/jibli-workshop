# Step 34: recap and search

## Goal

Mamma Chemi wants to see only its own orders. Add a search to the list endpoint, using everything from the last 14 steps: a repository query, a service method, and a request parameter. (Slide: Spring Boot 14/14, recap.)

## Tasks

- [ ] In `OrderRepository`, add `List<Order> findByRestaurant(String restaurant)`. Spring Data builds the query from the name.
- [ ] In `OrderService`, add a method that returns the orders of one restaurant.
- [ ] In `OrderController`, let `GET /orders` accept an optional `restaurant` parameter. With it, return only that restaurant's orders. Without it, return all of them, as before.
- [ ] Read the "What you built" part of the README once more.

## Hints

1. Query parameters come after the `?`: `GET /orders?restaurant=Mamma%20Chemi`. `%20` is a space.
2. `@RequestParam(required = false) String restaurant` is `null` when the parameter is missing.
3. Spring Data reads the method name: `findBy` + the field name `Restaurant`. The spelling has to match the field of the entity.
4. Keep the existing `GET /orders` behaviour. The old tests must stay green.

## Check yourself

```bash
./mvnw -q test -Dtest='Step34*'
```

Green means a search by restaurant returns only its orders, an unknown restaurant returns an empty list, and no parameter returns everything.

## Stretch

- Try a second filter on the product, or search ignoring upper and lower case (look for `IgnoreCase` in the Spring Data names).

## Solution notes

Each layer got one small change: a method name in the repository, a method in the service, a parameter in the controller. That is what the layers are for. `@RequestParam(required = false)` gives `null` when the client does not send the parameter, so the old behaviour stays. A common mistake is a typo in the derived query name, such as `findByRestaurants`, and Spring Data fails at startup because no such field exists.
