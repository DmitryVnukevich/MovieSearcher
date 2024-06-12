CREATE TABLE crew_member (
                              id BIGSERIAL PRIMARY KEY,
                              firstname VARCHAR(50) NOT NULL,
                              lastname VARCHAR(50) NOT NULL,
                              birth_date DATE NOT NULL,
                              bio TEXT,
                              photo_url TEXT
);