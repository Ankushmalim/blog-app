package com.blog_app.repositories;

import com.blog_app.entities.Post;
import com.blog_app.enums.PostStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByStatus(PostStatus status, Pageable pageable);

    Page<Post> findByUserId(Long userId, Pageable pageable);

    boolean existsByTitle(String title);
}
