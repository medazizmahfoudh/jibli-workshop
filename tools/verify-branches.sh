#!/usr/bin/env bash
# Checks the exercise/solution pairs.
#   tools/verify-branches.sh          all 34 steps, plus the main branch and branch count
#   tools/verify-branches.sh 05 06    only those steps
# Switches branches, so start with a clean working tree. Goes back to the starting branch at the end.
set -u
cd "$(git rev-parse --show-toplevel)"
start=$(git rev-parse --abbrev-ref HEAD)
trap 'git switch -q "$start" 2>/dev/null' EXIT

# Steps where the step tests may fail to compile (the exercise is to declare fields,
# constructors, classes or packages the tests refer to).
compile_error_ok=" 07 08 09 12 17 21 "

failures=0
bad() { echo "FAIL  $*"; failures=$((failures + 1)); }
good() { echo "ok    $*"; }

if [ $# -gt 0 ]; then steps="$*"; else steps=$(seq -w 1 34); fi

for n in $steps; do
  ex=$(git branch --list "step-$n-*/exercise" --format='%(refname:short)')
  if [ -z "$ex" ]; then bad "step $n: no exercise branch"; continue; fi
  sol="${ex%/exercise}/solution"

  git switch -q "$ex" || { bad "$ex: cannot switch"; continue; }
  if ! ./mvnw -q -DskipTests compile >/dev/null 2>&1; then
    bad "$ex: src/main does not compile"
    continue
  fi
  if ./mvnw -q test-compile >/dev/null 2>&1; then
    if ./mvnw -q test -Dtest="Step$n*" >/dev/null 2>&1; then
      bad "$ex: the step tests already pass"
    else
      good "$ex: compiles, Step$n tests are red"
    fi
    if ./mvnw -q test -Dtest="!Step$n*" -Dsurefire.failIfNoSpecifiedTests=false >/dev/null 2>&1; then
      good "$ex: earlier tests pass"
    else
      bad "$ex: earlier tests fail"
    fi
  else
    errors=$(./mvnw -q test-compile 2>&1 | grep -E '^\[ERROR\] /.*\.java' || true)
    if [[ "$compile_error_ok" != *" $n "* ]]; then
      bad "$ex: test-compile fails but this step should compile"
    elif echo "$errors" | grep -q 'src/main/'; then
      bad "$ex: compile errors in src/main"
    elif [ "$n" != "17" ] && echo "$errors" | grep -v "Step$n" | grep -q .; then
      bad "$ex: compile errors outside the Step$n tests"
    else
      good "$ex: main compiles, Step$n tests do not compile yet (expected)"
    fi
  fi

  git switch -q "$sol" || { bad "$sol: cannot switch"; continue; }
  if ./mvnw -q test >/dev/null 2>&1; then good "$sol: whole suite passes"; else bad "$sol: tests fail"; fi
  if grep -rn "TODO(step-" src 2>/dev/null | grep -q .; then bad "$sol: TODO markers left in src"; fi
  if git diff --name-only "$ex" "$sol" | grep -E '^src/test/|^README.md$' | grep -q .; then
    bad "$sol: differs from $ex in test files or README.md"
  fi
done

if [ $# -eq 0 ]; then
  if [ "$(git rev-parse main)" = "$(git rev-parse step-34-recap/solution)" ]; then
    good "main is step-34-recap/solution"
  else
    bad "main is not step-34-recap/solution"
  fi
  count=$(git branch --list 'step-*' | wc -l)
  if [ "$count" -eq 69 ]; then good "69 step branches"; else bad "expected 69 step branches, found $count"; fi
fi

echo
if [ "$failures" -eq 0 ]; then echo "All checks passed."; else echo "$failures check(s) failed."; exit 1; fi
