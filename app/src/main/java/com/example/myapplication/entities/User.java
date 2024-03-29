package com.example.myapplication.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Bicicle bicicle;
    private int trips;
    private int followers;
    private int following;
    private int traveled;

}
