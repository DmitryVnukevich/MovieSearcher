package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.CommentDTO;
import com.example.moviesearcher.entity.Comment;
import com.example.moviesearcher.repository.CommentRepository;
import com.example.moviesearcher.repository.MovieRepository;
import com.example.moviesearcher.repository.UserRepository;
import static com.example.moviesearcher.mapper.CommentMapper.COMMENT_MAPPER;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Comment comment = COMMENT_MAPPER.commentDTOToComment(commentDTO);
        comment = commentRepository.save(comment);

        CommentDTO result = COMMENT_MAPPER.commentToCommentDTO(comment);
        userRepository.findById(comment.getUserId())
                .ifPresent(user -> result.setUsername(user.getUsername()));

        return result;
    }

    public CommentDTO findCommentById(Long id) {
        return commentRepository.findById(id)
                .map(this::enrichCommentDTOWithUsername)
                .orElse(null);
    }

    public List<CommentDTO> findCommentsByMovieId(Long movieId) {
        return commentRepository.findByMovieId(movieId).stream()
                .map(this::enrichCommentDTOWithUsername)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public CommentDTO updateComment(CommentDTO commentDTO) {
        movieRepository.findById(commentDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + commentDTO.getMovieId()));

        Comment comment = COMMENT_MAPPER.commentDTOToComment(commentDTO);
        comment = commentRepository.save(comment);

        return enrichCommentDTOWithUsername(comment);
    }

    private CommentDTO enrichCommentDTOWithUsername(Comment comment) {
        CommentDTO commentDTO = COMMENT_MAPPER.commentToCommentDTO(comment);
        userRepository.findById(comment.getUserId())
                .ifPresent(user -> commentDTO.setUsername(user.getUsername()));
        return commentDTO;
    }
}