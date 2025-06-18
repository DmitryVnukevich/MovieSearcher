CREATE TABLE user_info_genres (
                                  user_info_id BIGINT NOT NULL,
                                  genre_id SMALLINT NOT NULL,
                                  PRIMARY KEY (user_info_id, genre_id),
                                  FOREIGN KEY (user_info_id) REFERENCES user_info(id) ON DELETE CASCADE
);