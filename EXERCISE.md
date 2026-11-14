# Step 11: stack and heap compared

## Goal

Every method call takes room on the stack, and the stack is small. Write a recursive method that sums a number down to 1, then make the stack run out on purpose to see what the JVM does. (Slide: Java 11/20, stack and heap compared.)

## Tasks

- [ ] `Recursion.sumTo(int n)` returns 1 + 2 + ... + n by calling itself. For `n <= 0` it returns 0.
- [ ] `Recursion.overflows()` calls a private method that calls itself forever, catches the `StackOverflowError`, and returns `true`.

## Hints

1. A recursive method needs a stopping case, otherwise it never ends.
2. `sumTo(n)` is `n + sumTo(n - 1)`.
3. `StackOverflowError` is an error, not an exception, but `catch` works the same way.
4. Do not try to fill the heap (`OutOfMemoryError`), that one can freeze your machine.

## Check yourself

```bash
./mvnw -q test -Dtest='Step11*'
```

Green means the sum is right and the overflow was caught.

## Stretch

- What is the biggest `n` for which `sumTo(n)` still works on your machine? How would a loop compare?

## Solution notes

Each call gets a frame on the stack with its parameters and local variables, and the frame disappears when the method returns. A recursion without a stopping case keeps adding frames until the JVM throws `StackOverflowError`. A common mistake is forgetting the stopping case or moving towards it in the wrong direction (`sumTo(n + 1)`).
