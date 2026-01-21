package com.blog_app.config;

import com.blog_app.dtos.CommentRequestDto;
import com.blog_app.dtos.CommentResponseDto;
import com.blog_app.entities.Comment;
import com.blog_app.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentRequestDto dto, User user) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setUser(user);
        return comment;
    }

    public CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getCreatedAt()
        );
    }
}
