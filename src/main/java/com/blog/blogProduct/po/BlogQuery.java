package com.blog.blogProduct.po;

import lombok.Data;

@Data
public class BlogQuery {
    private String title;
    private String typeId;
    private boolean recommend;
}
