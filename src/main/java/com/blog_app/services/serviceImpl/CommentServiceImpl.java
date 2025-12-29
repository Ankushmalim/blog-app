package com.blog_app.services.serviceImpl;

import com.blog_app.config.CommentMapper;
import com.blog_app.dtos.CommentDto;
import com.blog_app.entities.Comment;
import com.blog_app.entities.Post;
import com.blog_app.exception.ResourceNotFoundException;
import com.blog_app.repositories.CommentRepository;
import com.blog_app.repositories.PostRepository;
import com.blog_app.services.CommentService;
import com.blog_app.services.UserService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UserService userService,
                              CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userService = userService;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDto addComment(Long postId, CommentDto dto) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setPost(post);
        comment.setUser(userService.getCurrentUser());

        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getCommentsByPost(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post not found");
        }

        return commentRepository.findByPostId(postId)
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long id) {

        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment not found");
        }
        commentRepository.deleteById(id);
    }
}