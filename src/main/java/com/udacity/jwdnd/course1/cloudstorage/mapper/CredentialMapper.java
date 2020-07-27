package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Results(value = {
            @Result(property = "credentialId", column = "credentialid"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "key", column = "key"),
            @Result(property = "password", column = "password"),
            @Result(property = "userId", column = "userid"),
    })
    @Select("SELECT credentialid, url, CREDENTIALS.username, key, CREDENTIALS.password, CREDENTIALS.userid FROM CREDENTIALS LEFT JOIN USERS ON CREDENTIALS.userid = USERS.userid WHERE USERS.username = #{authenticatedUsername}")
    List<Credential> getCredentials(String authenticatedUsername);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) SELECT #{url}, #{username}, #{key}, #{password}, USERS.userid FROM USERS WHERE USERS.username = #{authenticatedUsername}")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    int deleteCredential(String credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialId}")
    int updateCredential(Credential credential);
}
