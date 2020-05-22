package com.blog.blogProduct.service;

import com.blog.blogProduct.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag(String ids);

    List<Tag> listTag();

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);

    Tag findByName(String name);
}
