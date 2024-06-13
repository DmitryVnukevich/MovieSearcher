package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Comments;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comments, Long> {
}
