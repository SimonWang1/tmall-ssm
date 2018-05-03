package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.UserMapper;
import com.wsx.tmall.pojo.User;
import com.wsx.tmall.pojo.UserExample;
import com.wsx.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frank on 2018/5/3.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        UserExample example = new UserExample();
        example.setOrderByClause("id asc");
        return userMapper.selectByExample(example);
    }

    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void delete(int id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
