package com.voyager.app.service;

import com.voyager.app.entity.User;

import java.util.Optional;

public interface UserService {
     Optional<User> getByUsername(String username);
}
