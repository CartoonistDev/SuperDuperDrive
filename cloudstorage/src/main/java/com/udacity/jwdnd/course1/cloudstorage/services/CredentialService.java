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

//    public CredentialService(CredentialMapper credentialMapper) {
//        this.credentialMapper = credentialMapper;
//    }


    public void createCredential(Credential credential){
       Credential newCredential = new Credential(null, credential.getUsername(), credential.getUrl(), credential.getPassword(), credential.getKey(), credential.getUserid());
       credentialMapper.addCredentials(newCredential);
    }


    public void upDateCredential(Credential credential){
        Credential newCredential = new Credential(credential.getCredentialId(), credential.getUsername(), credential.getUrl(), credential.getPassword(), credential.getKey(), credential.getUserid());
        credentialMapper.updateCredential(newCredential);
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
    }

    public List<Credential> getAllCredentials(){
        return credentialMapper.getAllCredentials();
    }

    public Credential getUserCredential(String key){
        return credentialMapper.getCredential(key);
    }



}
