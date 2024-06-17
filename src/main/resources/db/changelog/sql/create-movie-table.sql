CREATE TABLE movie (
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        description TEXT,
                        release_date DATE NOT NULL,
                        duration INTERVAL NOT NULL,
                        poster_url TEXT
);