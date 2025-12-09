# GitHub Profile Summarizer

A Spring Boot service that fetches a GitHub user's profile and repositories and returns a concise summary.

## Architecture & Decisions
- **Spring Boot (WebFlux client)**: WebClient for GitHub API calls, using CompleteableFuture for simultaneous queries;
- **In-memory caching**: `SummarizerService` keeps a 5-minute cache per username (simple, single-instance friendly). Redis can be added in the future.
- **404 passthrough**: GitHub 404 responses propagate back as 404 to clients; other errors return 500.
- **Java 25**: Uses Temurin 25 and Gradle wrapper for builds.
- **Dockerized**: Multi-stage Dockerfile builds the jar and runs it.

## Endpoints
- `GET /summary?username={github-username}` → `GitHubSummary` JSON.
  - `400` if `username` is missing/blank.
  - `404` if GitHub reports the user/repos are missing.
  - `500` for unexpected errors.

## Install & Run
### Prerequisites
- Docker
-or-
- JDK 25
- Gradle wrapper (included)

### Local build & run
```bash
./gradlew bootRun
# then visit http://localhost:8080/summary?username=octocat
```

### Local tests
```bash
./gradlew test
```

### Docker build & run
```bash
docker build -t github-profile-summarizer .
docker run --rm -p 8080:8080 github-profile-summarizer
# then visit http://localhost:8080/summary?username=octocat
```

## Usage Example
```bash
curl "http://localhost:8080/summary?username=octocat"
```
Response (trimmed):
```json
{
  "userName": "octocat",
  "displayName": "The Octocat",
  "avatar": "https://avatars.githubusercontent.com/u/583231?v=4",
  "geoLocation": "San Francisco",
  "email": null,
  "url": "https://github.com/octocat",
  "createdAt": "2011-01-25T18:44:36Z",
  "repos": [
    { "name": "Hello-World", "url": "https://github.com/octocat/Hello-World" }
  ]
}
```

## Notes
- Cache TTL is 5 minutes; adjust in `SummarizerService` if needed.