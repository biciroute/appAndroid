package com.example.myapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginWrapper {

    private String email;
    private String password;

    public LoginWrapper(String userName , String password){
        this.email = userName;
        this.password = password;
    }

}
