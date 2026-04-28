# Jira API

REST API для управления задачами в стиле Jira. Построен на Quarkus (Java 21) с использованием JAX-RS, Hibernate ORM с Panache, MapStruct и PostgreSQL. Аутентификация — JWT.

## Технологии

- **Java 21**, **Quarkus 3.x**
- **JAX-RS** (RESTEasy) — REST-контроллеры
- **Hibernate ORM + Panache** — работа с БД (active record)
- **MapStruct** — маппинг entity ↔ DTO
- **SmallRye JWT** — аутентификация по JWT токенам
- **PostgreSQL** — база данных

---

## Предварительные требования

- **Java 21+**
- **Docker** и **Docker Compose** (для PostgreSQL)
- Gradle Wrapper включён — отдельная установка Gradle не нужна

---

## Быстрый старт

```shell
./start.sh
```

Приложение доступно по адресу: `http://localhost:8080`

---

## Seed-данные (тестовый пользователь)

При старте в dev-режиме база данных пересоздаётся и заполняется:

| Поле     | Значение                                   |
|----------|--------------------------------------------|
| login    | `testuser`                                 |
| password | `password`                                 |
| projectId | `b0000000-0000-0000-0000-000000000001`    |

---

## Тестирование через Swagger UI

### Шаг 1. Открыть Swagger UI

Перейти по адресу: **http://localhost:8080/q/swagger-ui/**

---

### Шаг 2. Зарегистрировать пользователя (или использовать тестового)

> Можно пропустить, если используете `testuser` / `password`.

Найти эндпоинт **POST /v1/auth/register**, нажать **Try it out**, ввести тело запроса:

```json
{
  "login": "myuser",
  "password": "mypassword"
}
```

Нажать **Execute**. Ожидаемый ответ: `201 Created`.

---

### Шаг 3. Получить JWT токен

Найти эндпоинт **POST /v1/auth/token**, нажать **Try it out**.

Заполнить форму:

| Поле         | Значение   |
|--------------|------------|
| `grant_type` | `password` |
| `username`   | `testuser` |
| `password`   | `password` |

Нажать **Execute**. В ответе скопировать значение поля `access_token`:

```json
{
  "access_token": "eyJhbGciOiJSUzI1NiJ9...",
  "token_type": "Bearer",
  "expires_in": 3600
}
```

---

### Шаг 4. Авторизоваться в Swagger UI

1. Нажать кнопку **Authorize** (замок) в правом верхнем углу страницы.
2. В поле **BearerAuth (http, Bearer)** вставить скопированный токен **без префикса `Bearer `** — только сам токен:
   ```
   eyJhbGciOiJSUzI1NiJ9...
   ```
3. Нажать **Authorize**, затем **Close**.

Теперь все последующие запросы будут автоматически передавать заголовок `Authorization: Bearer <token>`.

---

### Шаг 5. Тестировать эндпоинты

#### Проекты

**Получить список проектов** — GET /v1/projects

Нажать **Try it out** → **Execute**. Вернёт все проекты, включая seed-проект.

**Создать проект** — POST /v1/projects

```json
{
  "name": "My Project",
  "description": "Project description"
}
```

Сохранить `id` созданного проекта для дальнейших запросов.

---

#### Задачи

**Создать задачу** — POST /v1/tasks

```json
{
  "title": "First task",
  "description": "Task description",
  "projectId": "b0000000-0000-0000-0000-000000000001"
}
```

Сохранить `id` задачи из ответа.

**Получить список задач** — GET /v1/tasks

Доступные query-параметры:
- `page` — номер страницы (по умолчанию 1)
- `limit` — размер страницы (по умолчанию 20)
- `projectId` — фильтр по проекту
- `status` — фильтр по статусу (`TODO`, `IN_PROGRESS`, `REVIEW`, `TO_TEST`, `IN_TEST`, `DONE`)

**Получить задачу по ID** — GET /v1/tasks/{taskId}

**Обновить задачу (частично)** — PATCH /v1/tasks/{taskId}

```json
{
  "title": "Updated title"
}
```

Поля `title`, `description`, `assigneeId` — все опциональны.

**Изменить статус задачи** — POST /v1/tasks/{taskId}/status

```json
{
  "status": "IN_PROGRESS"
}
```

Допустимые статусы: `TODO` → `IN_PROGRESS` → `REVIEW` → `TO_TEST` → `IN_TEST` → `DONE`

---

#### Доска (Kanban)

**Получить доску по projectId** — GET /v1/board?projectId={id}

Вернёт все задачи проекта, сгруппированные по статусам:

```json
{
  "projectId": "b0000000-0000-0000-0000-000000000001",
  "columns": {
    "TODO": [...],
    "IN_PROGRESS": [...],
    "REVIEW": [...],
    "TO_TEST": [...],
    "IN_TEST": [...],
    "DONE": [...]
  }
}
```

**Получить доску по ID проекта** — GET /v1/board/{id}

Альтернативный способ с path-параметром вместо query.

---

## Справочник эндпоинтов

| Метод  | Путь                        | Описание                            | Auth |
|--------|-----------------------------|-------------------------------------|------|
| POST   | /v1/auth/register           | Регистрация пользователя            | Нет  |
| POST   | /v1/auth/token              | Получить JWT токен                  | Нет  |
| GET    | /v1/projects                | Список проектов                     | Да   |
| POST   | /v1/projects                | Создать проект                      | Да   |
| GET    | /v1/tasks                   | Список задач (пагинация, фильтры)   | Да   |
| POST   | /v1/tasks                   | Создать задачу                      | Да   |
| GET    | /v1/tasks/{taskId}          | Получить задачу по ID               | Да   |
| PATCH  | /v1/tasks/{taskId}          | Обновить задачу (частично)          | Да   |
| POST   | /v1/tasks/{taskId}/status   | Изменить статус задачи              | Да   |
| GET    | /v1/board                   | Доска по projectId (query param)    | Да   |
| GET    | /v1/board/{id}              | Доска по ID проекта (path param)    | Да   |

---

## Полезные ссылки

- Swagger UI: http://localhost:8080/q/swagger-ui/
- OpenAPI спецификация: http://localhost:8080/openapi
- Dev UI: http://localhost:8080/q/dev/
