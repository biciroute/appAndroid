package com.example.myapplication.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "common_Route")
public class CommonRoute {

    @PrimaryKey
    private String _id;

    @NonNull
    private Point origin;

    @NonNull
    private Point destination;

    @NonNull
    @Ignore
    private User leaderRoute;

    @NonNull
    private Date hour;

    @Override
    public String toString() {
        return "CommonRoute [_id=" + _id + ", destination=" + destination + ", hour=" + hour + ", leaderRoute="
                + leaderRoute + ", origin=" + origin + "]";
    }



}