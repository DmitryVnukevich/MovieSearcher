package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.CommentDTO;
import com.example.moviesearcher.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public CommentDTO createComment(@Valid @RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(commentDTO);
    }

    @GetMapping("/{movieId}")
    public Page<CommentDTO> getCommentsByMovieId(
            @PathVariable Long movieId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return commentService.findCommentsByMovieId(movieId, page, size);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public CommentDTO updateComment(@PathVariable Long id, @Valid @RequestBody CommentDTO commentDTO) {
        commentDTO.setId(id);
        return commentService.updateComment(commentDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}