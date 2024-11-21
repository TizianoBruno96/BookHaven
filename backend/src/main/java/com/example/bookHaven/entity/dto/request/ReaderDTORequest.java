package com.example.bookHaven.entity.dto.request;

import com.example.bookHaven.entity.Reader.Gender;
import lombok.Data;

@Data
public class ReaderDTORequest {
    private String email;
    private String username;
    private String password;
    private Integer age;
    private Gender gender;
    private String bio;
    private byte[] profile_pic;
}
