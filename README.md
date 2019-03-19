Video Rental Store Application
==============

# Requirements

- JDK 11

# Build

run `./gradlew build` to test and build the project

## Test results

- [Unit and integration tests](build/reports/tests/test/index.html)
- [Acceptance tests](build/reports/cucumber/cucumber-html-reports/overview-features.html)

# Run

To start the application just run `./gradlew bootRun`

# Documentation

## REST endpoints

Application provides a REST API documentation using swagger.
You can check it by [starting the application](#run) and opening the following urls:

- http://localhost:8080/swagger-ui.html
- http://localhost:8080/v2/api-docs

## Architecture Decision Records

[0001-record-architecture-decisions.md](docs/adr/0001-record-architecture-decisions.md)
[0002-create-modular-monolith.md](docs/adr/0002-create-modular-monolith.md)
[0003-use-package-scope.md](docs/adr/0003-use-package-scope.md)
[0004-use-uuid-for-ids.md](docs/adr/0004-use-uuid-for-ids.md)
[0005-assume-films-are-always-available.md](docs/adr/0005-assume-films-are-always-available.md)
[0006-use-api-contracts.md](docs/adr/0006-use-api-contracts.md)
[0007-introduce-acceptance-tests.md](docs/adr/0007-introduce-acceptance-tests.md)
[0008-use-swagger-for-api-documentation.md](docs/adr/0008-use-swagger-for-api-documentation.md)
[0009-renting-film-for-different-duration.md](docs/adr/0009-renting-film-for-different-duration.md)
[0010-handling-nonexisting-customers-in-bonus-module.md](docs/adr/0010-handling-nonexisting-customers-in-bonus-module.md)
