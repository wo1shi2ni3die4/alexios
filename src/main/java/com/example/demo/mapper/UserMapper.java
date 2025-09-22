package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list(String name, Short email, String address);

    void delete(List<Integer> ids);
    @Insert("insert into user(name, username,username,password,nickname,image,email,phone,adress,create_time,update_time) values" +
            "(#{name},#{username},#{password},#{nickname},#{image},#{email},#{phone},#{address},#{createTime},#{updateTime}) ")
    void insert(User user);
    @Select("select * from user where id=#{id}")
    User getById(Integer id);

    void put(User user);
    @Select("select * from user where username=#{username} and password=#{password}")
    User getByUsernameAndPassword(User user);

    List<User> list(String username, String email, String address);
}
