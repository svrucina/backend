package com.vrucina.test.repository;

import com.vrucina.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
}
