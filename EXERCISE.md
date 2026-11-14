# Step 20: annotations

## Goal

You have been reading `@` labels for a while. Now use `@Override` properly and write one annotation yourself, so the next part (Spring Boot) stops looking like magic. Jibli will mark its delivery modes that are still experimental. (Slide: Java 20/20, annotations.)

## Tasks

- [ ] Add `@Override` to every method that overrides or implements something: `fee()` in `ExpressDelivery` and `PickupDelivery`, and `pay(...)` in `CardPayment`, `CashPayment` and `WalletPayment`.
- [ ] Create the annotation `tn.jibli.annotation.Experimental`. It is kept at runtime, it can only be put on a type, and it has a `String value()` that defaults to `""`.
- [ ] Put `@Experimental("Launching in Sfax first")` on `ExpressDelivery`.
- [ ] Write `AnnotationReader.experimentalNote(Class<?> type)`. It returns `Optional.of(note)` when the class has the annotation and `Optional.empty()` otherwise.

## Hints

1. `@Override` has no effect at runtime, it only makes the compiler check your signature.
2. An annotation is declared with `@interface`. Add `@Retention(RetentionPolicy.RUNTIME)` and `@Target(ElementType.TYPE)` to it.
3. The element is declared like a method: `String value() default "";`.
4. `type.getAnnotation(Experimental.class)` returns the annotation or `null`.

## Explore (not tested)

Change `fee()` to `fe()` in `ExpressDelivery` with `@Override` in place and read the compiler error. Then put it back. Without `@Override` that typo would have gone unnoticed.

## Check yourself

```bash
./mvnw -q test -Dtest='Step20*'
```

Green means the annotation is declared the right way and the reader finds the note. `@Override` itself cannot be tested because the compiler removes it.

## Stretch

- Make the annotation usable on methods too. Which `@Target` values do you need?

## Solution notes

An annotation is only a label. Something has to read it: the compiler for `@Override`, your own reflection code for `@Experimental`, and from the next step on, Spring for `@RestController`, `@Service` and friends. `RUNTIME` retention is what keeps the label alive after compilation. A common mistake is forgetting `@Retention(RUNTIME)`, then `getAnnotation` returns `null` and nothing seems to work.
