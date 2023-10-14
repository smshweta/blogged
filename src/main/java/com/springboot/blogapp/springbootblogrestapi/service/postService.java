package com.springboot.blogapp.springbootblogrestapi.service;

import com.springboot.blogapp.springbootblogrestapi.payload.postDto;

import java.util.List;

public interface postService {
    postDto createPost(postDto postDto);
    List<postDto> getAllPosts();
    postDto getPostById(long id);

    postDto updatePost(postDto postDto, long id );

    void deletePost(long id);
}
