# Step 06: methods

## Goal

The receipt needs a tip, a function that tries to change the total, and a discount on the whole cart. The tests show what Java really passes to a method: a copy of the value, or a copy of the reference. (Slide: Java 6/20, methods.)

## Tasks

- [ ] `Receipt.withTip(double total, double tip)` returns the total plus the tip.
- [ ] `Receipt.tryToChange(double total)` is a `void` method that sets its parameter to 0. Write it, then look at what happens to the caller's variable.
- [ ] `Receipt.applyDiscountInPlace(double[] prices, int percent)` reduces every price in the array by that percentage, without returning anything.

## Hints

1. A `void` method has no `return value`, it just does its work.
2. A `double` parameter is a copy. An array parameter is a copy of the reference, so both names point to the same array.
3. For the discount, a price of 20.0 with 50 percent becomes 10.0.

## Check yourself

```bash
./mvnw -q test -Dtest='Step06*'
```

Green means the primitive stayed untouched and the array content did change. Make sure you can say why.

## Stretch

- Write a second version of `withTip` that takes a percentage tip instead of an amount. Same name, different parameters. That is overloading.
