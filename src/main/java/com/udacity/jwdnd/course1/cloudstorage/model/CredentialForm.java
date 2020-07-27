package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialForm {
    private Integer credentialId;
    private String url;
    private String username;
    private String password;
    private String decryptedPassword;
}
