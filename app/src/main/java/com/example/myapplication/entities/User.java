package com.example.myapplication.entities;


import com.example.myapplication.entities.util.TypeUser;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{

    private ObjectId _id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private TypeUser typeUser;
    private int amountKilometers;
    private int burntCalories;

}