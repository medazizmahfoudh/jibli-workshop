# Step 21: framework and inversion of control

## Goal

Until now your code called everything: you wrote `new`, you wrote `main`. With a framework it is the other way round. You describe your classes, and Spring creates them and connects them. Start Jibli as a Spring Boot application. (Slide: Spring Boot 1/14, framework and IoC.)

## Tasks

- [ ] In `pom.xml`, add the dependency `org.springframework.boot:spring-boot-starter` (no version needed, the parent provides it).
- [ ] Create `tn.jibli.JibliApplication` with `@SpringBootApplication` and a `main` that calls `SpringApplication.run(...)`.
- [ ] Create `tn.jibli.WelcomeMessage`, a class annotated with `@Component`, with a method `String text()` that returns `"Welcome to Jibli"`.

## Hints

1. After editing `pom.xml`, reload the Maven project in your IDE, or run `./mvnw -q compile`.
2. `@SpringBootApplication` goes on a class, and Spring scans that class's package and everything below it.
3. `@Component` tells Spring "create one object of this class and keep it".
4. `SpringApplication.run(JibliApplication.class, args);`

## Check yourself

```bash
./mvnw -q test -Dtest='Step21*'
```

Green means Spring started and handed the test a `WelcomeMessage` that nobody created with `new`. Look at the test: it never says `new WelcomeMessage()`.

## Heads-up

Expected: compile errors in `Step21FrameworkTest` until the dependency is added and both classes exist. The test needs Spring and your two classes.

## Stretch

- Run `JibliApplication` from your IDE. Spring starts and stops straight away, can you see why? (There is nothing to keep it alive yet.)

## Solution notes

Inversion of control means the framework is in charge: you mark a class with `@Component` and Spring decides when to create it and who gets it. By default there is one shared instance per class (a singleton bean). A common mistake is putting `JibliApplication` in a sub-package, or the component outside it, so Spring never finds the class to scan.
