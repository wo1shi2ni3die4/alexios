package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer deptId, String address) {
        log.info("page:{},pageSize:{}", page, pageSize);
        PageBean PageBean = userService.page(page, pageSize, name, deptId, address);
        return Result.success(PageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除员工:{}", ids);
        userService.delete(ids);
        return Result.success();
    }
    @PostMapping
    public Result insert(@RequestBody User user) {
        log.info("添加员工:{}",user);
        userService.insert(user);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("通过{}查询员工信息",id);
        User user = userService.getById(id);
        return Result.success(user);
    }
    @PutMapping
    public Result put(@RequestBody User user) {
        log.info("修改数据:{}", user);
        userService.put(user);
        return Result.success();
    }
}
