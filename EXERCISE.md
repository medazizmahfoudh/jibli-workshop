# Step 13: static and final

## Goal

The delivery fee of 4 TND is the same for every order, and Jibli wants to know how many orders were created. Both belong to the class, not to one order. (Slide: Java 13/20, static and final.)

## Tasks

- [ ] Make `DELIVERY_FEE` a constant: `public static final double DELIVERY_FEE = 4.0`.
- [ ] Add one shared counter `createdCount` that goes up each time an order is created. Only a valid order counts.
- [ ] Implement `getCreatedCount()` and the package-private `resetCounter()` (the tests call it before each test).
- [ ] Implement `totalWithFee()`: the order total plus the delivery fee.

## Hints

1. `static` means one copy for the whole class. `final` means it can never be reassigned.
2. Constants are written in capitals with underscores.
3. Increase the counter at the end of the constructor, after the setters have accepted the values.
4. Call the method `totalWithFee()`, not `getTotalWithFee()`. We will turn orders into JSON later and a `get` method would show up as a field.
5. Reach static members through the class name: `Order.DELIVERY_FEE`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step13*'
```

Green means the constant is really `static final`, the counter counts only valid orders, and the fee is added.

## Stretch

- What would happen if `createdCount` were not `static`?

## Solution notes

A `static` member belongs to the class and exists once, so every order sees the same fee and the same counter. `final` on a `double` makes it a real constant. Shared mutable state is fine for a small counter but is a trap in bigger programs, which is why the tests reset it. A common mistake is incrementing the counter before validation, which counts orders that never existed.
