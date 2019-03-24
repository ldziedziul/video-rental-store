# 13. Database migration

Date: 2019-03-24

## Status

Accepted

## Context

We need to handle changes on the persistence layer by using migration
tools (e.g. liquibase or flyway) or one of `spring.jpa.hibernate.ddl-auto` modes

## Decision

We use `create-drop` mode

## Consequences

- Each start of application results with completely recreated
application state
