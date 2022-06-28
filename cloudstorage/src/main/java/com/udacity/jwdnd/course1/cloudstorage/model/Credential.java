package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
    private String username;
    private String url;
    private String password;
    private  Integer credentialId;
    private String key;
    private Integer userid;

    public Credential(){}

    public Credential(String username, String url, String password, Integer credentialId, String key, Integer userid) {
        this.username = username;
        this.url = url;
        this.password = password;
        this.credentialId = credentialId;
        this.key = key;
        this.userid = userid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
