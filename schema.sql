-- СКРИПТ СОЗДАНИЯ СХЕМЫ БД "Библиотека"
-- Сущности: authors, books

DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;


CREATE TABLE authors (
    id          BIGSERIAL       PRIMARY KEY,
    first_name  VARCHAR(100)    NOT NULL,
    last_name   VARCHAR(100)    NOT NULL,
    birth_year  INTEGER,
    country     VARCHAR(100)
);


CREATE TABLE books (
    id            BIGSERIAL       PRIMARY KEY,
    title         VARCHAR(255)    NOT NULL,
    isbn          VARCHAR(20),
    publish_year  INTEGER,
    pages         INTEGER,
    genre         VARCHAR(100),
    author_id     BIGINT          NOT NULL,

    CONSTRAINT fk_book_author FOREIGN KEY (author_id)
        REFERENCES authors (id)
        ON DELETE CASCADE,

    CONSTRAINT uk_book_isbn UNIQUE (isbn)
);





INSERT INTO authors (first_name, last_name, birth_year, country)
VALUES
    ('George', 'Orwell', 1903, 'United Kingdom'),
    ('J.K.', 'Rowling', 1965, 'United Kingdom'),
    ('Leo', 'Tolstoy', 1828, 'Russia');

INSERT INTO books (title, isbn, publish_year, pages, genre, author_id)
VALUES
    ('1984',           '9780451524935', 1949, 328,  'Dystopia',        1),
    ('Animal Farm',    '9780451526342', 1945, 112,  'Political satire', 1),
    ('Harry Potter and the Philosopher''s Stone', '9780747532699', 1997, 223, 'Fantasy', 2),
    ('Harry Potter and the Chamber of Secrets',   '9780747538493', 1998, 251, 'Fantasy', 2),
    ('War and Peace',  '9780199232765', 1869, 1225, 'Historical novel', 3);
