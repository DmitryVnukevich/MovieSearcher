package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.CommentDTO;
import com.example.moviesearcher.entity.Comment;
import com.example.moviesearcher.mapper.CommentMapper;
import com.example.moviesearcher.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public CommentDTO saveComment(CommentDTO commentDTO) {
        Comment comment = CommentMapper.INSTANCE.commentDTOToComment(commentDTO);
        comment.setCreatedAt(new Date());
        comment = commentRepository.save(comment);
        return CommentMapper.INSTANCE.commentToCommentDTO(comment);
    }

    public List<CommentDTO> findAllComments() {
        return commentRepository.findAll().stream()
                .map(CommentMapper.INSTANCE::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO findCommentById(Long id) {
        return commentRepository.findById(id)
                .map(CommentMapper.INSTANCE::commentToCommentDTO)
                .orElse(null);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public CommentDTO updateComment(CommentDTO commentDTO) {
        Comment comment = CommentMapper.INSTANCE.commentDTOToComment(commentDTO);
        comment = commentRepository.save(comment);
        return CommentMapper.INSTANCE.commentToCommentDTO(comment);
    }
}
