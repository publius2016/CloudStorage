package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Credential {
    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userId;
    private String authenticatedUsername;
    private String decryptedPassword;

    public Credential(Integer credentialId, String url, String username, String key, String password, Integer userId, String authenticatedUsername, String decryptedPassword) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
        this.authenticatedUsername = authenticatedUsername;
        this.decryptedPassword = decryptedPassword;
    }
}
