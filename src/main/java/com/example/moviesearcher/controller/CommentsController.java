package com.example.moviesearcher.controller;

import com.example.moviesearcher.entities.Comments;
import com.example.moviesearcher.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping
    public Comments createComment(@RequestBody Comments comment) {
        return commentsService.saveComment(comment);
    }

    @GetMapping
    public List<Comments> getAllComments() {
        return commentsService.findAllComments();
    }

    @GetMapping("/{id}")
    public Comments getCommentById(@PathVariable Long id) {
        return commentsService.findCommentById(id);
    }

    @PutMapping("/{id}")
    public Comments updateComment(@PathVariable Long id, @RequestBody Comments commentDetails) {
        commentDetails.setId(id);
        return commentsService.updateComment(commentDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentsService.deleteComment(id);
    }
}
