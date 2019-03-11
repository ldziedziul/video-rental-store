# 6. Use API contracts

Date: 2019-03-11

## Status

Accepted

## Context

We need a way to make sure that we as a producer use the same API (resources, methods, data structures) as our consumers.

## Decision

We use Spring Cloud Contract to maintain contract and generate contract tests.

## Consequences

- Our tests will fail if we change anything related to API thus we will know that we need to inform our consumers about it or rethink the change
- Spring Cloud Contract allows also to use PACT contracts which are used in frontend (JS) applications
- We have pay attention to the definition of the contract. Everything not present in the contract can be used/implement differently by the producers and consumers



