package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.CommentDTO;
import com.example.moviesearcher.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(commentDTO);
    }

    @GetMapping
    public List<CommentDTO> findCommentsByMovieId(@PathVariable Long id) {
        return commentService.findCommentsByMovieId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public CommentDTO updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        commentDTO.setId(id);
        return commentService.updateComment(commentDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public void deleteComment(@PathVariable Long id) {
        if (commentService.findCommentById(id) == null) {
            throw new IllegalArgumentException("Comment not found with ID: " + id);
        }
        commentService.deleteComment(id);
    }
}