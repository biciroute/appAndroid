package com.example.myapplication.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.bson.types.ObjectId;

import java.util.Date;

public class CommonRoute {

    private ObjectId _id;

    private Point origin;

    private Point destination;

    @NonNull
    @Ignore
    private User leaderRoute;

    private String address;

    private Date hour;

    @Override
    public String toString() {
        return "CommonRoute [_id=" + _id + ", destination=" + destination + ", hour=" + hour + ", leaderRoute="
                + leaderRoute + ", origin=" + origin + "]";
    }



}