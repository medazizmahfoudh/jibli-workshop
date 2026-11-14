# Step 14: inheritance

## Goal

Jibli delivers in three ways: standard (4 TND), express (7 TND) and pickup (free). They are all deliveries, they only differ in the fee. Model that without copying code. (Slide: Java 14/20, inheritance.)

## Tasks

- [ ] `Delivery.fee()` returns the standard fee, `Order.DELIVERY_FEE`.
- [ ] Make `ExpressDelivery` extend `Delivery` and replace `fee()` so it returns 7.0.
- [ ] Make `PickupDelivery` extend `Delivery` and replace `fee()` so it returns 0.0.

## Hints

1. `class B extends A` gives B everything public in A.
2. A subclass can write a method with the same signature to replace the parent version. This is overriding.
3. Reuse the constant from step 13 in `Delivery`, do not type 4.0 again.
4. `public class ExpressDelivery extends Delivery { ... }`

## Check yourself

```bash
./mvnw -q test -Dtest='Step14*'
```

Green means the three fees are right and both subclasses really extend `Delivery`.

## Stretch

- Add `ScooterDelivery` with a fee of 5.0. Which existing class did you have to touch? (None should change.)
