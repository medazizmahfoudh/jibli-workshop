# Step 28: service

## Goal

The repository saves anything, even an order for a restaurant that is closed tonight. The rules about orders belong in one place, the service. Chapati Medina is closed, so Dhia cannot order from it. (Slide: Spring Boot 8/14, service.)

## Tasks

- [ ] In `OrderService.place(Order order)`, throw `IllegalArgumentException` when the total is 0 or less.
- [ ] Throw `RestaurantClosedException` (from step 19) when the restaurant is `"Chapati Medina"`. Keep the closed restaurants in a small fixed set.
- [ ] Otherwise save the order with the repository and return the saved order.

## Hints

1. The `repo` field is already there. `@Autowired` asks Spring to fill it in.
2. `Set.of("Chapati Medina")` is a handy fixed set, and `set.contains(name)` checks it.
3. `repo.save(order)` returns the saved entity, with its id.
4. Check the rules first, so nothing is saved for a refused order.

## Check yourself

```bash
./mvnw -q test -Dtest='Step28*'
```

Green means good orders are saved, bad ones are refused, and nothing reaches the database in that case.

## Stretch

- Move the check into a private method `checkRules(Order order)`. You will reuse it in step 31.
