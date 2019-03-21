# 11. Returning a film with changed pricing

Date: 2019-03-21

## Status

Accepted

## Context

It could happen that film was rented with one price and during rental the price was changed. How should calculate surcharge?

## Decision

If film was returned late than we calculate surcharge according to current pricing, but if the calculated surcharge is negative then we return 0. 
This could happen when the film type was changed from NEW to REGULAR or pricing of film types was changed

## Consequences

- We need to check surcharge if it's negative and in that case return 0



