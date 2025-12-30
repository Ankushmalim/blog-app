package com.blog_app.services.serviceImpl;

import com.blog_app.config.PostMapper;
import com.blog_app.dtos.PostRequestDto;
import com.blog_app.dtos.PostResponseDto;
import com.blog_app.entities.Post;
import com.blog_app.entities.User;
import com.blog_app.exception.AlreadyExistException;
import com.blog_app.exception.GlobalExceptionHandler;
import com.blog_app.exception.ResourceNotFoundException;
import com.blog_app.repositories.PostRepository;
import com.blog_app.services.PostService;
import com.blog_app.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository,
                           PostMapper postMapper,
                           UserService userService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userService = userService;
    }

    @Override
    public PostResponseDto createPost(PostRequestDto dto) {

        User user = userService.getCurrentUser();
        Post post = postMapper.toEntity(dto, user);
        if (postRepository.existsByTitle(dto.getTitle())){
            throw new AlreadyExistException("Post already exists with title: " + dto.getTitle());
        }
        log.info("Post Created!");
        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public Page<PostResponseDto> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(postMapper::toDto);
    }

    @Override
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found"));
        return postMapper.toDto(post);
    }

    @Override
    public PostResponseDto updatePost(Long id, PostRequestDto dto) {

        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found"));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post not found");
        }
        postRepository.deleteById(id);
    }
}