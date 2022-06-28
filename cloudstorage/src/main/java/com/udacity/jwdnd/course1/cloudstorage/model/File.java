package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Arrays;

public class File {
    private String fileName;
    private Integer fileId;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private byte[] fileData;


    public File(){}
    public File(String fileName, Integer fileId, String contentType, String fileSize, Integer userId, byte[] fileData) {
        this.fileName = fileName;
        this.fileId = fileId;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }


    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", userId=" + userId +
                ", fileData=" + Arrays.toString(fileData) +
                '}';
    }
}
