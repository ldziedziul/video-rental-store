# 3. Use package scope where possible

Date: 2019-03-11

## Status

Accepted

## Context

We need to hide implementation details to be ready for modularization and scaling of the application

## Decision

Use package scope for implementation classes and for persistence and hide them in subpackages (e.g. impl, web)

## Consequences

- There is no access to core logic from web layer and from other packages.
- Spring and JPA can work with them
- Interface used as an entrypoint for logic doesn't have any information about implementation
which can be helpful in tests (easier mocking)

