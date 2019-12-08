package com.example.myapplication.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Point {

    private ObjectId _id;
    private String latitude;
    private String longitude;
    private String address;
    private boolean common;

}