package com.wsx.tmall.service;

import com.wsx.tmall.pojo.User;

import java.util.List;

/**
 * Created by frank on 2018/5/3.
 */
public interface UserService {
    List<User> list();

    User get(int id);

    void update(User user);

    void add(User user);

    void delete(int id);
}
