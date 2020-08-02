package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Results(value = {
            @Result(property = "fileId", column = "fileid"),
            @Result(property = "fileName", column = "filename"),
    })
    @Select("SELECT fileid, filename FROM FILES LEFT JOIN USERS ON FILES.userid = USERS.userid WHERE USERS.username = #{authenticatedUsername}")
    List<File> getFiles(String authenticatedUsername);

    @Results(value = {
            @Result(property = "fileId", column = "fileid"),
            @Result(property = "contentType", column = "contenttype"),
            @Result(property = "fileSize", column = "filesize"),
            @Result(property = "fileData", column = "filedata"),
    })
    @Select("SELECT fileid, contenttype, filesize, filedata FROM FILES WHERE fileid = #{fileId}")
    File getFileData(Integer fileId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) SELECT #{fileName}, #{contentType}, #{fileSize}, #{fileData}, USERS.userid FROM USERS WHERE USERS.username = #{authenticatedUsername}")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    int deleteFile(Integer fileId);
}