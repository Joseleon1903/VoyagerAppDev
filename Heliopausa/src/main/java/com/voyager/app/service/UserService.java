package com.voyager.app.service;

import com.voyager.app.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @Author joseleon1903
 */
public interface UserService {

     Optional<User> findByEmail(String email);

     Optional<User> findByUsername(String username);

     User save(User user);

     Iterable<User> findAllUsers();
}
