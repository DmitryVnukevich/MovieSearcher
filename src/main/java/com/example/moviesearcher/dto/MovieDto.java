package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.Comment;
import com.example.moviesearcher.entity.CrewMember;
import com.example.moviesearcher.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private Time duration;
    private String posterUrl;
    private List<Comment> comments;
    private List<CrewMember> movieCrew;
    private List<Genre> genres;
}

