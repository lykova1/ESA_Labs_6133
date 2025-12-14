# Практическая работа №2 — Spring MVC приложение


## Описание
Веб-приложение **Library**, разработанное с использованием Spring по типовой
трёхслойной архитектуре.  
Приложение предоставляет CRUD-операции для работы с базой данных через
веб-интерфейс.

---

## Предметная область
**Библиотека**

- **Author** — автор книги  
- **Book** — книга  

Связь: **один автор — много книг (One-To-Many)**



---

## Архитектура
- **Data layer** — JPA-сущности (`Author`, `Book`) и Spring Data JPA репозитории  
- **Business layer** — сервисы (`AuthorService`, `BookService`)  
- **Presentation layer** — Spring MVC контроллеры и Thymeleaf-шаблоны  

---

## Функциональность

### Просмотр данных
- Просмотр списка авторов
- Просмотр списка книг

<img width="2146" height="966" alt="Authors list"
src="https://github.com/user-attachments/assets/f31fdc99-10a2-46ba-be41-a1e489b55987" />

<img width="2158" height="844" alt="Books list"
src="https://github.com/user-attachments/assets/5cc01960-3ff9-478d-ba2a-dec46c26cf97" />

---

### Управление авторами
- Добавление автора
- Редактирование автора
- Удаление автора

<img width="1076" height="906" alt="Add author"
src="https://github.com/user-attachments/assets/f76d9de6-4dbf-4dd5-8a99-3d9c8e1570c5" />

<img width="1948" height="967" alt="Edit author"
src="https://github.com/user-attachments/assets/15702699-9548-4fa6-ab86-2e6e86014fe9" />

<img width="1379" height="1051" alt="Authors table"
src="https://github.com/user-attachments/assets/f30065ea-8114-4263-a120-7cee525cffe1" />

<img width="1820" height="753" alt="Delete author"
src="https://github.com/user-attachments/assets/70631176-bd24-41a0-a42e-37261a1ca5c6" />

---

### Управление книгами
- Добавление книги
- Редактирование книги
- Удаление книги

<img width="1089" height="1138" alt="Add book"
src="https://github.com/user-attachments/assets/54c4b8a3-1bbd-42d6-9d7b-32802199406c" />

<img width="2059" height="1033" alt="Edit book"
src="https://github.com/user-attachments/assets/788f6544-6e27-4871-9616-41ea7d2cd443" />

<img width="988" height="1152" alt="Books table"
src="https://github.com/user-attachments/assets/2b1b332c-74d6-44c3-a25d-8eb6345e6ff7" />

<img width="1772" height="895" alt="Delete book"
src="https://github.com/user-attachments/assets/7ae36461-27ef-4701-8cba-653af7396441" />

---

### Привязка книги к автору
При создании и редактировании книги автор выбирается из списка существующих авторов.
Связь реализована на уровне модели данных как **Many-To-One (Book → Author)**. При удалении автора удаляются его книги:

<img width="1852" height="873" alt="Select author"
src="https://github.com/user-attachments/assets/0c25278e-eb51-4de2-aaa6-1945dabd297f" />

<img width="1799" height="535" alt="Book author relation"
src="https://github.com/user-attachments/assets/5fd1c1ad-534b-4167-94c2-23c274415966" />

## Используемые технологии
- Java  
- Spring Boot  
- Spring MVC  
- Spring Data JPA (Hibernate)  
- Thymeleaf  
- PostgreSQL  
- Bootstrap  

---

## Запуск приложения
1. Настроить подключение к базе данных в `application.properties`
2. Использовать скрипт из schema.sql
3. Запустить Spring Boot приложение
4. Открыть в браузере:
   - `http://localhost:8080/books`
   - `http://localhost:8080/authors`
---





