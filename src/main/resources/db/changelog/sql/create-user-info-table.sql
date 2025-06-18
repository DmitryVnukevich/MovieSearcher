CREATE TABLE user_info (
                           id BIGSERIAL PRIMARY KEY,
                           user_id BIGINT NOT NULL UNIQUE,
                           content_type_preference VARCHAR(50),
                           min_rating SMALLINT CHECK (min_rating >= 1 AND min_rating <= 10),
                           max_rating SMALLINT CHECK (max_rating >= 1 AND max_rating <= 10),
                           min_duration SMALLINT CHECK (min_duration >= 1 AND min_duration <= 32767),
                           max_duration SMALLINT CHECK (max_duration >= 1 AND max_duration <= 32767),
                           preferred_age_rating VARCHAR(50),
                           FOREIGN KEY (user_id) REFERENCES usr(id) ON DELETE CASCADE
);