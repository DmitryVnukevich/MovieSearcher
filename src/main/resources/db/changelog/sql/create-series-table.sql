CREATE TABLE series (
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        description TEXT,
                        start_date DATE NOT NULL,
                        end_date DATE,
                        poster_url TEXT
);