#!/usr/bin/env bash
# Calls the orders API with curl and checks the status codes.
# Usage: BASE_URL=http://localhost:8080 bash scripts/smoke-test.sh
set -euo pipefail

BASE_URL="${BASE_URL:-http://localhost:8080}"
JSON='Content-Type: application/json'

# check <name> <expected status> <actual status>
check() {
  if [ "$2" != "$3" ]; then
    echo "FAIL $1: expected $2 but got $3"
    exit 1
  fi
  echo "ok   $1 ($3)"
}

# POST: we need the body (for the id) and the status
response=$(curl -s -w '\n%{http_code}' -X POST "$BASE_URL/orders" -H "$JSON" \
  -d '{"restaurant":"Mamma Chemi","product":"Plat Healthy","total":25.0}')
status=$(echo "$response" | tail -n 1)
body=$(echo "$response" | sed '$d')
check "POST /orders" 201 "$status"

# pick the id out of {"id":7,...} without jq
id=$(echo "$body" | sed -E 's/.*"id":([0-9]+).*/\1/')
echo "created order $id"

status=$(curl -s -o /dev/null -w '%{http_code}' "$BASE_URL/orders")
check "GET /orders" 200 "$status"

status=$(curl -s -o /dev/null -w '%{http_code}' "$BASE_URL/orders/$id")
check "GET /orders/$id" 200 "$status"

status=$(curl -s -o /dev/null -w '%{http_code}' -X PUT "$BASE_URL/orders/$id" -H "$JSON" \
  -d '{"restaurant":"Dar Lablabi","product":"Lablabi","total":6.5}')
check "PUT /orders/$id" 200 "$status"

status=$(curl -s -o /dev/null -w '%{http_code}' -X DELETE "$BASE_URL/orders/$id")
check "DELETE /orders/$id" 204 "$status"

echo "ALL CHECKS PASSED"
