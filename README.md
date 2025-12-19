# Практическая работа №1 — Jakarta EE приложение


## Описание
Веб-приложение **Plant Stock**, разработанное с использованием платформы **Jakarta EE**
по типовой трёхслойной архитектуре.  
Приложение предоставляет базовые операции для работы с базой данных
(добавление, редактирование, удаление и просмотр данных) через веб-интерфейс.

Приложение развёртывается на сервере приложений **GlassFish** и использует
реляционную СУБД **PostgreSQL**. 
IDE выступила Intellij IDEA 2025

---

## Предметная область
**Склад растений**

- **Warehouse** — склад  
- **Plant** — растение  

Связь: **один склад — много растений (One-To-Many)**  
Каждое растение принадлежит одному складу (**Many-To-One**).

---

## Архитектура
Приложение реализовано в соответствии с типовой архитектурой Jakarta EE и состоит из трёх слоёв:

- **Data layer** — JPA Entity Beans (`Plant`, `Warehouse`)  
- **Business layer** — Stateless Session Beans (`PlantService`, `WarehouseServise`)  
- **Presentation layer** — Servlets (`PlantServlet`, `AddPlantServlet`) и JSP (`index.jsp`)  

Конфигурация JPA выполнена в файле `persistence.xml`, размещённом в каталоге `META-INF`.

---

## Структура проекта

```text
src
└── main
    ├── java
    │   └── com.example.jakartaeeplantstocks
    │       ├── model
    │       │   ├── Plant.java
    │       │   └── Warehouse.java
    │       │
    │       ├── service
    │       │   ├── PlantService.java
    │       │   └── WarehouseService.java
    │       │
    │       └── servlet
    │           ├── AddPlantServlet.java
    │           └── PlantServlet.java
    │
    ├── resources
    │   └── META-INF
    │       └── persistence.xml
    │
    └── webapp
        ├── index.jsp
        └── WEB-INF
            └── web.xml
```

---

## Функционал

### Просмотр данных
- Просмотр списка растений
- Просмотр количества каждого растения
- Просмотр информации о складе, на котором хранится растение

 <img width="1605" height="651" alt="image" src="https://github.com/user-attachments/assets/2e9bffd6-49ce-454a-be40-b28566b5da08" />

---

### Управление растениями
- Добавление нового растения
  
    <img width="830" height="733" alt="image" src="https://github.com/user-attachments/assets/2be9f9bc-9c7c-429f-92f4-5d561c818537" />

- Редактирование информации о растении
  
    <img width="830" height="728" alt="image" src="https://github.com/user-attachments/assets/c4fc1e74-d6e9-43d5-95da-825cb9edcdf5" />

- Удаление растения
  
    <img width="1628" height="795" alt="image" src="https://github.com/user-attachments/assets/20589dd3-f50a-4a9d-a6ac-e2e21ec53a4a" />


---

### Привязка растения к складу
При создании и редактировании растения указывается склад, на котором оно хранится.  
Связь реализована на уровне модели данных как **Many-To-One (Plant → Warehouse)**.  
Один склад может содержать несколько растений (**One-To-Many**).

---

## Структура базы данных

В базе данных используются две основные таблицы:

### Таблица `warehouse`
- `id` — идентификатор склада (PRIMARY KEY)
- `name` — название склада
- `location` — местоположение склада

### Таблица `plant`
- `id` — идентификатор растения (PRIMARY KEY)
- `name` — название растения
- `quantity` — количество
- `warehouse_id` — внешний ключ на таблицу `warehouse`

Связь между таблицами реализована через внешний ключ `warehouse_id`.

---

## Используемые технологии
- Java
- Jakarta EE
- Maven
- PostgreSQL
- JDBC
- Servlet API
- JSP
- HTML / CSS

---

## Запуск приложения

1. Установить и запустить PostgreSQL
2. Создать базу данных
3. Выполнить SQL-скрипт создания таблиц:

```sql
CREATE TABLE warehouse (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100)
);

CREATE TABLE plant (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    warehouse_id INT REFERENCES warehouse(id)
);
```

4. При необходимости добавить тестовые данные
5. Настроить подключение к базе данных в конфигурации приложения
6. Собрать проект с помощью Maven

<img width="1820" height="362" alt="image" src="https://github.com/user-attachments/assets/f4a42463-5f78-4b47-8c5c-d0f01b35899a" />

8. Развернуть .war файл на сервере приложений

<img width="1955" height="138" alt="image" src="https://github.com/user-attachments/assets/54314e0f-a32c-4d20-b92d-1bad933f11d2" />

10. Открыть приложение в браузере

<img width="2879" height="1119" alt="image" src="https://github.com/user-attachments/assets/53c25aee-bf94-4f4f-8383-a371f4724ba5" />

