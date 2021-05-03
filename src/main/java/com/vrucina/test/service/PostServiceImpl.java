package com.vrucina.test.service;

import com.vrucina.test.utils.SecurityUtils;
import com.vrucina.test.model.Post;
import com.vrucina.test.model.command.PostCommand;
import com.vrucina.test.controller.exceptions.UserNotAuthorizedException;
import com.vrucina.test.model.modelDTO.PostDTO;
import com.vrucina.test.repository.PostRepository;
import com.vrucina.test.model.User;
import com.vrucina.test.model.modelDTO.UserDTO;
import com.vrucina.test.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger("LOGGER");

    public PostServiceImpl(PostRepository postRepository, UserServiceImpl userService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<PostDTO>> getAllPosts() {
        logger.info("Loading a list of all posts.");
        List<Post> posts = postRepository.findAll();
        List<PostDTO> response = new ArrayList<>();
        posts.forEach(post -> response.add(mapPostToPostDTO(post)));
        return Optional.of(response);
    }

    @Override
    public Optional<Long> deletePost(Long id) {
        logger.info("Deleting post with ID [{}]", id.toString());
        Optional<PostDTO> post = getPostById(id);
        if(post.isPresent()){
            Integer authorID = post.get().getAuthor().getId();
            if(isUserAuthor(authorID) || SecurityUtils.isCurrentUserInRole("ROLE_ADMIN")){
                postRepository.deleteById(id);
                return Optional.of(id);
            }
            logger.info("User is not authorized to delete post with ID [{}]", id.toString());
        }
        logger.info("Post with ID [{}] not found.", id.toString());
        return Optional.empty();
    }

    @Override
    public Optional<PostDTO> updatePost(Long postID, PostCommand command) {
        Optional<Post> post = postRepository.findById(postID);
        if(post.isPresent()){
            Post saving = post.get();
            if(isUserAuthor(saving.getUser().getId())) {
                logger.info("Updating post with ID [{}]", postID.toString());
                saving.setComment(command.getComment());
                LocalDateTime timestamp = LocalDateTime.now();
                saving.setUpdated(timestamp);
                return Optional.of(mapPostToPostDTO(postRepository.save(saving)));
            }
            logger.info("User is not authorized to edit post with ID [{}]", postID.toString());
            throw new UserNotAuthorizedException();
        }
        logger.info("Post with ID [{}] not found.", postID.toString());
        return Optional.empty();
    }

    @Override
    public Optional<PostDTO> createPost(PostCommand command) {
        Optional<User> user = userRepository.findOneByUsername(getCurrentUserUsername());
        if(user.isPresent()){
            logger.info("Creating a new post by user ['{}']", user.get().getUsername());
            LocalDateTime timestamp = LocalDateTime.now();
            Post post = new Post(command.getComment(), timestamp, user.get());
            return Optional.of(mapPostToPostDTO(postRepository.save(post)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PostDTO> getPostById(Long id){
        logger.info("Loading post with ID [{}]", id.toString());
        Optional<Post> post = postRepository.findById(id);
        return post.map(this::mapPostToPostDTO);
    }

    private PostDTO mapPostToPostDTO(Post post){
        Optional<UserDTO> user = UserServiceImpl.mapUserToUserDTO(post.getUser());
        return user.map(userDTO -> new PostDTO(post.getId(), post.getComment(), post.getDate(), post.getUpdated(), userDTO)).orElse(null);
    }

    private boolean isUserAuthor(Integer authorID){
            Optional<UserDTO> user = userService.findUserByUsername(getCurrentUserUsername());
        return user.map(userDTO -> userDTO.getId().equals(authorID)).orElse(false);
    }

    private String getCurrentUserUsername(){
        Optional<String> username = SecurityUtils.getCurrentUserUsername();
        return username.orElse(null);
    }
}
