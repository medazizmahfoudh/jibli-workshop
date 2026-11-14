# Step 01: the JVM

## Goal

Dhia installs Jibli and the first thing he sees is a welcome message. Write the code that builds it, then compile and run it yourself to see what the JVM does. (Slide: Java 1/20, JVM and JDK.)

## Tasks

- [ ] In `src/main/java/tn/jibli/Greeter.java`, make `welcome(String name)` return `"Welcome to Jibli, <name>!"`.
- [ ] In `src/main/java/tn/jibli/Main.java`, print `Greeter.welcome("Dhia")` from `main`.
- [ ] Compile and run it from the terminal without Maven (see the commands below).

## Hints

1. A method that returns text uses `return`, and `+` joins strings.
2. `System.out.println(...)` prints a line.
3. `return "Welcome to Jibli, " + name + "!";`

## Check yourself

```bash
./mvnw -q test -Dtest='Step01*'
```

Green means `welcome` and `Main` both do what the test expects.

To see the JVM at work, compile and run by hand:

```bash
mkdir -p target/manual
javac -d target/manual src/main/java/tn/jibli/*.java
java -cp target/manual tn.jibli.Main
javap -c -cp target/manual tn.jibli.Greeter
```

`javac` turns the `.java` files into `.class` files (bytecode), `java` starts the JVM that runs them, and `javap -c` shows the bytecode inside a class. The `target` folder is ignored by Git, so nothing you generate there ends up in a commit.

## Stretch

- Change the greeting for one name only, for example a different message when `name` is `"Dhia"`. Try it once with `if`, even though we have not seen `if` yet.
