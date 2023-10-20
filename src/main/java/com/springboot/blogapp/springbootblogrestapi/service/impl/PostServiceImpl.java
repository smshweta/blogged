package com.springboot.blogapp.springbootblogrestapi.service.impl;


import com.springboot.blogapp.springbootblogrestapi.entity.Post;
import com.springboot.blogapp.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springboot.blogapp.springbootblogrestapi.payload.PostResponse;
import com.springboot.blogapp.springbootblogrestapi.payload.postDto;
import com.springboot.blogapp.springbootblogrestapi.repository.PostRepository;
import com.springboot.blogapp.springbootblogrestapi.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements postService {

    private PostRepository postRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();


        List<postDto> content = listOfPosts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(postResponse.isLast());

        return postResponse;
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
        postDto postDto = mapper.map(post, postDto.class);
        return postDto;
    }

    private Post mapToEntity(postDto postDto){
        Post post = mapper.map(postDto, Post.class);
        return post;

    }
}
