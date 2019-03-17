# 8. Use swagger for api documentation

Date: 2019-03-17

## Status

Accepted

## Context

We need to help API users to understand how use our API by providing the documentation

## Decision

We use swagger with swagger-ui

## Consequences

- There are two endpoint with documenation: `/swagger-ui.html`(GUI) and `/v2/api-docs` (JSON)
- All endpoints in our application will be documented in automatic fashion
- We can customize documentation using swagger annotations like `@Api`, `@ApiOperation`, `@ApiModel` etc.
- This could potential bring the security issue, be aware that by default all endpoints are included in documentation
