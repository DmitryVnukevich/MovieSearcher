CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(30) NOT NULL,
                       email VARCHAR(20) NOT NULL UNIQUE,
                       password_hash TEXT NOT NULL,
                       role_id BIGINT NOT NULL,
                       CONSTRAINT fk_role
                           FOREIGN KEY(role_id)
                               REFERENCES role(id)
                               ON DELETE CASCADE
);
