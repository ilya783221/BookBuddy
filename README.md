# BookBuddy API

BookBuddy — REST API для управления книгами, пользователями и отзывами.

Приложение предоставляет HTTP API для выполнения операций с книгами, пользователями и отзывами, 
использует PostgreSQL для хранения данных и Liquibase для управления структурой базы данных.

---

## Технологический стек

* Java 17
* Spring Boot 3.5
* Spring Web
* Spring Data JPA
* Hibernate
* PostgreSQL 18
* Liquibase
* MapStruct
* Jakarta Bean Validation
* Springdoc OpenAPI
* Lombok
* Maven
* Docker
* Docker Compose

---

## Требования

Для запуска проекта в Docker необходимы:

* Docker Desktop
* Git

При запуске через Docker Compose отдельная установка Java, Maven и PostgreSQL не требуется.

---

## Запуск с использованием Docker Compose

Docker Compose является основным способом запуска приложения.

### Клонирование репозитория

```bash
git clone https://github.com/ilya783221/BookBuddy.git
cd BookBuddy
```

### Запуск

```bash
docker compose up --build
```

В процессе запуска Docker Compose:

1. собирает приложение;
2. запускает PostgreSQL;
3. ожидает готовности базы данных;
4. запускает BookBuddy;
5. выполняет Liquibase migrations;
6. запускает REST API.

После успешного запуска приложение доступно по адресу:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

### Остановка

Для остановки приложения используйте:

```bash
docker compose down
```

Команда останавливает и удаляет контейнеры, но сохраняет данные PostgreSQL в Docker volume.

Для удаления контейнеров вместе с данными PostgreSQL:

```bash
docker compose down -v
```

> Команда `docker compose down -v` удаляет Docker volume `postgres_data` и все сохранённые в нём данные базы данных.

---

## Конфигурация

Основные параметры приложения находятся в:

```text
src/main/resources/application.yml
```

Конфигурация подключения к базе данных поддерживает переменные окружения:

```yaml
spring:
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/bookbuddy}
    username: ${DB_USERNAME:myuser}
    password: ${DB_PASSWORD:11111}
```

Переменные окружения позволяют изменять параметры подключения без изменения исходного кода.

---

## Переменные окружения Docker Compose

Для настройки PostgreSQL используется файл `.env`.

Пример конфигурации находится в:

```text
.env.example
```

Пример:

```env
POSTGRES_DB=bookbuddy
POSTGRES_USER=myuser
POSTGRES_PASSWORD=11111
```

Файл `.env` создавать необязательно. При его отсутствии Docker Compose использует значения по умолчанию, определённые в `docker-compose.yml`.

Файл `.env` исключён из Git через `.gitignore`.

---

## Локальный запуск без Docker

Приложение также может быть запущено непосредственно из IntelliJ IDEA.

Для локального запуска необходимо установить:

* Java 17
* Maven
* PostgreSQL

После запуска PostgreSQL приложение можно запустить через класс:

```text
BookBuddyApplication
```

Основной конфигурационный файл:

```text
src/main/resources/application.yml
```

---

## API

### Books

| Method | Endpoint          | Description                       |
| ------ | ----------------- | --------------------------------- |
| GET    | `/api/books`      | Получение списка книг             |
| GET    | `/api/books/{id}` | Получение книги по идентификатору |
| POST   | `/api/books`      | Создание книги                    |
| PUT    | `/api/books/{id}` | Обновление книги                  |
| DELETE | `/api/books/{id}` | Удаление книги                    |

### Users

| Method | Endpoint          | Description                              |
| ------ | ----------------- | ---------------------------------------- |
| GET    | `/api/users`      | Получение списка пользователей           |
| GET    | `/api/users/{id}` | Получение пользователя по идентификатору |
| POST   | `/api/users`      | Создание пользователя                    |
| DELETE | `/api/users/{id}` | Удаление пользователя                    |

### Reviews

| Method | Endpoint                     | Description                |
| ------ | ---------------------------- | -------------------------- |
| GET    | `/api/reviews/book/{bookId}` | Получение отзывов по книге |
| POST   | `/api/reviews`               | Создание отзыва            |

---

## Примеры API-запросов

### Создание книги

`POST /api/books`

Request:

```json
{
  "title": "Spring in Action",
  "author": "Craig Walls",
  "publishedYear": 2022,
  "genre": "Programming"
}
```

Response:

```json
{
  "id": "47b1e16c-019d-4201-a0aa-ace93aa641a3",
  "title": "Spring in Action",
  "author": "Craig Walls",
  "publishedYear": 2022,
  "genre": "Programming"
}
```

Идентификатор книги создаётся автоматически.

---

### Получение книги

`GET /api/books/{id}`

```text
GET /api/books/47b1e16c-019d-4201-a0aa-ace93aa641a3
```

Response:

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

### Создание пользователя

`POST /api/users`

Request:

```json
{
  "name": "John Doe",
  "email": "john@mail.com"
}
```

Response:

```json
{
  "id": "c76d61bf-fa52-4ebe-a9cb-06ff6e68766d",
  "name": "John Doe",
  "email": "john@mail.com"
}
```

---

### Создание отзыва

`POST /api/reviews`

Request:

```json
{
  "userId": "c76d61bf-fa52-4ebe-a9cb-06ff6e68766d",
  "bookId": "47b1e16c-019d-4201-a0aa-ace93aa641a3",
  "rating": 5,
  "comment": "Great book!"
}
```

Response:

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

### Получение отзывов по книге

`GET /api/reviews/book/{bookId}`

```text
GET /api/reviews/book/47b1e16c-019d-4201-a0aa-ace93aa641a3
```

Response:

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

## Валидация данных

Для проверки входных данных используется Jakarta Bean Validation.

Используемые ограничения включают:

* `@NotBlank` — проверка обязательных строковых значений;
* `@NotNull` — проверка обязательных значений;
* `@Min` / `@Max` — ограничение числовых значений;
* `@Size` — ограничение длины строк;
* `@Email` — проверка формата электронной почты.

---

## Обработка ошибок

Для централизованной обработки исключений используется `@RestControllerAdvice`.

Основные HTTP-статусы:

| Status                    | Description                       |
| ------------------------- | --------------------------------- |
| 400 Bad Request           | Некорректные входные данные       |
| 404 Not Found             | Запрашиваемая сущность не найдена |
| 409 Conflict              | Конфликт данных                   |
| 500 Internal Server Error | Непредвиденная ошибка сервера     |

Пример ответа:

```json
{
  "timestamp": "2026-04-14T16:45:12.2886341",
  "status": 404,
  "error": "Not Found",
  "message": "Book not found"
}
```

---

## Swagger / OpenAPI

Интерактивная документация REST API предоставляется через Swagger UI.

После запуска приложения:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI specification:

```text
http://localhost:8080/v3/api-docs
```

---

## PostgreSQL

При запуске через Docker Compose PostgreSQL запускается автоматически.

Основные параметры по умолчанию:

```text
Port: 5432
Database: bookbuddy
User: myuser
```

Данные базы данных хранятся в Docker volume:

```text
postgres_data
```

Удаление контейнеров через:

```bash
docker compose down
```

не удаляет данные PostgreSQL.

---

## Liquibase

Liquibase используется для версионирования и управления изменениями структуры базы данных.

Основной файл:

```text
src/main/resources/db/changelog/db.changelog-master.yaml
```

При запуске приложения Liquibase проверяет состояние базы данных и применяет отсутствующие changesets.

Ручное создание структуры базы данных не требуется.

---

## Архитектура

Приложение использует многослойную архитектуру:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

Для разделения API-моделей и моделей базы данных используется DTO-слой.

MapStruct используется для преобразования:

```text
DTO ↔ Entity
```

---

## Структура проекта

```text
BookBuddy/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── .env.example
├── .gitignore
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## Лицензия

Проект предназначен для демонстрации REST API и контейнеризированного Spring Boot приложения.
