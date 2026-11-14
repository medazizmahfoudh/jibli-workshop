# Step 12: encapsulation

## Goal

Private fields stop outsiders from writing, but nothing stops an order of -50 TND from being created. Make `Order` refuse invalid data, wherever it comes from. (Slide: Java 12/20, encapsulation.)

## Tasks

- [ ] Add `setTotal(double total)`. It throws `IllegalArgumentException` when the total is negative.
- [ ] Add `setRestaurant(String)` and `setProduct(String)`. They throw `IllegalArgumentException` when the value is `null` or blank.
- [ ] Change the constructor so it calls the three setters instead of assigning the fields directly.

## Hints

1. A setter is a public method that changes a private field after checking the new value.
2. `value == null || value.isBlank()` covers both bad cases.
3. If the constructor uses the setters, the same rules apply to new objects and to later changes. Write each rule once.
4. `throw new IllegalArgumentException("total must not be negative");`

## Check yourself

```bash
./mvnw -q test -Dtest='Step12*'
```

Green means invalid values are rejected everywhere, and an order that failed validation keeps its old value.

## Heads-up

Expected: compile errors in `Step12EncapsulationTest` until the setters exist.

## Stretch

- Should a total of exactly 0 be allowed? Discuss, then decide where that rule belongs.
