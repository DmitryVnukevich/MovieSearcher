CREATE TABLE series_genre (
                               series_id BIGINT NOT NULL,
                               genre_id BIGINT NOT NULL,
                               PRIMARY KEY (series_id, genre_id),
                               CONSTRAINT fk_series
                                   FOREIGN KEY(series_id)
                                       REFERENCES series(id)
                                       ON DELETE CASCADE,
                               CONSTRAINT fk_genre
                                   FOREIGN KEY(genre_id)
                                       REFERENCES genre(id)
                                       ON DELETE CASCADE
);