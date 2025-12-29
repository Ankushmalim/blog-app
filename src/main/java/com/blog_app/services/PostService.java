package com.blog_app.services;

import com.blog_app.dtos.PostRequestDto;
import com.blog_app.dtos.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostResponseDto createPost(PostRequestDto dto);

    Page<PostResponseDto> getAllPosts(Pageable pageable);

    PostResponseDto getPostById(Long id);

    PostResponseDto updatePost(Long id, PostRequestDto dto);

    void deletePost(Long id);
}
