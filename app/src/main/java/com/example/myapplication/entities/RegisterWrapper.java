package com.example.myapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterWrapper {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
