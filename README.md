### Api Endpont Workflow
- Any api endpont hit -> calls ApiExecutor with input(SERVICE_CLASS, method)
- ApiExecutor -> creates proxy and executes method on proxy
- proxy -> executes method as described by invocation handler
- invocation handler -> executes the method with extra logic
- extra logic -> TBD