package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CredentialMapper {

    //From the schema.sql you create mappers and from the mappers
    // you build your business logic in the services section
    @Select("SELECT * FROM CREDENTIALS WHERE key = #{key}")
    Credential getCredential(String key);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredentials(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void deleteCredential(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} WHERE credentialId = #{credentialId}")
    int updateCredential(Credential credential);

    @Select("SELECT * FROM CREDENTIALS ")
    List<Credential> getAllCredentials();
}
