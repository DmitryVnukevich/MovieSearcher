CREATE TABLE episode (
                          id BIGSERIAL PRIMARY KEY,
                          season SMALLINT NOT NULL,
                          episode SMALLINT NOT NULL,
                          title VARCHAR(100) NOT NULL,
                          description TEXT,
                          release_date DATE NOT NULL,
                          duration INTERVAL NOT NULL,
                          series_id BIGINT NOT NULL,
                          CONSTRAINT fk_series
                              FOREIGN KEY(series_id)
                                  REFERENCES series(id)
                                  ON DELETE CASCADE
);