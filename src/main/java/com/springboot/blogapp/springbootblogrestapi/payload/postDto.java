package com.springboot.blogapp.springbootblogrestapi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class postDto {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 character")
    private String title;
    @NotEmpty
    @Size(min = 2, message = "Post description should have at least 10 character")
    private String description;
    @NotEmpty
    private String content;
}
