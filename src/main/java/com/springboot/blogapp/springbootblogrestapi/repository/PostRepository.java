package com.springboot.blogapp.springbootblogrestapi.repository;

import com.springboot.blogapp.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
