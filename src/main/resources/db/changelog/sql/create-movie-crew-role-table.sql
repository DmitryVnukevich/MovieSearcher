CREATE TABLE movie_crew_role (
                                 movie_crew_id BIGINT NOT NULL,
                                 role VARCHAR(50) NOT NULL,
                                 PRIMARY KEY (movie_crew_id, role),
                                 FOREIGN KEY (movie_crew_id) REFERENCES movie_crew(id) ON DELETE CASCADE
);