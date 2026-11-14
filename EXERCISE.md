# Step 24: the first endpoint

## Goal

The app starts, but nobody can talk to it. Add the first HTTP endpoint, so Dhia's phone can say hello to Jibli. (Slide: Spring Boot 4/14, hello endpoint.)

## Tasks

- [ ] In `tn.jibli.HelloController`, add the annotation that makes it a REST controller.
- [ ] Add a method that answers `GET /hello` with the text `Hello from Jibli!`.
- [ ] Start the app with `./mvnw spring-boot:run` and open <http://localhost:8080/hello> in the browser.

## Hints

1. `@RestController` on the class turns the returned value into the HTTP response body.
2. `@GetMapping("/hello")` on a method says which URL and which HTTP method it answers.
3. Do not forget the imports from `org.springframework.web.bind.annotation`.
4. Stop the app with Ctrl+C before running the tests, otherwise the port stays busy if you run it in the background.

## Check yourself

```bash
./mvnw -q test -Dtest='Step24*'
```

Green means a `GET /hello` request returns status 200 and the exact text.

## Stretch

- Make `GET /hello?name=Dhia` answer `Hello Dhia, welcome to Jibli!`, and keep plain `/hello` working. Look at `@RequestParam`.

## Solution notes

Spring finds `HelloController` at startup (it is under `tn.jibli`), reads the annotations, and connects `GET /hello` to the method. You never call `hello()`, the framework does when a request arrives. A common mistake is writing `@Controller` instead of `@RestController`: Spring then looks for a page template called `Hello from Jibli!` and answers with an error.
