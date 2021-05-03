package com.vrucina.test.service;

import com.vrucina.test.model.command.PostCommand;
import com.vrucina.test.model.modelDTO.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<List<PostDTO>> getAllPosts();
    Optional<PostDTO> getPostById(Long id);
    Optional<Long> deletePost(Long id);
    Optional<PostDTO> updatePost(Long postID, PostCommand command);
    Optional<PostDTO> createPost(PostCommand command);
}
