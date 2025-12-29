package com.blog_app.controllers;

import com.blog_app.dtos.PostRequestDto;
import com.blog_app.dtos.PostResponseDto;
import com.blog_app.services.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponseDto createPost(
            @RequestBody @Valid PostRequestDto dto) {
        return postService.createPost(dto);
    }

    @GetMapping
    public Page<PostResponseDto> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort) {

        String[] sortArr = sort.split(",");
        Pageable pageable = PageRequest.of(
                page, size, Sort.by(
                        Sort.Direction.fromString(sortArr[1]),
                        sortArr[0])
        );
        return postService.getAllPosts(pageable);
    }

    @GetMapping("/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(
            @PathVariable Long id,
            @RequestBody @Valid PostRequestDto dto) {
        return postService.updatePost(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "Post deleted successfully";
    }
}
