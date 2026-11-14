# Step 09: constructors

## Goal

An order with no restaurant and no total makes no sense. Make Jibli build an order in one go, with everything it needs, so an empty `Order` can never exist. (Slide: Java 9/20, constructors.)

## Tasks

- [ ] In `Order`, add a constructor `Order(String restaurant, String product, double total)` that stores the three values.
- [ ] Notice what happens to `new Order()`. Read the error.
- [ ] Use `this` to tell the field from the parameter.

## Hints

1. A constructor has the name of the class and no return type.
2. Writing any constructor removes the default one that Java adds when there is none.
3. `this.restaurant = restaurant;` assigns the parameter to the field.

## Check yourself

```bash
./mvnw -q test -Dtest='Step09*'
```

Green means a new order holds exactly what it was built with, and `audit()` shows it.

## Heads-up

The step 08 "new order starts empty" test is retired: it used `new Order()`, which will stop compiling once you add your constructor. Expected: compile errors in `Step09ConstructorsTest` until the constructor exists.

## Stretch

- Add a second constructor `Order(String restaurant, String product)` that sets the total to 0.0 by calling the first one with `this(...)`.
