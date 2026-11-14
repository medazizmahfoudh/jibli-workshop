# Step 30: controller

## Goal

Dhia taps "Order" in the app, and the phone sends the order as JSON. The controller receives it, turns it into an `Order`, and hands it to the service. (Slide: Spring Boot 10/14, controller.)

## Tasks

- [ ] Put `@RequestMapping("/orders")` on `OrderController`.
- [ ] Inject `OrderService` through the constructor, as you did in step 29.
- [ ] Add a method with `@PostMapping` that takes `@RequestBody Order order`, calls `service.place(order)`, and returns the saved order.

## Hints

1. `@RequestBody` tells Spring to read the JSON of the request and build an `Order` from it. It uses the setters.
2. Whatever the method returns is written back as JSON.
3. The controller does not check any rule. That is the service's job.
4. `@PostMapping` with no path means `POST /orders`, because of the class-level `@RequestMapping`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step30*'
```

Green means a `POST /orders` with a JSON order succeeds and the response contains the same restaurant, product and total. The service is a fake in this test, so only the web layer is checked.

## Stretch

- Start the app and try the endpoint with curl: `curl -X POST localhost:8080/orders -H 'Content-Type: application/json' -d '{"restaurant":"Mamma Chemi","product":"Plat Healthy","total":25.0}'`.
