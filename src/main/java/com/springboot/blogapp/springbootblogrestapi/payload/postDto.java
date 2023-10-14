package com.springboot.blogapp.springbootblogrestapi.payload;

import lombok.Data;

@Data
public class postDto {
    private Long id;
    private String title;
    private String description;
    private String content;
}
