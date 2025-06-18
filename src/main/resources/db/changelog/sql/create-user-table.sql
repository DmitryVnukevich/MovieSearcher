CREATE TABLE usr (
                     id BIGSERIAL PRIMARY KEY,
                     username VARCHAR(30) NOT NULL UNIQUE,
                     email VARCHAR(20) NOT NULL UNIQUE,
                     roles VARCHAR(255) NOT NULL,
                     password VARCHAR(255) NOT NULL
);