package com.example.bookHaven.entity.dto.response;

import com.example.bookHaven.entity.Reader;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReaderDTOResponse {
    private String id;
    private String email;
    private String username;
    private String password;
    private Integer age;
    private Reader.Gender gender;
    private String bio;
    private byte[] profile_pic;
    private List<String> friendIds = new ArrayList<>();
}
