package com.springboot.blogapp.springbootblogrestapi.service.impl;


import com.springboot.blogapp.springbootblogrestapi.entity.Post;
import com.springboot.blogapp.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springboot.blogapp.springbootblogrestapi.payload.postDto;
import com.springboot.blogapp.springbootblogrestapi.repository.PostRepository;
import com.springboot.blogapp.springbootblogrestapi.service.postService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements postService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override

    public postDto createPost(postDto postDto) {
        // convert DTO to entity

        Post post = mapToEntity(postDto);
        Post newpost = postRepository.save(post);

        // convert entity to DTO

        postDto postResponse = mapToDto(newpost);
        return postResponse;

    }

    @Override
    public List<postDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public postDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);

    }

    @Override
    public postDto updatePost(postDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatePost = postRepository.save(post);
        return mapToDto(updatePost);



    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);

    }

    // convert entity to DTO

    private postDto mapToDto(Post post){
        postDto postDto = new postDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToEntity(postDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;

    }
}
