package com.blog_app.services.serviceImpl;

import com.blog_app.dtos.RegisterRequestDto;
import com.blog_app.entities.User;
import com.blog_app.enums.Role;
import com.blog_app.exception.BadRequestException;
import com.blog_app.exception.ResourceNotFoundException;
import com.blog_app.repositories.UserRepository;
import com.blog_app.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(RegisterRequestDto dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.ROLE_USER);

        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}

