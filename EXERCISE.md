# Step 29: dependency injection

## Goal

`@Autowired` on a field works, but you cannot test the service without starting Spring, and the field can be changed by anyone. Ask for the repository in the constructor instead. Then the service can be tested with a fake repository in milliseconds. (Slide: Spring Boot 9/14, dependency injection.)

## Tasks

- [ ] Make the field `private final OrderRepository repo` and remove `@Autowired`.
- [ ] Finish the constructor: it stores the repository in the field.
- [ ] Run the tests. The new ones use a Mockito fake instead of the real database.

## Hints

1. With one constructor, Spring passes the needed beans in by itself, no annotation needed.
2. A `final` field must be set in the constructor, which makes it impossible to forget.
3. `this.repo = repo;`
4. In the test, `new OrderService(fakeRepo)` is only possible because the dependency comes through the constructor.

## Check yourself

```bash
./mvnw -q test -Dtest='Step29*'
```

Green means the service works with a fake repository, the field is `final`, nothing is `@Autowired` any more, and Spring can still build the bean.

## Heads-up

The constructor already exists but does nothing, so the service still runs inside Spring because the old `@Autowired` field is filled in. The new tests build the service by hand and show it does not work yet.

## Stretch

- Why does the test verify that `save` was never called for Chapati Medina?
