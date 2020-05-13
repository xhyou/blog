package com.blog.blogProduct.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name ="t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    //标签的类型
    private String type;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}
