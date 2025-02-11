package com.example.moviesearcher.repository;

import com.example.moviesearcher.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
