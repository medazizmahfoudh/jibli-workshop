# Step 10: JVM memory

## Goal

Jibli sometimes has to copy an order, and sometimes has to ask "are these two the same order?". In Java that question has two answers, depending on whether you compare references or values. (Slide: Java 10/20, JVM memory.)

## Tasks

- [ ] `MemoryLab.copyOf(Order o)` returns a new object with the same restaurant, product and total.
- [ ] `MemoryLab.sameObject(Order a, Order b)` is true only when both variables point to the very same object.
- [ ] `MemoryLab.sameValues(Order a, Order b)` is true when restaurant, product and total are equal. Compare the strings with `equals`.

## Hints

1. A variable of a class type holds a reference (an address on the heap), not the object itself.
2. `==` on references asks "same object?". `equals` on strings asks "same characters?".
3. For the total, `==` is fine because `double` is a primitive.
4. `new String("Mamma Chemi")` creates a different object than the literal `"Mamma Chemi"`, which is why the test uses it.

## Check yourself

```bash
./mvnw -q test -Dtest='Step10*'
```

Green means a copy is a different object with the same values, and your string comparison does not depend on object identity.

## Stretch

- Draw the stack and the heap for `Order a = new Order(...); Order b = a;`. How many objects are there?

## Solution notes

Local variables and method calls live on the stack. Objects live on the heap, and a variable of a class type only holds the reference. `Order b = a;` copies the reference, not the order. A common mistake is comparing strings with `==`: it sometimes works with literals (they are shared) and then fails with text coming from user input.
