package com.blog.blogProduct.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name="t_type")
public class Type {
    @GeneratedValue
    @Id
    private Long id;

    //分类的名字
    @NotBlank(message = "分类名称不能为空")
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();


}
