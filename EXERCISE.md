# Step 08: access modifiers

## Goal

Right now anyone can write `order.total = -50`. Jibli must control who can touch what. Close the fields, open the class, and give the outside world a safe way to read an order. (Slide: Java 8/20, access modifiers.)

## Tasks

- [ ] Make the class `Order` public.
- [ ] Make `restaurant`, `product` and `total` `private`.
- [ ] Add `String note` with no modifier (package-private).
- [ ] Add `protected String audit()` that returns `"audit: <restaurant>/<product>/<total>"`.
- [ ] Add public getters `getRestaurant()`, `getProduct()` and `getTotal()`.

## Hints

1. The four levels, from most open to most closed: `public`, `protected`, nothing (package-private), `private`.
2. A getter is a public method that returns a private field.
3. `audit()` can read the private fields because it is inside the class. A fresh order gives `audit: null/null/0.0`.
4. `return "audit: " + restaurant + "/" + product + "/" + total;`

## Check yourself

```bash
./mvnw -q test -Dtest='Step08*'
```

Green means the modifiers are exactly right and a new order starts with `null`, `null` and `0.0`.

## Heads-up

The step 07 test is gone: it wrote to the fields directly, which is exactly what you are about to forbid. Expected: compile errors in the `Step08` tests until the getters exist.

## Stretch

- Try to write `order.total = 5;` in `Main`. Read the compiler message.

## Solution notes

`private` is the default choice for fields, then you open only what other classes really need. `protected` and package-private are visible inside the package, which is why the tests (same package) can still call `audit()` and read `note`. A common mistake is making everything `public` "to make it work", which brings back exactly the problem from step 07.
