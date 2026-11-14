# Step 17: packages

## Goal

Eighteen classes in one folder is already hard to read, and Jibli is just starting. Sort them into packages so everyone finds things where they expect. (Slide: Java 17/20, packages.)

## Tasks

- [ ] Create three packages and move the classes in with your IDE's Move refactoring (right click, Refactor, Move, or F6 in IntelliJ). The IDE updates the `package` lines and the imports for you.

  | Package | Classes |
  |---|---|
  | `tn.jibli.basics` | `Greeter`, `Basics`, `Calc`, `DeliveryFee`, `Cart`, `Receipt`, `MemoryLab`, `Recursion` |
  | `tn.jibli.model` | `Order`, `Delivery`, `ExpressDelivery`, `PickupDelivery` |
  | `tn.jibli.payment` | `Payment`, `CardPayment`, `CashPayment`, `WalletPayment`, `Checkout` |

- [ ] `Main` stays in `tn.jibli`. Make sure it still compiles.
- [ ] Run all the tests. They were already moved to the matching packages for you.

## Hints

1. The folder must match the package: `tn.jibli.model` lives in `src/main/java/tn/jibli/model`.
2. A class in another package needs an `import`, and its public members must really be `public`.
3. Without an IDE: create the folders, `git mv` each file, change the `package` line, and add the imports by hand.

## Check yourself

```bash
./mvnw -q test
```

Green means everything compiles in its new place and the whole suite still passes. The new `Step17` test checks the locations with `Class.forName`.

## Heads-up

The tests from steps 01 to 16 were moved into the mirrored packages and now import the classes from their future location. Expected: compile errors in many test files until the main classes are moved. `src/main` compiles the whole time.

## Stretch

- What happens if you make `Order.note` public after the move? Why was package-private a bad idea once the classes are in different packages?
