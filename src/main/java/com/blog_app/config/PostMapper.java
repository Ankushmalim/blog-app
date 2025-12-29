package com.blog_app.config;

import com.blog_app.dtos.PostRequestDto;
import com.blog_app.dtos.PostResponseDto;
import com.blog_app.entities.Post;
import com.blog_app.entities.User;
import com.blog_app.enums.PostStatus;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post toEntity(PostRequestDto dto, User user) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUser(user);
        post.setStatus(PostStatus.PUBLISHED);
        return post;
    }

    public PostResponseDto toDto(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getStatus().name(),
                post.getUser().getUsername(),
                post.getCreatedAt()
        );
    }
}
