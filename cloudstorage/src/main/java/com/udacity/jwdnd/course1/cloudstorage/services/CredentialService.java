package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }


    public Integer createCredential(Credential credential){
       Credential newCredential = new Credential(credential.getUsername(), credential.getUrl(), credential.getPassword(), credential.getCredentialId(), credential.getKey(), credential.getUserid());
       return credentialMapper.addCredentials(newCredential);
    }


    public void upDateNote(Credential credential){
        Credential newCredential = new Credential(credential.getUsername(), credential.getUrl(), credential.getPassword(), credential.getCredentialId(), credential.getKey(), credential.getUserid());
        credentialMapper.updateCredential(newCredential);
    }

    public void deleteNote(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
    }

    public List<Credential> getAllCredentials(){
        return credentialMapper.getAllCredentials();
    }

    public Credential getUserCredential(String key){
        return credentialMapper.getCredential(key);
    }



}
