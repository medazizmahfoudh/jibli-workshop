# Step 04: conditionals

## Goal

Jibli charges 4 TND for standard delivery, free from 40 TND. Express costs 7 TND and pickup is free. Write the rule once, so every order uses it. (Slide: Java 4/20, conditionals.)

## Tasks

- [ ] In `DeliveryFee.fee(double subtotal, String mode)` return 0.0 for `"pickup"` and 7.0 for `"express"`.
- [ ] For `"standard"` return 4.0, but 0.0 when the subtotal is 40.0 or more.
- [ ] Throw an `IllegalArgumentException` for any other mode.
- [ ] Use a `switch` for the mode and an `if/else` for the 40 TND threshold.

## Hints

1. A `switch` on a `String` works like one on a number: `case "pickup":`.
2. Do not forget `break` or use `return` inside each case.
3. 40.0 itself is already free, so the comparison is `>=`.
4. `default:` is the place for the exception.

## Check yourself

```bash
./mvnw -q test -Dtest='Step04*'
```

Green means every mode works, including the boundary cases 39.99 and 40.0.

## Stretch

- Add a mode `"night"` that costs 6 TND. Which part of the code do you have to touch?

## Solution notes

`switch` picks one branch out of several fixed values, `if/else` decides on a condition. Each `case` here ends with a `return`, so there is no fall-through to the next case. A common mistake is writing `> 40` and charging 4 TND for an order of exactly 40.0.
