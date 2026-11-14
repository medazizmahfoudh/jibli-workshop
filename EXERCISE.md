# Step 32: Postman

## Goal

Typing JSON in curl gets old quickly. Postman keeps your requests in a collection that you can run again, change, and share with a teammate. Build one for the order API. (Slide: Spring Boot 12/14, Postman.)

## Tasks

- [ ] Start the app: `./mvnw spring-boot:run`.
- [ ] In Postman, create a collection called `Jibli` with a collection variable `baseUrl` set to `http://localhost:8080`.
- [ ] Add five requests that use `{{baseUrl}}`: `POST /orders` (with a JSON body), `GET /orders`, `GET /orders/1`, `PUT /orders/1` (with a JSON body) and `DELETE /orders/1`.
- [ ] Run them against the app. The ids may differ, use the id that `POST` returned.
- [ ] Export the collection (Collection v2.1) to `postman/jibli.postman_collection.json`.

## Hints

1. A body for `POST` and `PUT` is raw JSON, for example `{"restaurant": "Mamma Chemi", "product": "Plat Healthy", "total": 25.0}`.
2. Set the header `Content-Type: application/json` (Postman does it for raw JSON bodies).
3. The three `GET`, `PUT` and `DELETE` by id requests need an id that exists. Create an order first.
4. Stop the app with Ctrl+C when you are done.

## Check yourself

```bash
./mvnw -q test -Dtest='Step32*'
```

Green means the exported file is valid JSON and contains the five kinds of request on `/orders`.

## Stretch

- Add a test script in Postman that checks the status code of the `POST` request is 201.

## Solution notes

A collection is just a JSON file, so it can be committed, reviewed and shared like code. The `{{baseUrl}}` variable lets the same requests run against your laptop today and a server later. A common mistake is hard-coding `localhost:8080` in every request, then changing the port means editing five places.
