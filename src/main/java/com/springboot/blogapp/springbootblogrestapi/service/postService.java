package com.springboot.blogapp.springbootblogrestapi.service;

import com.springboot.blogapp.springbootblogrestapi.payload.PostResponse;
import com.springboot.blogapp.springbootblogrestapi.payload.postDto;

import java.util.List;

public interface postService {
    postDto createPost(postDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy);
    postDto getPostById(long id);

    postDto updatePost(postDto postDto, long id );

    void deletePost(long id);
}
