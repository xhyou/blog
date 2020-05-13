package com.blog.blogProduct.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name="t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    //昵称
    private String nickName;
    private String email;
    private String content;
    //头像
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;

    //一个子类对象对应多条评论
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replayComments= new ArrayList<>();

    //一个子类对象对应一个Comment
    @ManyToOne
    private Comment parentComment;
}
