package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    String isUserAvaliable(User username);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    int getUserId(String username);

    @Select("SELECT * FROM USERS")
    List<User> getAllUsers();

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int addUser(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{userid}")
    void deleteUser(Integer userid);
}
