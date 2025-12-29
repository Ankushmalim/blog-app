package com.blog_app.config;

import com.blog_app.dtos.CommentDto;
import com.blog_app.entities.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUsername()
        );
    }
}
