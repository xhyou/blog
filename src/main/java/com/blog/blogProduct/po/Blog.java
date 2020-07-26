package com.blog.blogProduct.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name="t_blog")
public class Blog {

    @Id
    @GeneratedValue
    private Long id;
    //标题
    private String title;
    //内容
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    //首图
    private String firstPicture;
    //标记
    private String flag;
    //浏览次数
    private Integer views;
    //赞赏是否开启
    private boolean appreciation;
    //转载声明是否开启
    private boolean shareStatement;
    //评论是否开启
    private boolean commentabled;
    //是否发布
    private boolean published;
    //是否推荐
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    //级联新增
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Type type;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Transient//不被数据库当做列
    private String tagIds;

    private String description;

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

}
