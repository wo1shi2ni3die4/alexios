package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list(@Param("name") String name, @Param("deptId") Integer deptId, @Param("address") String address);

    void delete(List<Integer> ids);
    @Insert("insert into user(name, username,password,dept_id,image,email,phone,address,create_time,update_time) values" +
            "(#{name},#{username},#{password},#{deptId},#{image},#{email},#{phone},#{address},#{createTime},#{updateTime}) ")
    void insert(User user);
    @Select("select * from user where id=#{id}")
    User getById(Integer id);

    void put(User user);
    @Select("select * from user where username=#{username} and password=#{password}")
    User getByUsernameAndPassword(User user);
    @Delete("delete from user where dept_id=#{id}")
    void deleteByDeptId(Integer id);
}
