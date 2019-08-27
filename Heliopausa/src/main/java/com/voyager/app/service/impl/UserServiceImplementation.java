package com.voyager.app.service.impl;

import com.voyager.app.entity.User;
import com.voyager.app.service.UserService;
import com.voyager.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
