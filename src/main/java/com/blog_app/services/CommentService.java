package com.blog_app.services;

import com.blog_app.dtos.CommentRequestDto;
import com.blog_app.dtos.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto addComment(Long postId, CommentRequestDto dto);

    List<CommentResponseDto> getCommentsByPost(Long postId);

    void deleteComment(Long id);
}
