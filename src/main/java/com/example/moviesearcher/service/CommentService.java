package com.example.moviesearcher.service;

import com.example.moviesearcher.entity.Comment;
import com.example.moviesearcher.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
