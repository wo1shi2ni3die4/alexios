package com.example.demo.service;

import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.User;

import java.util.List;

public interface UserService {
    PageBean page(Integer page, Integer pageSize, String name,Integer deptId, String address);
    void delete(List<Integer> ids);

    void insert(User user);

    User getById(Integer id);

    void put(User user);

    User login(User user);
}
