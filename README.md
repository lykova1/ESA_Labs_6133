## Сравнение JAX-RS и Spring REST

### JAX-RS
JAX-RS (Jakarta RESTful Web Services) — это стандарт для создания RESTful
веб-сервисов на Java. Он определяет набор аннотаций и правил
(`@Path`, `@GET`, `@POST`, `@Produces`, `@Consumes`), но не предоставляет
готовую реализацию. Для работы требуется выбор и настройка
конкретного провайдера (Jersey, RESTEasy, Apache CXF и др.).

Преимущества:
- является стандартом и не привязан к конкретному фреймворку;
- переносим между различными реализациями;
- хорошо подходит для Jakarta EE приложений.

Недостатки:
- требует дополнительной настройки окружения;
- работа с сериализацией JSON/XML зависит от выбранной реализации;
- меньше встроенных средств для интеграции с другими слоями приложения.

---

### Spring REST
Spring REST (на базе Spring MVC) — это подход к разработке REST API,
использующий инфраструктуру Spring Framework. REST-контроллеры
создаются с помощью аннотаций `@RestController`, `@RequestMapping`,
`@GetMapping`, `@PostMapping` и др.

Преимущества:
- удобная и лаконичная реализация REST API;
- встроенная поддержка JSON и XML через механизм `HttpMessageConverter`;
- простое управление форматами данных с помощью `produces` и `consumes`;
- удобное формирование HTTP-ответов и кодов статуса.

Недостатки:
- не является стандартом;
- привязан к экосистеме Spring.

---

### Выбор технологии
Для данной лабораторной работы выбран **Spring REST**, так как он
обеспечивает более простую реализацию REST API и удобную поддержку
нескольких форматов представления данных (JSON и XML), что напрямую
соответствует требованиям задания. Кроме того, Spring REST предоставляет
понятные средства для обработки HTTP-статусов и интеграции с бизнес-логикой.
## Используемая предметная область (Задание 2)

Использовано приложение **Library** из практической работы №2.

Сущности:
- **Author**
- **Book**

Связь:
- один автор может иметь несколько книг (One-To-Many);
- книга связана с одним автором (Many-To-One).

---

## REST API

Реализованы REST-контроллеры:
- `/api/authors`
- `/api/books`

Поддерживаемые операции:
- `GET` — получение списка объектов;
- `POST` — добавление нового объекта;
- `PUT` — редактирование объекта;
- `DELETE` — удаление объекта.

API поддерживает:
- **JSON**
- **XML**

Поддержка форматов реализована через:
- `produces = application/json | application/xml`
- `consumes = application/json | application/xml`

---

## XML + XSL-преобразование

Для XML-ответов разработаны XSL-шаблоны:
- `authors.xsl`
- `books.xsl`

XSL-файлы размещены в статической части веб-приложения (`/xsl`).

При открытии XML-ответа в браузере данные автоматически
отображаются в виде HTML-страницы с таблицами и навигацией.

---
## Добавление XSL в XML-ответы
Для выполнения требования о подключении XSL-преобразования
в начало XML-ответов используется ручное формирование XML.

Перед сериализованными данными в XML добавляется инструкция
`xml-stylesheet`, указывающая путь к соответствующему XSL-файлу.

---

## Демонстрация функционала:

### Просмотр данных
- Просмотр списка авторов
- Просмотр списка книг
<img width="1030" height="400" alt="image" src="https://github.com/user-attachments/assets/8559f4bf-1ee0-4ef9-a0ba-dcb795928473" />
<img width="1849" height="754" alt="image" src="https://github.com/user-attachments/assets/7b7a8357-84f9-4a37-be27-eb66c70f6fa4" />


---

### Управление авторами
- Добавление автора
- Редактирование автора
- Удаление автора
<img width="939" height="683" alt="image" src="https://github.com/user-attachments/assets/0b6f8b42-a534-4ed5-96bc-730c658cf87e" />
<img width="897" height="657" alt="image" src="https://github.com/user-attachments/assets/861e34f2-0466-4629-88c0-61e73caf7399" />
<img width="1727" height="686" alt="image" src="https://github.com/user-attachments/assets/daeacd2f-1c01-4268-9084-a23d71343deb" />
<img width="879" height="642" alt="image" src="https://github.com/user-attachments/assets/88fbe926-7e2a-4559-85ce-de9a0353c1d3" />
<img width="1732" height="562" alt="image" src="https://github.com/user-attachments/assets/d9a146e8-744a-4a91-9331-0a4426a4a510" />

<img width="863" height="672" alt="image" src="https://github.com/user-attachments/assets/0d84216d-496f-4c44-a4b5-d9b0ce478cbf" />
<img width="868" height="642" alt="image" src="https://github.com/user-attachments/assets/cbf6f264-25b4-45da-b6af-5c95b2424800" />
<img width="1763" height="627" alt="image" src="https://github.com/user-attachments/assets/372ef4ed-9c49-4b30-9998-20165fbb57ec" />

---

### Управление книгами
- Добавление книги
- Редактирование книги
- Удаление книги
<img width="863" height="696" alt="image" src="https://github.com/user-attachments/assets/d75ebd7f-7371-40de-a799-fe6db5b9b500" />
<img width="866" height="694" alt="image" src="https://github.com/user-attachments/assets/63a76f23-9743-4d3f-9aac-ba1143f47004" />
<img width="1750" height="670" alt="image" src="https://github.com/user-attachments/assets/55c104c6-1456-4ad8-9974-6e447defdf18" />
<img width="918" height="698" alt="image" src="https://github.com/user-attachments/assets/d0f46100-bf01-4dd7-8193-c9df6d4a4bce" />
<img width="870" height="698" alt="image" src="https://github.com/user-attachments/assets/5ddff4e8-b4af-4bc0-a977-33e3827f9295" />
<img width="912" height="635" alt="image" src="https://github.com/user-attachments/assets/d12f86dd-3809-4c21-989d-f7768400a87e" />
<img width="1791" height="573" alt="image" src="https://github.com/user-attachments/assets/7364a82a-6665-4b4f-a25d-8e5acffddd42" />


---


