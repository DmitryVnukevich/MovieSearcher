package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.CommentDTO;
import com.example.moviesearcher.entity.Comment;
import com.example.moviesearcher.repository.CommentRepository;
import com.example.moviesearcher.repository.MovieRepository;
import com.example.moviesearcher.repository.UserRepository;
import static com.example.moviesearcher.mapper.CommentMapper.COMMENT_MAPPER;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    @Transactional
    public CommentDTO saveComment(CommentDTO commentDTO) {
        movieRepository.findById(commentDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + commentDTO.getMovieId()));
        userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + commentDTO.getUserId()));

        if (commentDTO.getCreatedAt() == null) {
            commentDTO.setCreatedAt(new Date());
        }

        Comment comment = COMMENT_MAPPER.commentDTOToComment(commentDTO);
        comment = commentRepository.save(comment);
        CommentDTO result = COMMENT_MAPPER.commentToCommentDTO(comment);
        userRepository.findById(comment.getUserId())
                .ifPresent(user -> result.setUsername(user.getUsername()));
        return result;
    }

    public Page<CommentDTO> findCommentsByMovieId(Long movieId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Comment> commentPage = commentRepository.findByMovieId(movieId, pageable);
        return commentPage.map(this::enrichCommentDTOWithUsername);
    }

    @Transactional
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new IllegalArgumentException("Comment not found with ID: " + id);
        }
        commentRepository.deleteById(id);
    }

    @Transactional
    public CommentDTO updateComment(CommentDTO commentDTO) {
        Comment commentFromDb = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + commentDTO.getId()));

        if (commentDTO.getMovieId() != null) {
            movieRepository.findById(commentDTO.getMovieId())
                    .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + commentDTO.getMovieId()));
        }
        if (commentDTO.getUserId() != null) {
            userRepository.findById(commentDTO.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + commentDTO.getMovieId()));
        }

        if (commentDTO.getRating() != null) {
            commentFromDb.setRating(commentDTO.getRating());
        }
        if (commentDTO.getReview() != null) {
            commentFromDb.setReview(commentDTO.getReview());
        }
        if (commentDTO.getCreatedAt() != null) {
            commentFromDb.setCreatedAt(commentDTO.getCreatedAt());
        }
        if (commentDTO.getUserId() != null) {
            commentFromDb.setUserId(commentDTO.getUserId());
        }
        if (commentDTO.getMovieId() != null) {
            commentFromDb.setMovieId(commentDTO.getMovieId());
        }

        commentFromDb = commentRepository.save(commentFromDb);
        return enrichCommentDTOWithUsername(commentFromDb);
    }

    private CommentDTO enrichCommentDTOWithUsername(Comment comment) {
        CommentDTO commentDTO = COMMENT_MAPPER.commentToCommentDTO(comment);
        userRepository.findById(comment.getUserId())
                .ifPresent(user -> commentDTO.setUsername(user.getUsername()));
        return commentDTO;
    }
}