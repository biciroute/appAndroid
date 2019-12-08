package com.example.myapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginWrapper {

    private String userName;
    private String password;

    public LoginWrapper(String userName , String password){
        this.userName = userName;
        this.password = password;
    }

}
