CREATE TABLE user_info_directors (
                                     user_info_id BIGINT NOT NULL,
                                     director_id BIGINT NOT NULL,
                                     PRIMARY KEY (user_info_id, director_id),
                                     FOREIGN KEY (user_info_id) REFERENCES user_info(id) ON DELETE CASCADE
);