package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping
    public String addCredential(CredentialForm credentialForm) {
        try {
            List<Credential> credentials = credentialService.getCredentials();
            Boolean isUpdatedCredentialUsernameSame = credentialService.isUpdatedCredentialUsernameSame(credentialForm, credentials);
            Boolean isNewUsernameDuplicate = credentialService.isNewUsernameDuplicate(credentialForm, credentials);
            Boolean isUpdateRequest = credentialForm.getCredentialId() != null;

            if (isUpdateRequest) {
                if(isUpdatedCredentialUsernameSame || !isNewUsernameDuplicate) {
                    credentialService.updateCredential(credentialForm);
                    return "redirect:/home?successMessage=Your credentials have been updated successfully.";
                } else {
                    return "redirect:/home?errorMessage=Duplicate usernames are not permitted. Please enter a unique username.";
                }
            } else {
                if(isNewUsernameDuplicate) {
                    return "redirect:/home?errorMessage=Duplicate usernames are not permitted. Please enter a unique username.";
                }
                credentialService.addCredential(credentialForm);
                return "redirect:/home?successMessage=Your credentials have been added successfully.";
            }
        } catch (Exception e) {
            return "redirect:/home?errorMessage=Something went wrong while saving your credentials";
        }
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") String credentialId) {
        try {
            credentialService.deleteCredential(credentialId);
            return "redirect:/home?successMessage=Your credentials were deleted successfully.";
        } catch (Exception e) {
            return "redirect:/home?errorMessage=Something went wrong while deleting your credentials.";
        }
    }
}
