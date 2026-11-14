# Jibli workshop

Code for a one-day beginner backend workshop (Java, then Spring Boot) for first-year engineering students. It follows the slide deck "Intro to Backend Development: Java & Spring Boot", one step per slide or small group of slides.

We build Jibli, a food delivery app (jibli.tn). Dhia orders a Plat Healthy (12.5 TND) from Mamma Chemi, and by the end of the day the backend can create, list, update, delete and search those orders.

You write the code. Each step has two branches:

- `step-NN-slug/exercise` is the previous solution plus a task. Read `EXERCISE.md` at the root of the repository, then write the code.
- `step-NN-slug/solution` is the same step solved.

Everything is cumulative, so each branch contains all the earlier steps, and `main` is the finished app (the last solution).

## Getting started

You need a JDK 21 or newer and Git. Maven is included through the wrapper.

```bash
git clone <url> jibli-workshop
cd jibli-workshop
git switch step-01-jvm/exercise
```

Open the folder in your IDE as a Maven project, read `EXERCISE.md`, and write code until the check for the step turns green:

```bash
./mvnw -q test -Dtest='Step01*'
```

On Windows use `mvnw.cmd` in cmd or PowerShell. Step 33 runs a bash script, so use Git Bash or WSL for that one.

When you are done with a step, go to the next exercise branch. Commit or stash your work first (`git stash`), because Git will not switch with uncommitted changes that would be overwritten.

## Stuck or behind?

Stuck: `git switch step-NN-slug/solution` shows the finished step.

Behind: switch to the previous step's solution branch and carry on from there.

## Steps

Format tells the instructor how long a step takes. `core` is 10 to 15 minutes of coding, `quick` is 5 minutes or less, and `guided` is mostly reading and running things. Any step can be skipped by switching to its solution branch.

| Step | Slide | Format | Topic | Exercise branch | Solution branch |
|---|---|---|---|---|---|
| 01 | Java 1/20 JVM, JDK | quick | From .java to .class to the JVM | `step-01-jvm/exercise` | `step-01-jvm/solution` |
| 02 | Java 2/20 Variables, types | quick | int, double, String, boolean | `step-02-variables/exercise` | `step-02-variables/solution` |
| 03 | Java 3/20 Operators, casts | quick | Arithmetic, integer division, casts, % | `step-03-operators/exercise` | `step-03-operators/solution` |
| 04 | Java 4/20 Conditionals | core | if/else and switch | `step-04-conditionals/exercise` | `step-04-conditionals/solution` |
| 05 | Java 5/20 Loops, arrays | core | for, enhanced for, while, arrays | `step-05-loops/exercise` | `step-05-loops/solution` |
| 06 | Java 6/20 Methods | core | Parameters, return values, pass by value | `step-06-methods/exercise` | `step-06-methods/solution` |
| 07 | Java 7/20 Classes and objects | core | Fields and new | `step-07-classes/exercise` | `step-07-classes/solution` |
| 08 | Java 8/20 Access modifiers | quick | private, protected, package-private, public | `step-08-access/exercise` | `step-08-access/solution` |
| 09 | Java 9/20 Constructors | quick | Building an object in one go | `step-09-constructors/exercise` | `step-09-constructors/solution` |
| 10 | Java 10/20 JVM memory | core | References, == versus equals | `step-10-memory/exercise` | `step-10-memory/solution` |
| 11 | Java 11/20 Stack and heap compared | quick | Recursion and StackOverflowError | `step-11-memory-areas/exercise` | `step-11-memory-areas/solution` |
| 12 | Java 12/20 Encapsulation | core | Setters that protect the data | `step-12-encapsulation/exercise` | `step-12-encapsulation/solution` |
| 13 | Java 13/20 static and final | core | Constants and class-level state | `step-13-static-final/exercise` | `step-13-static-final/solution` |
| 14 | Java 14/20 Inheritance | core | extends and overriding | `step-14-inheritance/exercise` | `step-14-inheritance/solution` |
| 15 | Java 15/20 Interfaces | core | Contracts, several implementations | `step-15-interfaces/exercise` | `step-15-interfaces/solution` |
| 16 | Java 16/20 Polymorphism | core | One call, many behaviours | `step-16-polymorphism/exercise` | `step-16-polymorphism/solution` |
| 17 | Java 17/20 Packages | guided | Moving classes into packages | `step-17-packages/exercise` | `step-17-packages/solution` |
| 18 | Java 18/20 Lists and maps | core | List, Map, unmodifiable views | `step-18-collections/exercise` | `step-18-collections/solution` |
| 19 | Java 19/20 Exceptions | core | throw, try/catch, custom exception | `step-19-exceptions/exercise` | `step-19-exceptions/solution` |
| 20 | Java 20/20 Annotations | quick | @Override and your own annotation | `step-20-annotations/exercise` | `step-20-annotations/solution` |
| 21 | Spring Boot 1/14 Framework, IoC | guided | Spring creates the objects | `step-21-framework/exercise` | `step-21-framework/solution` |
| 22 | Spring Boot 2/14 Maven | guided | Dependencies and the Boot plugin | `step-22-maven/exercise` | `step-22-maven/solution` |
| 23 | Spring Boot 3/14 Spring Initializr | guided | start.spring.io | `step-23-initializr/exercise` | `step-23-initializr/solution` |
| 24 | Spring Boot 4/14 Hello endpoint | quick | @RestController, @GetMapping | `step-24-hello/exercise` | `step-24-hello/solution` |
| 25 | Spring Boot 5/14 Layers | quick | controller, service, repository | `step-25-layers/exercise` | `step-25-layers/solution` |
| 26 | Spring Boot 6/14 Entity | core | @Entity, H2, application.properties | `step-26-entity/exercise` | `step-26-entity/solution` |
| 27 | Spring Boot 7/14 Repository | quick | JpaRepository | `step-27-repository/exercise` | `step-27-repository/solution` |
| 28 | Spring Boot 8/14 Service | core | Business rules | `step-28-service/exercise` | `step-28-service/solution` |
| 29 | Spring Boot 9/14 Dependency injection | core | Constructor injection | `step-29-di/exercise` | `step-29-di/solution` |
| 30 | Spring Boot 10/14 Controller | core | @RequestBody, @PostMapping | `step-30-controller/exercise` | `step-30-controller/solution` |
| 31 | Spring Boot 11/14 REST and CRUD | core | Five endpoints, status codes | `step-31-rest/exercise` | `step-31-rest/solution` |
| 32 | Spring Boot 12/14 Postman | guided | A collection you can share | `step-32-postman/exercise` | `step-32-postman/solution` |
| 33 | Spring Boot 13/14 curl | core | A smoke test script | `step-33-curl/exercise` | `step-33-curl/solution` |
| 34 | Spring Boot 14/14 Recap (capstone) | core | Search with a query parameter | `step-34-recap/exercise` | `step-34-recap/solution` |

The same table with the deck positions is in [docs/SLIDE_MAP.md](docs/SLIDE_MAP.md). Branch `step-00-setup` is the starting point and has no exercise.

## Checking the branches

`tools/verify-branches.sh` goes through every exercise and solution branch and checks that the exercise compiles and its step tests are red, and that the solution is green. It switches branches, so run it with a clean working tree.

## What you built

A small backend for Jibli with five REST endpoints on orders, stored in H2, with the business rules in a service. You started from `javac` and ended with an app you can call from Postman and curl.

A request travels like this:

```
curl / Postman / the Jibli app
   |  GET /orders?restaurant=Mamma%20Chemi
   v
OrderController    reads the request, writes JSON
   |  service.findByRestaurant("Mamma Chemi")
   v
OrderService       business rules (a total above 0, Chapati Medina is closed)
   |  repo.findByRestaurant("Mamma Chemi")
   v
OrderRepository    Spring Data builds the query from the method name
   |
   v
H2 database        table "orders"
```

Run it with `./mvnw spring-boot:run`, then try `bash scripts/smoke-test.sh`, the Postman collection in `postman/`, or the H2 console at <http://localhost:8080/h2-console> (JDBC URL `jdbc:h2:mem:jibli`).

## Where to go next

- Relationships: a `Restaurant` entity and `@OneToMany` / `@ManyToOne` between restaurants and orders, instead of the fixed list of closed restaurants.
- PostgreSQL instead of H2, so the data survives a restart.
- Security with JWT, so only Dhia can see Dhia's orders.
- Clean error answers: a closed restaurant should give a clear 4xx, not a 500.
- More tests, and Docker to package the app.
