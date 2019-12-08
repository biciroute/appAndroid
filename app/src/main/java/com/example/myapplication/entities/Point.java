package com.example.myapplication.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Point {

    @PrimaryKey
    private String _id;
    private String latitude;
    private String longitude;
    private String address;
    private boolean common;

}