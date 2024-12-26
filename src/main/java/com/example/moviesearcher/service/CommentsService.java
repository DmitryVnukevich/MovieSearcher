package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.Comments;
import com.example.moviesearcher.repository.CommentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentsService {

    private final CommentsRepository commentRepository;

    public CommentsService(CommentsRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comments saveComment(Comments comment) {
        return commentRepository.save(comment);
    }

    public List<Comments> findAllComments() {
        return commentRepository.findAll();
    }

    public Comments findCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public Comments updateComment(Comments comment) {
        return commentRepository.save(comment);
    }
}
