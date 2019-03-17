# 9. Renting film for different duration

Date: 2019-03-17

## Status

Accepted

## Context

Films are rented for a given amount of days. In the task specification there's no clear information if it's allowed to rent film for different number of days in a single rental process.

## Decision

Each film can be rented with different amount of days

## Consequences

- Requests are more complex since each film entry in the rental request has to have a separate field with number of rental days
- System is more flexible



