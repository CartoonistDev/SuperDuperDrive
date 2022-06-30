package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialMapper credentialMapper;

    public void createCredential(Credential credential, Integer userId){
       Credential newCredential = new Credential();

       newCredential.setUserid(userId);
       newCredential.setUrl(credential.getUrl());
       newCredential.setKey(credential.getKey());
       newCredential.setPassword(credential.getPassword());
       newCredential.setUsername(credential.getUsername());

       credentialMapper.addCredentials(newCredential);
    }


    public void upDateCredential(Credential credential){

        credential.getUrl();
        credential.getKey();
        credential.getPassword();
        credential.getUsername();

        credentialMapper.updateCredential(credential);
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
    }

    public List<Credential> getAllCredentials(Integer userId){
        return credentialMapper.getAllCredentials(userId);
    }

    public Credential getUserCredential(String key){
        return credentialMapper.getCredential(key);
    }



}
