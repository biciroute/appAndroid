package com.example.myapplication.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class Token{

    private String accessToken;
    private String firstName;
    private String userId;

}
