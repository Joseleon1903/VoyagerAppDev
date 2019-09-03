package com.voyager.app.service.impl;

import com.voyager.app.constant.ConstantApplication;
import com.voyager.app.entity.User;
import com.voyager.app.service.UserService;
import com.voyager.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Iterable<User> searchByUsernameActive(String username) {
        String name = '%'+username+'%';
        return userRepository.findByUsernameAndAndStatus(name, ConstantApplication.STATUS_A);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
}
