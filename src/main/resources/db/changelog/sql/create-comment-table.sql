CREATE TABLE comment (
                         id BIGSERIAL PRIMARY KEY,
                         rating SMALLINT NOT NULL CHECK (rating >= 1 AND rating <= 10),
                         review VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         user_id BIGINT NOT NULL,
                         movie_id BIGINT NOT NULL,
                         UNIQUE (movie_id, user_id),
                         FOREIGN KEY (user_id) REFERENCES usr(id),
                         FOREIGN KEY (movie_id) REFERENCES movie(id)
);