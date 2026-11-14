# Step 03: operators and casts

## Goal

Jibli needs to add the delivery fee, apply discounts, show whole dinars and spot loyalty orders. These are small calculations, but integer division and casts hide traps. (Slide: Java 3/20, operators and casts.)

## Tasks

- [ ] `Calc.total(subtotal, fee)` returns subtotal plus fee.
- [ ] `Calc.applyDiscount(subtotal, percent)` returns the subtotal reduced by that percentage (a 10% discount on 40.0 gives 36.0).
- [ ] `Calc.dinars(amount)` returns the whole dinars as an `int`, cutting off the decimals.
- [ ] `Calc.averageItems(totalItems, orders)` returns the average as a `double` (7 items in 2 orders is 3.5).
- [ ] `Calc.isLoyaltyOrder(orderNumber)` is true when the order number is divisible by 5.

## Hints

1. `7 / 2` is `3` in Java because both sides are `int`.
2. A cast such as `(double) x` changes the type of one side before the division happens.
3. `%` gives the remainder of a division. An order number divisible by 5 has remainder 0.
4. For the discount, check where `percent / 100` ends up if both are `int`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step03*'
```

Green means all five methods give the right values, including the integer division cases.

## Stretch

- What does `dinars(-3.7)` return? Is that what a shop would want?

## Solution notes

Division of two `int` values stays an `int`, so the decimals are lost before anything else happens. Cast one operand to `double` first, and put the cast on the operand, not on the result. A common mistake is writing `(double) (7 / 2)`, which still gives 3.0.
