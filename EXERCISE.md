# Step 33: curl

## Goal

Postman is nice, but servers have no screen. `curl` works everywhere, and a small script that calls the API and checks the answers is the quickest way to know that a deployed app is alive. Write that script. (Slide: Spring Boot 13/14, curl.)

## Tasks

Write `scripts/smoke-test.sh`, a bash script that:

- [ ] stops at the first error (`set -euo pipefail`);
- [ ] reads the server address from `BASE_URL`, with `http://localhost:8080` as the default;
- [ ] calls, in this order, `POST /orders`, `GET /orders`, `GET /orders/{id}`, `PUT /orders/{id}`, `DELETE /orders/{id}`, and checks the status codes 201, 200, 200, 200 and 204;
- [ ] takes the id of the new order from the `POST` response, without using `jq`;
- [ ] prints `ALL CHECKS PASSED` as its last line and exits with 0 when everything is right. On the first wrong status it prints what went wrong and exits with a non-zero code.

## Hints

1. `curl -s -o /dev/null -w '%{http_code}' URL` prints only the status code.
2. For the `POST` you need both the body and the status. `-w '\n%{http_code}'` appends the status on a new line, then `tail -n 1` takes the status and `sed '$d'` removes it from the body.
3. A body like `{"id":7,"restaurant":"Mamma Chemi"}` gives its id with `sed -E 's/.*"id":([0-9]+).*/\1/'`.
4. A small function `check name expected actual` keeps the script short.
5. Send JSON with `-H 'Content-Type: application/json' -d '{...}'`.

## Check yourself

```bash
./mvnw -q test -Dtest='Step33*'
```

The test starts the application on a random port and runs your script against it. Green means exit code 0 and the last line `ALL CHECKS PASSED`. You can also start the app yourself and run `bash scripts/smoke-test.sh`.

## Heads-up

This test needs `bash` and `curl`. On Windows run it from Git Bash or WSL. If they are missing, the test is skipped instead of failing.

## Stretch

- Make the script also check that `GET` on the deleted id now answers 404.

## Solution notes

`set -e` makes bash stop at the first failing command, `-u` catches typos in variable names, and `pipefail` makes a pipe fail when any part fails. The `-w` option of curl prints the status code, so the script can compare it with what it expects. A common mistake is only looking at the body of a response: an error page is also "a response", the status code is what tells you if the call worked.
