/*package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("员工登录:{}",user);
        User u=userService.login(user);
        //生成并下发令牌
        if(u!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",u.getId());
            claims.put("name",u.getName());
            claims.put("username",u.getUsername());
            String jwt=JwtUtils.generateJwt(claims);//包含信息
                return Result.success(jwt);
        }
        return Result.error("用户账号或密码错误");
    }


}…*/
