CREATE TABLE movie_crew (
                            id BIGSERIAL PRIMARY KEY,
                            movie_id BIGINT NOT NULL,
                            crew_member_id BIGINT NOT NULL,
                            UNIQUE (movie_id, crew_member_id),
                            FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE,
                            FOREIGN KEY (crew_member_id) REFERENCES crew_member(id) ON DELETE CASCADE
);
