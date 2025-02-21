package com.example.moviesearcher.mapper;

import com.example.moviesearcher.dto.CommentDTO;
import com.example.moviesearcher.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "movie.id", target = "movieId")
    CommentDTO commentToCommentDTO(Comment comment);

    @Mapping(source = "movieId", target = "movie.id")
    Comment commentDTOToComment(CommentDTO commentDTO);
}
