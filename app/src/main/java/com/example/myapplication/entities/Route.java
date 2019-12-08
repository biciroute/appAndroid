package com.example.myapplication.entities;

import org.bson.types.ObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.myapplication.entities.Point;

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