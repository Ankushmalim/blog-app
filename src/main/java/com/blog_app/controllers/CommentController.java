package com.blog_app.controllers;

import com.blog_app.dtos.CommentDto;
import com.blog_app.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentDto addComment(
            @PathVariable Long postId,
            @RequestBody @Valid CommentDto dto) {
        return commentService.addComment(postId, dto);
    }

    @GetMapping
    public List<CommentDto> getComments(
            @PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @DeleteMapping("/{id}")
    public String deleteComment(
            @PathVariable Long id) {
        commentService.deleteComment(id);
        return "Comment deleted successfully";
    }
}