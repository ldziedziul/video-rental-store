# 2. Create modular monolith

Date: 2019-03-09

## Status

Accepted

## Context

Our application should have maintainable and flexible architecture.

## Decision

We create a modular application. Each function module is has only limited entry point and hide its own implementation details along with persistence mechanism.

## Consequences

- It's easier to use modules since entrypoint services are public and avaiable on the top of other packages
- Domain models of different modules are seperate this there's no relation between them which can lead to desynchronization, e.g. when we delete entity from one module while keeping entites referencing deleted one by id
- It's much more easy to make a complex refactoring of module implementation since other modules don't have access to this details
- Application is ready to be split into seperate microservices if needed. The only requirement is to provide http-based client for communication with other module. 
At current stage not all operations are exported with REST endpoint so there would be need to add rest controllers but it should be an easy task to do
- If we need only to inform another module about specific situation like renting a film then we can and proboably should use events of some sort, even Spring Events are good starting point to decople two modules.



