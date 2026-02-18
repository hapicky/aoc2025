# Requirements

- Java 17 or later
- sbt

# Generate boilerplate code

```sh
sbt "genPart1 day01"
sbt "genPart2 day01"
```

# Run

```sh
sbt "runMain day01.Part1 input/day01/sample.txt"
```

# Test

```sh
sbt test
sbt "testOnly day01.Part1Test"
```
