# Step 07: classes and objects

## Goal

So far an order was just numbers and text floating around. Give Jibli a real `Order` that groups the restaurant, the product and the total, so Dhia's order from Mamma Chemi travels as one thing. (Slide: Java 7/20, classes and objects.)

## Tasks

- [ ] Open `src/main/java/tn/jibli/Order.java`. It is an empty class.
- [ ] Add three fields: `String restaurant`, `String product` and `double total`. Do not write a constructor and do not add any modifier yet.
- [ ] Look at the test to see how objects are created and used.

## Hints

1. A field is a variable declared inside the class but outside any method.
2. Every `new Order()` gives a separate object with its own copy of each field.
3. `String restaurant;` is a complete field declaration.

## Check yourself

```bash
./mvnw -q test -Dtest='Step07*'
```

Green means two orders hold different values at the same time.

## Heads-up

Expected: compile errors in `Step07ClassesTest` until you add the fields. That is the point of this step, the test cannot compile without them.

## Stretch

- Create a third order for Dar Lablabi in `Main` and print its fields.
