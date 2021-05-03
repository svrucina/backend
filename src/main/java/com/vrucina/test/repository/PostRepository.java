package com.vrucina.test.repository;

import com.vrucina.test.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    List<Post> findAll();
    @Override
    void deleteById(Long id);
    @Override
    Optional<Post> findById(Long id);
}
