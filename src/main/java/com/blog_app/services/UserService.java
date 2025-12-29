package com.blog_app.services;

import com.blog_app.dtos.RegisterRequestDto;
import com.blog_app.entities.User;


public interface UserService {

    User registerUser(RegisterRequestDto dto);

    User getCurrentUser();
}
