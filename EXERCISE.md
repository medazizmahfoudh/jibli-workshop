# Step 02: variables and types

## Goal

An order needs numbers and text: a product, a quantity and a price. Store them in variables of the right type and compute what Dhia owes for two Plat Healthy. (Slide: Java 2/20, variables and types.)

## Tasks

- [ ] In `Basics.java`, make `subtotal(int quantity, double unitPrice)` return quantity times price.
- [ ] Make `describeOrder(String product, int quantity, double unitPrice)` return text like `2 x Plat Healthy = 25.0 TND`.
- [ ] Use a local variable inside `describeOrder` instead of calling `subtotal` twice.

## Hints

1. `int` times `double` gives a `double`.
2. Reuse your own `subtotal` method.
3. `double sub = subtotal(quantity, unitPrice);` then join the pieces with `+`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step02*'
```

Green means both methods return exactly the expected text and numbers.

## Stretch

- Print the same text from `Main` for 3 x Plat Healthy at 12.5 TND.
