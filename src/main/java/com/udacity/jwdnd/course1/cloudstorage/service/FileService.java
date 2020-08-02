package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    private FileMapper fileMapper;
    private AuthenticationService authenticationService;

    public FileService(FileMapper fileMapper, AuthenticationService authenticationService) {
        this.fileMapper = fileMapper;
        this.authenticationService = authenticationService;
    }

    public void addFile(MultipartFile fileForm) throws IOException {

        fileMapper.insertFile(
                new File(
                        null,
                        fileForm.getOriginalFilename(),
                        fileForm.getContentType(),
                        String.valueOf(fileForm.getSize()),
                        null,
                        fileForm.getInputStream().readAllBytes(),
                        authenticationService.getCurrentUsername(),
                        null
                )
        );
    }

    public List<File> getFiles() {
        return fileMapper.getFiles(authenticationService.getCurrentUsername());
    }

    public List<String> getFileNames(List<File> files) {
        return files.stream().map(file -> file.getFileName()).collect(Collectors.toList());
    }

    public Boolean isFileNameDuplicate(List<String> fileNames, MultipartFile fileForm) {
        return fileNames.contains(fileForm.getOriginalFilename());
    }

    public File getFile(Integer fileId) { return fileMapper.getFileData(fileId); }

    public void deleteFile(Integer fileId) { fileMapper.deleteFile(fileId); }
}