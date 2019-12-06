package com.example.myapplication.entities;

import android.graphics.Point;

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
public class Route{

    private ObjectId _id;
    private Point origin;
    private Point destination;
    private User user;
    private CommonRoute commonRoute;

    @Override
    public String toString() {
        return "Route [_id=" + _id + ", commonRoute=" + commonRoute + ", destination=" + destination + ", origin="
                + origin + ", user=" + user + "]";
    }
}