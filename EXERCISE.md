# Step 05: loops and arrays

## Goal

Dhia's cart is a list of prices. Jibli needs the total, the most expensive item, and a way to find a product by name. (Slide: Java 5/20, loops and arrays.)

## Tasks

- [ ] `Cart.sum(double[] prices)` adds all prices with a `for` loop.
- [ ] `Cart.mostExpensive(double[] prices)` returns the biggest price using an enhanced `for` loop. An empty array gives 0.0.
- [ ] `Cart.indexOf(String[] names, String wanted)` returns the position of `wanted` using a `while` loop, or -1 when it is not there.

## Hints

1. `prices.length` is the number of elements, and the first index is 0.
2. For `mostExpensive`, start with 0.0 and replace it whenever you see something bigger.
3. For `indexOf`, keep a counter `i`, stop when `i == names.length` or when you found the name. Compare strings with `equals`, not `==`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step05*'
```

Green means all three methods behave, including the empty array and the missing name.

## Stretch

- Write `Cart.average(double[] prices)`. What should it return for an empty array?

## Solution notes

Use a plain `for` when you need the index, an enhanced `for` when you only need each element, and `while` when you stop on a condition rather than on a count. Comparing strings with `==` checks if they are the same object, so use `equals`. A common mistake is writing `i <= prices.length` and getting an `ArrayIndexOutOfBoundsException`.
