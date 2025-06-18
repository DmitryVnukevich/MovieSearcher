CREATE TABLE movie_genre (
                             movie_id BIGINT NOT NULL,
                             genre_id SMALLINT NOT NULL,
                             PRIMARY KEY (movie_id, genre_id),
                             FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE
);