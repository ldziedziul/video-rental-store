# 10. Handling nonexisting customers in bonus module

Date: 2019-03-20

## Status

Accepted

## Context

Application can be asked about bonus points for nonexisting customer. Should we throw error or just return 0

## Decision

We simply return 0 but still throw error if id is not valid UUID value

## Consequences

- Implementation is simpler and more efficient since it doesn't have to call customer module to check if the customer exists
- This could be potentially misleading if somebody will rely on this information to determine if customer exists or not. In such situation customer module should be asked.


