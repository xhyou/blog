package com.blog.blogProduct.service;

import com.blog.blogProduct.po.User;

public interface UserService {
    User checkUser(String userName, String passWord);
}
