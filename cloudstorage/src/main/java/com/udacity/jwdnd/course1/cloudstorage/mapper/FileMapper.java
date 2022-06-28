package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.*;
import com.udacity.jwdnd.course1.cloudstorage.model.File;

import java.util.List;
@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE filename = #{filename};")
    File getFile(String filename);

    @Select("SELECT * FROM FILES;")
    List<File> getAllFiles();

    @Select("SELECT * FROM FILES WHERE userid = #{userid};")
    List<File> getUserFiles(Integer userid);
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File findById(Integer fileId);

    @Select("SELECT CASE WHEN EXIST (SELECT * FROM FILES WHERE filename = #{filename} AND userid = #{userid}) THEN TRUE ELSE FALSE END AS bool;")
    boolean existingFile(String fileName, Integer userId);

    @Insert("INSERT INTO FILES(fileName, contentType, fileSize, userId, fileData) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata});")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer addFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId};")
    void deleteFile(Integer fileId);
}