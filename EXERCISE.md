# Step 23: Spring Initializr

## Goal

Nobody writes a Spring pom from scratch. The Spring team runs a website that generates one. Use it to recreate the project you already have, and see that it matches what you wrote by hand. (Slide: Spring Boot 3/14, Spring Initializr.)

## Tasks

- [ ] Open <https://start.spring.io>.
- [ ] Choose Maven, Java, the latest stable Spring Boot 3.x, group `tn.jibli`, artifact `jibli`, packaging Jar, Java 21.
- [ ] Add the dependencies that match our `pom.xml`: Spring Web, Spring Data JPA and H2 Database.
- [ ] Click Explore (do not download yet) and compare the generated `pom.xml` with ours.
- [ ] Fill in `docs/initializr.properties` with the values you chose.

## Hints

1. Each dependency name in Initializr turns into one or more `artifactId` entries in the pom.
2. Packaging is `jar` or `war`. Our pom does not say, which means the default.
3. List the dependencies like this: `dependencies=Spring Web, Spring Data JPA, H2 Database`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step23*'
```

Green means the file agrees with the real `pom.xml`: same group, artifact, packaging, Java version and dependencies.

## Stretch

- Which dependencies would Initializr add if you picked Spring Security? Do not add it, just look.

## Solution notes

Initializr produces the same kind of `pom.xml` you wrote in step 22, plus the main class and a test, so you can start a new project in a minute. The "dependency" names on the site are friendly labels for one or more starters. A common mistake is picking a Spring Boot snapshot or milestone version, which can change from one day to the next.
