# Step 16: polymorphism

## Goal

The checkout screen has to pay with whatever Dhia chose and add up delivery fees, without a long `if` on the type. Then Jibli adds a wallet, and the checkout must keep working without being touched. (Slide: Java 16/20, polymorphism.)

## Tasks

- [ ] `Checkout.pay(Payment p, double amount)` pays and returns the receipt.
- [ ] `Checkout.payAll(List<Payment> payments, double amount)` pays with each one and returns the list of receipts, in the same order.
- [ ] `Checkout.totalFees(List<Delivery> deliveries)` adds the fee of each delivery.
- [ ] Write `WalletPayment` (a new `Payment`) that returns `"Paid 25.0 TND from wallet"`. Do not change `Checkout` to support it.

## Hints

1. A variable of type `Payment` can hold a `CardPayment`, a `CashPayment` or any other implementation. Which `pay` runs is decided at runtime.
2. No `instanceof`, no `if`. Just call the method.
3. A list of `Delivery` can hold `ExpressDelivery` and `PickupDelivery` objects too.
4. A standard, an express and a pickup delivery cost 4.0 + 7.0 + 0.0.

## Check yourself

```bash
./mvnw -q test -Dtest='Step16*'
```

Green means the same `Checkout` code handles all payments and deliveries, including the wallet you just added.

## Stretch

- Add `D17Payment` (the mobile payment) in a new file. Does anything else need to change?

## Solution notes

Polymorphism lets one line of code do different things depending on the real object behind the variable. That is what makes `Checkout` closed to changes but open to new payments: `WalletPayment` was added without touching it. A common mistake is writing `if (p instanceof CardPayment)` inside the checkout, which brings back the long chain of conditions you wanted to avoid.
