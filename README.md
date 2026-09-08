# dr-demo-claims-processor

Processes and adjudicates insurance claims.

## Overview

`dr-demo-claims-processor` is a Spring Boot service in the claims domain.

## Build

```bash
./mvnw clean package
java -jar target/dr-demo-claims-processor.jar
```

## Layout

- `src/main/java/com/oodles/drdemoclaimsprocessor/AccountController.java` — HTTP surface
- `src/main/java/com/oodles/drdemoclaimsprocessor/AccountRepository.java` — JDBC access
- `src/main/java/com/oodles/drdemoclaimsprocessor/SessionCodec.java` — session encoding
- `src/test/java/com/oodles/drdemoclaimsprocessor/FixtureTest.java` — hostile-input fixtures
