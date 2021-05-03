package com.vrucina.test.controller;

import com.vrucina.test.model.command.PostCommand;
import com.vrucina.test.model.modelDTO.PostDTO;
import com.vrucina.test.service.PostServiceImpl;
import com.vrucina.test.controller.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostServiceImpl postService;

    @GetMapping
    public Optional<List<PostDTO>> getAllPosts(){
        return postService.getAllPosts();
    }

    @DeleteMapping
    public Long deletePost(@RequestParam Long postID) {
        return postService.deletePost(postID).orElseThrow(() ->  new PostNotFoundException(postID));
    }

    @PutMapping
    public PostDTO updatePost(@RequestParam Long postID,
                                        @Valid @RequestBody PostCommand command){
        return postService.updatePost(postID, command).orElseThrow(() -> new PostNotFoundException(postID));
    }

    @PostMapping
    public Optional<PostDTO> createPost(@Valid @RequestBody PostCommand command){
        return postService.createPost(command);
    }
}
