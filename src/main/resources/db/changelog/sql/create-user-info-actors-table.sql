CREATE TABLE user_info_actors (
                                  user_info_id BIGINT NOT NULL,
                                  actor_id BIGINT NOT NULL,
                                  PRIMARY KEY (user_info_id, actor_id),
                                  FOREIGN KEY (user_info_id) REFERENCES user_info(id) ON DELETE CASCADE
);