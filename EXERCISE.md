# Step 25: layers

## Goal

If everything lives in one controller, Jibli turns into a mess after ten features. Split the backend into three layers, each with one job, before real code arrives. (Slide: Spring Boot 5/14, layers.)

```
Client -> Controller -> Service -> Repository -> Database
```

## Tasks

- [ ] Create the package `tn.jibli.controller` and move `HelloController` into it.
- [ ] In the same package create `OrderController`, annotated with `@RestController`.
- [ ] Create `tn.jibli.service.OrderService`, annotated with `@Service`.
- [ ] Create `tn.jibli.repository.OrderRepository`, a plain class annotated with `@Repository` for now. We replace it in step 27.

## Hints

1. The controller deals with HTTP, the service with business rules, the repository with data. A layer only talks to the one below it.
2. `@Service` and `@Repository` do the same as `@Component` for Spring, but tell readers (and tools) what the class is for.
3. Use your IDE's Move refactoring for `HelloController`, then check that `/hello` still works.

## Check yourself

```bash
./mvnw -q test -Dtest='Step25*'
```

Green means the four classes are in the right packages with the right annotations, Spring created them, and `/hello` still answers.

## Stretch

- Why would it be a bad idea for the controller to talk to the repository directly?

## Solution notes

Each layer has one reason to change: the controller when the API changes, the service when a business rule changes, the repository when the storage changes. Spring finds all of them through scanning, so no wiring code is needed yet. A common mistake is moving the class in the file system but not under the scanned package, for example outside `tn.jibli`, so the bean never appears.
