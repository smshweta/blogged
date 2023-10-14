package com.springboot.blogapp.springbootblogrestapi.controller;

import com.springboot.blogapp.springbootblogrestapi.payload.postDto;
import com.springboot.blogapp.springbootblogrestapi.service.impl.PostServiceImpl;
import com.springboot.blogapp.springbootblogrestapi.service.postService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private postService postservice;

    public PostController(postService postservice) {
        this.postservice = postservice;
    }
    // create blog post
    @PostMapping
    public ResponseEntity<postDto> createPost(@RequestBody postDto postDto){
        return new ResponseEntity<>(postservice.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public List<postDto> getAllPosts(){
        return postservice.getAllPosts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<postDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postservice.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<postDto> updatePost(@RequestBody postDto postDto, @PathVariable(name = "id") long id ){

        postDto postResponse = postservice.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postservice.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);

    }
}
