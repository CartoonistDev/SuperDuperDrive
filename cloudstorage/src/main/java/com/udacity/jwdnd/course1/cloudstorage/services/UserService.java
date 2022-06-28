package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;


    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(User user) {
        return userMapper.isUserAvaliable(user) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        user.setSalt(encodedSalt);
        user.setPassword(hashedPassword);

        return userMapper.addUser(user);

        //return userMapper.addUser();
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
    public int getUserId(String username) {
        User user = userMapper.getUser(username);
        return user.getUserid();
    }
//
//    public List<User> getAllUsers(){
//        return userMapper.getAllUsers();
//    }

}