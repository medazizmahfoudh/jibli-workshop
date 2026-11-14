# Step 18: lists and maps

## Goal

Jibli receives orders all day, so an array with a fixed size is not enough. Keep the orders in a list, and keep the restaurants in a map so Jibli can find one by its id. (Slide: Java 18/20, lists and maps.)

## Tasks

`Restaurant` (id, name, open flag) is already in `tn.jibli.model`. Write the class `tn.jibli.basics.OrderBook`:

- [ ] `add(Order order)` and `size()`.
- [ ] `orders()` returns the orders as a `List<Order>` that callers cannot modify.
- [ ] `revenue()` adds up the totals of all orders.
- [ ] `ordersFrom(String restaurantName)` returns only the orders of that restaurant.
- [ ] `addRestaurant(Restaurant restaurant)` stores it in a `Map<Long, Restaurant>`, with its id as the key.
- [ ] `findRestaurant(long id)` returns the restaurant, or `null` when the id is unknown.

## Hints

1. Declare the fields with the interface type and build them with a concrete class: `List<Order> orders = new ArrayList<>();`.
2. `map.get(key)` already returns `null` when the key is missing.
3. `Collections.unmodifiableList(list)` gives a read-only view of a list.
4. Returning your own list lets the caller change it behind your back. That is why `orders()` is read-only.

## Check yourself

```bash
./mvnw -q test -Dtest='Step18*'
```

Green means every method works and the list you hand out cannot be modified.

## Stretch

- Make `findRestaurant` return an `Optional<Restaurant>` instead of `null`. What changes for the callers?
