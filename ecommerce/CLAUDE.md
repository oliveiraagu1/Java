# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build
./gradlew build

# Run application (starts on http://localhost:8080)
./gradlew bootRun

# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests "br.com.ecommerce.domain.product.ProductServiceTest"

# Clean build
./gradlew clean build

# Build JAR
./gradlew bootJar
```

API docs available at `http://localhost:8080/swagger-ui.html` when running.

## Architecture

**Stack:** Java 21, Spring Boot 4.0.5, Gradle, PostgreSQL (dev), H2 (test), MapStruct, Lombok, JWT (JJWT 0.12.6), SpringDoc OpenAPI.

**Pattern:** Vertical slice — each domain packages all its layers together:

```
src/main/java/br/com/ecommerce/
├── config/               # SecurityConfig, OpenApiConfig
├── domain/
│   ├── product/          # Entity, Controller, Service, Repository, Mapper, DTOs, utils/
│   └── exception/        # Domain-specific exceptions
├── exception/            # GlobalExceptionHandler (@RestControllerAdvice), ErrorResponse
└── EcommerceApplication.java
```

When adding a new domain, follow the `domain/product/` structure as the template.

## Key Conventions

- **DTOs:** Separate `*Request` (input with validation) and `*Response` (output) classes per domain.
- **Mapping:** Use MapStruct (`*Mapper` interface) for entity ↔ DTO conversion — never map manually in service/controller.
- **DI:** Constructor injection via Lombok `@RequiredArgsConstructor` — no `@Autowired`.
- **Exceptions:** Throw domain-specific exceptions (e.g., `ProductNotFoundException`) from the service layer; `GlobalExceptionHandler` converts them to HTTP responses with `ErrorResponse`.
- **Slug generation:** `Product` entity uses `@PrePersist`/`@PreUpdate` + `SlugUtils` to auto-generate URL-friendly slugs.
- **Pagination:** Service methods accept `Pageable`; controller passes it from request params.

## Profiles & Configuration

| Profile | Database | DDL |
|---------|----------|-----|
| `dev` (default) | PostgreSQL via Neon.tech | `update` |
| `test` | H2 in-memory | `create-drop` |

Test config lives in `src/test/java/resources/application-test.yml`.

## Security

CSRF is disabled and all endpoints are currently open (JWT infrastructure is included but not yet enforced). Future auth work should wire JWT filter into `SecurityConfig`.

## Testing Patterns

- **Unit tests:** Mockito (`@Mock`, `@InjectMocks`, `@ExtendWith(MockitoExtension.class)`) with AssertJ assertions.
- **Integration tests:** `@SpringBootTest` with H2 via the `test` profile.
- Place tests mirroring the main source tree under `src/test/java/br/com/ecommerce/`.
