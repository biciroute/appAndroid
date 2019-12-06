package com.example.myapplication.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@ToString
public class Point {

    @Id
    private ObjectId _id;
    private String latitude;
    private String longitude;
    private String address;
    private boolean common;

}