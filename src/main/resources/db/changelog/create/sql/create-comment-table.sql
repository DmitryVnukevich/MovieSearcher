CREATE TABLE comment (
                          id BIGSERIAL PRIMARY KEY,
                          rating SMALLINT NOT NULL CHECK (rating >= 1 AND rating <= 10),
                          review TEXT NOT NULL,
                          created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                          user_id BIGINT NOT NULL,
                          movie_id BIGINT,
                          series_id BIGINT,
                          CONSTRAINT fk_user
                              FOREIGN KEY(user_id)
                                  REFERENCES user(id)
                                  ON DELETE CASCADE,
                          CONSTRAINT fk_movie
                              FOREIGN KEY(movie_id)
                                  REFERENCES movie(id)
                                  ON DELETE CASCADE,
                          CONSTRAINT fk_series
                              FOREIGN KEY(series_id)
                                  REFERENCES series(id)
                                  ON DELETE CASCADE
);