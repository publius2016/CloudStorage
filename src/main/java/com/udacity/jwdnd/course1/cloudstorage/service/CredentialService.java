package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private AuthenticationService authenticationService;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, AuthenticationService authenticationService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.authenticationService = authenticationService;
        this.encryptionService = encryptionService;
    }

    public void addCredential(CredentialForm credentialForm) {
        String encodedKey = encryptionService.getEncodedKey();
        String encryptedPassword = encryptionService.encryptValue(credentialForm.getDecryptedPassword(), encodedKey);

        credentialMapper.insertCredential(
                new Credential(
                        null,
                        credentialForm.getUrl(),
                        credentialForm.getUsername(),
                        encodedKey,
                        encryptedPassword,
                        null,
                        authenticationService.getCurrentUsername(),
                        null)
        );
    }

    public void updateCredential(CredentialForm credentialForm) {
        String encodedKey = encryptionService.getEncodedKey();
        String encryptedPassword = encryptionService.encryptValue(credentialForm.getDecryptedPassword(), encodedKey);

        credentialMapper.updateCredential(
                new Credential(
                        credentialForm.getCredentialId(),
                        credentialForm.getUrl(),
                        credentialForm.getUsername(),
                        encodedKey,
                        encryptedPassword,
                        null,
                        null,
                        null)
        );
    }

    public List<Credential> getCredentials(String username) {
        List<Credential> credentialsWithDecryptedValues = credentialMapper
                .getCredentials(username)
                .stream()
                .map(credential -> {
                    credential.setDecryptedPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
                    return credential;
                })
                .collect(Collectors.toList());

        return credentialsWithDecryptedValues;
    }

    public void deleteCredential(String credentialId) { credentialMapper.deleteCredential(credentialId); }
}
