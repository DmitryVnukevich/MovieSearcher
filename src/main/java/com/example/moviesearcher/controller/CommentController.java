package com.example.moviesearcher.controller;

import com.example.moviesearcher.entity.Comment;
import com.example.moviesearcher.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.findAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return commentService.findCommentById(id);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        commentDetails.setId(id);
        return commentService.updateComment(commentDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
