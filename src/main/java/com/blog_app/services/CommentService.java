package com.blog_app.services;

import com.blog_app.dtos.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addComment(Long postId, CommentDto dto);

    List<CommentDto> getCommentsByPost(Long postId);

    void deleteComment(Long id);
}
