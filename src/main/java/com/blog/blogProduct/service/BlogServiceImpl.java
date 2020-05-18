package com.blog.blogProduct.service;

import com.blog.blogProduct.NotFoundException;
import com.blog.blogProduct.dao.BlogRepository;
import com.blog.blogProduct.po.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        //需要按照条件进行查询
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> list= new ArrayList<>();
                if(!"".equals(blog.getTitle()) && blog.getTitle()!=null){
                    list.add(cb.like(root.get("title"),"%"+blog.getTitle()+"%"));
                }
                if(blog.getType()!=null){
                    list.add(cb.equal(root.get("type").get("id"),blog.getType()));
                }
                if(blog.isRecommend()){
                    list.add(cb.equal(root.get("recommend"),blog.isRecommend()));
                }
                cq.where(list.toArray(new Predicate[list.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = getBlog(id);
        if(b==null){
            throw new NotFoundException("更新的类不存在");
        }
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
