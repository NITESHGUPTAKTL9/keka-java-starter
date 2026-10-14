# Keka Java/Spring Boot live-coding starter

Minimal Java 17 + Spring Boot project with:
- JSON POST endpoint
- Bean validation
- Controller / service / repository separation
- In-memory storage
- SLF4J logging
- Configured `RestClient` with connect/read timeouts
- A basic Spring context test

## Prerequisites
- JDK 17+
- Maven 3.9+
- IntelliJ IDEA (or your preferred Java IDE)

Check:
```bash
java -version
mvn -version
```

## Run
From this directory:
```bash
mvn spring-boot:run
```

## Test the POST endpoint
```bash
curl -i -X POST http://localhost:8080/api/tasks \
  -H 'Content-Type: application/json' \
  -d '{"title":"Prepare for Keka interview"}'
```

Expected response: HTTP `201 Created`, with JSON similar to:
```json
{"id":1,"title":"Prepare for Keka interview","status":"CREATED"}
```

Invalid request:
```bash
curl -i -X POST http://localhost:8080/api/tasks \
  -H 'Content-Type: application/json' \
  -d '{"title":"   "}'
```

List tasks:
```bash
curl http://localhost:8080/api/tasks
```

## Outbound HTTP client
A `RestClient` bean is configured in `HttpClientConfig` with connection and read timeouts. Use it if the interview problem requires calling an external API, for example:
```java
String body = restClient.get()
        .uri("https://example.com/api")
        .retrieve()
        .body(String.class);
```
Inject `RestClient` into the service that needs it. Do not make a network call unless the problem requires it.

## Suggested package structure
- `task/TaskController.java` — HTTP layer
- `task/TaskService.java` — business logic
- `task/TaskRepository.java` — in-memory storage
- `task/*Request.java`, `task/*Response.java` — API models
- `config/HttpClientConfig.java` — shared HTTP client config

## Interview-day checklist
1. Run the app once before the call.
2. Run the POST cURL command and confirm the JSON response.
3. Keep this project open in your IDE.
4. Disable Copilot, AI autocomplete, ChatGPT, Claude, and other AI coding tools as Keka requested.
5. During the round, clarify requirements first, then implement the smallest correct end-to-end slice.
6. After the interview, inspect for secrets and personal data before pushing the code to GitHub.

Note: in-memory data resets when the application restarts. This is intentional for the interview starter.


[//]: # (http://localhost:8080/swagger-ui/index.html#/task-controller/create)