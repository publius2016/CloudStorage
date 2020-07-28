package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class File {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private byte[] fileData;
    private String authenticatedUsername;
    private String fileDataBlob;

    public File(Integer fileId, String fileName, String contentType, String fileSize, Integer userId, byte[] fileData, String authenticatedUsername, String fileDataBlob) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
        this.authenticatedUsername = authenticatedUsername;
        this.fileDataBlob = fileDataBlob;
    }
}
