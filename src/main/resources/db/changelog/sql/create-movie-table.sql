CREATE TABLE movie (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description VARCHAR(255) NOT NULL,
                       release_date DATE NOT NULL,
                       duration SMALLINT NOT NULL CHECK (duration >= 1 AND duration <= 32767),
                       poster TEXT NOT NULL,
                       age_rating VARCHAR(50) NOT NULL,
                       content_type VARCHAR(50) NOT NULL
);