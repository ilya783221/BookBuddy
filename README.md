# 📚 BookBuddy API

BookBuddy — REST API приложение для управления книгами, пользователями и отзывами.

Проект разработан на Java + Spring Boot + PostgreSQL с использованием Docker и Liquibase.

---

## 🚀 Технологии

- Java 17
- Spring Boot 3.5
- Spring Web (REST API)
- Spring Data JPA
- Hibernate
- PostgreSQL
- Liquibase (миграции базы данных)
- MapStruct (маппинг DTO ↔ Entity)
- Bean Validation (Jakarta Validation)
- Swagger / OpenAPI (springdoc)
- Docker
- Lombok

---

## 🧰 Lombok

Используется для уменьшения boilerplate-кода:

- `@Data` — геттеры/сеттеры
- `@Builder` — паттерн Builder
- `@NoArgsConstructor` — конструктор без параметров
- `@AllArgsConstructor` — конструктор со всеми полями
- `@RequiredArgsConstructor` — конструктор для final полей (Spring DI)

---

## ⚙️ Запуск проекта

### 1. Запуск PostgreSQL через Docker

docker run -d --name postgres_bookbuddy -e POSTGRES_DB=bookbuddy -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=11111 -p 5432:5432 postgres:latest

---

### 2. Конфигурация приложения (application.yaml)

Файл находится: `src/main/resources/application.yaml`

```yaml
spring:
  application:
    name: BookBuddy

  datasource:
    url: jdbc:postgresql://localhost:5432/bookbuddy
    username: myuser
    password: 11111
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    open-in-view: false

  liquibase:
    change-log: classpath:db/changelog/db.changelog-master.yaml

springdoc:
  api-docs:
    enabled: true
  swagger-ui:
    enabled: true

server:
  port: 8080
```
---

### 3. Запуск приложения

./mvnw spring-boot:run

или через IntelliJ IDEA:
Run BookBuddyApplication

---

## 📌 API Endpoints

### 📚 Books
- GET /api/books — список книг (с пагинацией)
- GET /api/books/{id} — книга по ID
- POST /api/books — создать книгу
- PUT /api/books/{id} — обновить книгу
- DELETE /api/books/{id} — удалить книгу

---

### 👤 Users
- GET /api/users — получить всех пользователей
- GET /api/users/{id} — получить пользователя по ID
- POST /api/users — создать пользователя
- DELETE /api/users/{id} — удалить пользователя

---

### ⭐ Reviews

Работа с отзывами

- GET /api/reviews/book/{bookId} — получить отзывы по книге
- POST /api/reviews — создать отзыв

---

## 📌 API Examples

---

### 📚 Book — создать книгу (POST /api/books)

#### Request
```json
{
  "title": "Spring in Action",
  "author": "Craig Walls",
  "publishedYear": 2022,
  "genre": "Programming"
}
```

#### Response
```json
{
  "id": "47b1e16c-019d-4201-a0aa-ace93aa641a3",
  "title": "Spring in Action",
  "author": "Craig Walls",
  "publishedYear": 2022,
  "genre": "Programming"
}
```

👉 `id` создаётся автоматически в базе данных

---

### 📚 Book — получить книгу (GET /api/books/{id})

```
GET /api/books/47b1e16c-019d-4201-a0aa-ace93aa641a3
```

#### Response
```json
{
  "id": "47b1e16c-019d-4201-a0aa-ace93aa641a3",
  "title": "Spring in Action",
  "author": "Craig Walls",
  "publishedYear": 2022,
  "genre": "Programming"
}
```

---

### 👤 User — создать пользователя (POST /api/users)

#### Request
```json
{
  "name": "John Doe",
  "email": "john@mail.com"
}
```

#### Response
```json
{
  "id": "c76d61bf-fa52-4ebe-a9cb-06ff6e68766d",
  "name": "John Doe",
  "email": "john@mail.com"
}
```

---

### 👤 User — получить пользователя (GET /api/users/{id})

```
GET /api/users/c76d61bf-fa52-4ebe-a9cb-06ff6e68766d
```

---

### ⭐ Review — создать отзыв (POST /api/reviews)

#### Request
```json
{
  "userId": "c76d61bf-fa52-4ebe-a9cb-06ff6e68766d",
  "bookId": "47b1e16c-019d-4201-a0aa-ace93aa641a3",
  "rating": 5,
  "comment": "Great book!"
}
```

#### Response
```json
{
  "id": "cd7adea6-4f99-4f6a-bf7f-be7b7ab0ffc4",
  "userId": "c76d61bf-fa52-4ebe-a9cb-06ff6e68766d",
  "bookId": "47b1e16c-019d-4201-a0aa-ace93aa641a3",
  "rating": 5,
  "comment": "Great book!"
}
```

---

### ⭐ Review — получить отзывы по книге

```
GET /api/reviews/book/47b1e16c-019d-4201-a0aa-ace93aa641a3
```

#### Response
```json
[
  {
    "id": "cd7adea6-4f99-4f6a-bf7f-be7b7ab0ffc4",
    "userId": "c76d61bf-fa52-4ebe-a9cb-06ff6e68766d",
    "bookId": "47b1e16c-019d-4201-a0aa-ace93aa641a3",
    "rating": 5,
    "comment": "Great book!"
  }
]
```

---

## ⚠️ Валидация

В проекте используется Bean Validation (jakarta.validation):

- `@NotBlank` — обязательные строки (не пустые)
- `@NotNull` — обязательные поля
- `@Min` / `@Max` — числовые ограничения (диапазоны)
- `@Size` — ограничение длины строки
- `@Email` — проверка корректного email формата (например: user@mail.com)

---

## ❌ Обработка ошибок

Используется @RestControllerAdvice

Возможные ошибки:

- 400 Bad Request — ошибка валидации
- 404 Not Found — сущность не найдена
- 409 Conflict — дубликаты (например email)
- 500 Internal Server Error — неожиданные ошибки

Пример ответа:

```json
{
"timestamp": "2026-04-14T16:45:12.2886341",
"status": 404,
"error": "Not Found",
"message": "Book not found"
}
```

```json
{
"timestamp": "2026-04-14T16:47:38.3645394",
"status": 409,
"error": "Conflict",
"message": "Duplicate value violates unique constraint"
}
```

---

## 📑 Swagger UI

http://localhost:8080/swagger-ui/index.html

---

## 🐘 База данных

PostgreSQL:
- Port: 5432
- DB: bookbuddy
- User: myuser
- Password: 11111

---

## 🧬 Liquibase (миграции базы данных)

Liquibase автоматически создаёт и обновляет структуру БД при запуске приложения.

📁 Путь:
src/main/resources/db/changelog/db.changelog-master.yaml

📌 Важно:
- таблицы создаются автоматически
- вручную создавать schema не нужно

---

## 🧠 Архитектура

Controller → Service → Repository → Database

Используется DTO слой и MapStruct для преобразования данных.

---

## 👨‍💻 Автор

Проект создан в рамках обучения Spring Boot backend разработке.