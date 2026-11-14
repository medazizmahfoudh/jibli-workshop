# Step 15: interfaces

## Goal

Dhia can pay by card or in cash. The rest of Jibli should not care which one, it only needs to know that something can pay. Describe that with an interface. (Slide: Java 15/20, interfaces.)

## Tasks

- [ ] Create `interface Payment` with one method `String pay(double amount)`.
- [ ] Make `CardPayment` implement it and return `"Paid 25.0 TND by card"` for an amount of 25.0.
- [ ] Make `CashPayment` implement it and return `"Paid 25.0 TND in cash"`.

## Hints

1. An interface declares what a class can do, not how. Its methods have no body.
2. `implements` is to interfaces what `extends` is to classes.
3. A class that implements an interface must provide every method of it.
4. Build the text with the amount: `"Paid " + amount + " TND by card"`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step15*'
```

Green means `Payment` exists as an interface, both classes implement it, and the messages are right.

## Stretch

- Could a class implement two interfaces? Could it extend two classes?
