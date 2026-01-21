package com.blog_app.dtos;

import com.blog_app.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String content;
    private String user;
    private LocalDateTime createdAt;
}