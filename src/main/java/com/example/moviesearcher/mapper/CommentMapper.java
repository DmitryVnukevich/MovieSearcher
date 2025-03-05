package com.example.moviesearcher.mapper;

import com.example.moviesearcher.dto.CommentDTO;
import com.example.moviesearcher.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper COMMENT_MAPPER = Mappers.getMapper(CommentMapper.class);

    CommentDTO commentToCommentDTO(Comment comment);

    Comment commentDTOToComment(CommentDTO commentDTO);
}
