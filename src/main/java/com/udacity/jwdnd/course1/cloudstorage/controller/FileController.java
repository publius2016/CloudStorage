package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    private NoteService noteService;
    private CredentialService credentialService;
    private FileService fileService;
    private AuthenticationService authenticationService;

    public FileController(NoteService noteService, CredentialService credentialService, FileService fileService, AuthenticationService authenticationService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/{fileId}", produces = MediaType.ALL_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getFile(@PathVariable("fileId") Integer fileId) {
        File file = fileService.getFile(fileId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", file.getContentType() + "; charset=UTF-8");
        headers.add("Accepts", file.getContentType());

        return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public String insertFile(@RequestParam("fileUpload") MultipartFile fileForm, NoteForm noteForm, CredentialForm credentialForm, Model model) throws IOException {
        fileService.addFile(fileForm);
        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        model.addAttribute("credentials", credentialService.getCredentials(authenticationService.getCurrentUsername()));
        model.addAttribute("files", fileService.getFileNames(authenticationService.getCurrentUsername()));
        return "home";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(NoteForm noteForm, CredentialForm credentialForm, FileForm fileForm, @PathVariable("fileId") Integer fileId, Model model) {
        fileService.deleteFile(fileId);
        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        model.addAttribute("credentials", credentialService.getCredentials(authenticationService.getCurrentUsername()));
        model.addAttribute("files", fileService.getFileNames(authenticationService.getCurrentUsername()));
        return "home";
    }
}
