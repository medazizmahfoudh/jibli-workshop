# Step 22: Maven

## Goal

Jibli needs a web server, a database layer and a database. In Maven you do not download jars by hand, you list what you need in `pom.xml` and Maven fetches it, with everything it depends on. (Slide: Spring Boot 2/14, Maven.)

## Tasks

- [ ] Add these dependencies to `pom.xml`: `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, and `com.h2database:h2` with scope `runtime`.
- [ ] Add a `build` section with the `spring-boot-maven-plugin`, configured with `<mainClass>tn.jibli.JibliApplication</mainClass>`.
- [ ] Explore (not tested): run `./mvnw dependency:tree`, then `./mvnw package`, then `java -jar target/jibli-0.0.1-SNAPSHOT.jar`.

## Hints

1. A "starter" is a dependency that pulls in a whole set of libraries that work together.
2. The `runtime` scope means the jar is needed to run the app, but not to compile it.
3. The project has two classes with a `main` method: `Main` and `JibliApplication`. The plugin needs to be told which one starts the app.
4. Dependencies go in `<dependencies>`, the plugin in `<build><plugins>`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step22*'
```

Green means the pom has the right dependencies and plugin, and the Spring context still starts with them.

## Stretch

- Find `tomcat-embed-core` in the output of `dependency:tree`. Which of your dependencies brought it in?
