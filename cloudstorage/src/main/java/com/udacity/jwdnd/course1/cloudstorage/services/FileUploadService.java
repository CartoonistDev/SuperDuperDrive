package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileUploadService {

    private FileMapper fileMapper;

    public FileUploadService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFile(String fileName){
        return fileMapper.getFile(fileName);
    }

    public List<File> getAllFiles(){
        return fileMapper.getAllFiles();
    }

    public List<File> getUserFiles(Integer userid){
        return fileMapper.getUserFiles(userid);
    }

    public void delete(Integer fileId){
        fileMapper.deleteFile(fileId);
    }

    public File findById(Integer fileId){
        return fileMapper.findById(fileId);
    }


    public Integer uploadFile(File file, MultipartFile multipartFile) throws Exception {
        file.setFileName(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setFileSize(String.valueOf(multipartFile.getSize()));
        file.setFileData(multipartFile.getBytes());
        return fileMapper.addFile(file);
    }

//    public boolean duplicateFile(MultipartFile multipartFile, Integer userId){
//        return fileMapper.existingFile(multipartFile.getOriginalFilename(), userId);
//    }

}
