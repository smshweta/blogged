package com.springboot.blogapp.springbootblogrestapi.controller;

import com.springboot.blogapp.springbootblogrestapi.payload.PostResponse;
import com.springboot.blogapp.springbootblogrestapi.payload.postDto;
import com.springboot.blogapp.springbootblogrestapi.service.impl.PostServiceImpl;
import com.springboot.blogapp.springbootblogrestapi.service.postService;
import com.springboot.blogapp.springbootblogrestapi.utils.AppConstants;
import jakarta.validation.Valid;
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
    public ResponseEntity<postDto> createPost(@Valid @RequestBody postDto postDto){
        return new ResponseEntity<>(postservice.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy
    ){
        return postservice.getAllPosts(pageNo, pageSize, sortBy);
    }
    @GetMapping("/{id}")
    public ResponseEntity<postDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postservice.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<postDto> updatePost(@Valid @RequestBody postDto postDto, @PathVariable(name = "id") long id ){

        postDto postResponse = postservice.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postservice.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);

    }
}
