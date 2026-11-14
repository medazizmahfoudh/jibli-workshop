# Step 19: exceptions

## Goal

Chapati Medina is closed tonight. If Dhia tries to order from it, Jibli must say so clearly instead of creating a ghost order. (Slide: Java 19/20, exceptions.)

## Tasks

- [ ] Finish `tn.jibli.exception.RestaurantClosedException`. It is a `RuntimeException`, and its message is `"Restaurant closed: <name>"`.
- [ ] `OrderPlacer.placeOrder(Restaurant r, String product, double total)` returns an `Order` for an open restaurant and throws `RestaurantClosedException` for a closed one.
- [ ] `OrderPlacer.tryPlace(...)` takes the same arguments, catches the exception, and returns `"Restaurant closed"` or `"Order accepted"`.

## Hints

1. `throw new SomeException(...)` stops the method right there.
2. `try { ... } catch (SomeException e) { ... }` handles it so the program continues.
3. The exception takes the restaurant name, and it builds the message by itself with `super(...)`.
4. Use `r.isOpen()` and `r.getName()` from `Restaurant`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step19*'
```

Green means a closed restaurant throws the right exception with the right message, and `tryPlace` never lets it escape.

## Stretch

- Why is a `RuntimeException` a good choice here, and when would you prefer a checked exception?
