package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Integer deptId, String address) {
        PageHelper.startPage(page,pageSize);
        List<User>userList=userMapper.list(name,deptId,address);
        Page<User> p=(Page<User>)userList;
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        userMapper.delete(ids);
    }

    @Override
    public void insert(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void put(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.put(user);
    }

    @Override
    public User login(User user) {
        User u=userMapper.getByUsernameAndPassword(user);
        return u;
    }
}
