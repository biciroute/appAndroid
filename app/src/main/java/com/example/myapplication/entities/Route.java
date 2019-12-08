package com.example.myapplication.entities;

import android.graphics.Point;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName =  "route")
public class Route{

    @PrimaryKey
    private String _id;

    @NonNull
    private Point origin;

    @NonNull
    private Point destination;

    @NonNull
    private User user;

    @NonNull
    private CommonRoute commonRoute;

    @Override
    public String toString() {
        return "Route [_id=" + _id + ", commonRoute=" + commonRoute + ", destination=" + destination + ", origin="
                + origin + ", user=" + user + "]";
    }
}