package com.example.myapplication.network.services;


import com.example.myapplication.entities.Point;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IPointServices {



    @GET
    Call<List<Point>> getAllPoints();

    @POST
    Point createPoint(Point point);

    @GET
    public Call<List<Point>> getCommonRoute();


}