package com.example.myapplication.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonRoute {
    private ObjectId _id;
    private Point origin;
    private Point destination;
    private User leaderRoute;
    private Date hour;

    @Override
    public String toString() {
        return "CommonRoute [_id=" + _id + ", destination=" + destination + ", hour=" + hour + ", leaderRoute="
                + leaderRoute + ", origin=" + origin + "]";
    }



}