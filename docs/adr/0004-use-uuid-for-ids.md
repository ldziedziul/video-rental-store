# 4. Use UUID for ids

Date: 2019-03-1933

## Status

Accepted

## Context

We need unique id generated for each entity stored in the database. We can use generator on the database or generate values in the code.

## Decision

We use UUID for ids but stored as uuid type be readable (instead of binary representation)

## Consequences

- We know the id before saving the entity in db which allows us to be more flexible when creating relations
- There's no way to predict ids so it's more safer against iterating through consecutive numbers
- "uuid" type in db makes id rendered as text but could be a potential source of performance issues on db

