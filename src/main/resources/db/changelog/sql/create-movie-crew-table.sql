CREATE TABLE movie_crew (
                            id BIGSERIAL PRIMARY KEY,
                            movie_id BIGINT NOT NULL,
                            crew_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            CONSTRAINT fk_movie
                                FOREIGN KEY(movie_id)
                                    REFERENCES movie(id)
                                    ON DELETE CASCADE,
                            CONSTRAINT fk_crew
                                FOREIGN KEY(crew_id)
                                    REFERENCES crew_member(id)
                                    ON DELETE CASCADE,
                            CONSTRAINT fk_role
                                FOREIGN KEY(role_id)
                                    REFERENCES role(id)
                                    ON DELETE CASCADE
);
