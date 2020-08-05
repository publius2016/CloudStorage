package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/{fileId}", produces = MediaType.ALL_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getFile(@PathVariable("fileId") Integer fileId) {
        try {
            File file = fileService.getFile(fileId);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", file.getContentType() + "; charset=UTF-8");
            headers.add("Accepts", file.getContentType());
            headers.add("Content-Disposition", "attachment");

            return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public String insertFile(@RequestParam("fileUpload") MultipartFile fileForm) throws IOException {
        try {
            List<String> fileNames = fileService.getFileNames(fileService.getFiles());
            Boolean isFileNameDuplicate = fileService.isFileNameDuplicate(fileNames, fileForm);

            if (!fileForm.getOriginalFilename().equals("") && !isFileNameDuplicate) {
                fileService.addFile(fileForm);
                return "redirect:/home?successMessage=Your file has been added successfully.";
            } else if (isFileNameDuplicate) {
                return "redirect:/home?errorMessage=File already exists. Please upload a unique file.";
            } else {
                return "redirect:/home?errorMessage=Please choose a file to upload.";
            }
        } catch(Exception e) {
            return "redirect:/home?errorMessage=Something went wrong while adding your file.";
        }
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId) {
        try {
            fileService.deleteFile(fileId);
            return "redirect:/home?successMessage=Your file has been deleted successfully.";
        } catch(Exception e) {
            return "redirect:/home?errorMessage=Something went wrong while deleting your file.";
        }
    }
}
