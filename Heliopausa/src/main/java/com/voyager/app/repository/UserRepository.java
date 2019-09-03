package com.voyager.app.repository;

import com.voyager.app.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * UserRepository
 */
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query(value ="select * from User u where u.username like ?1 and u.status = ?2", nativeQuery = true)
    Iterable<User> findByUsernameAndAndStatus(String username, String status);

}
