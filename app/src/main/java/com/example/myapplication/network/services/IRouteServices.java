package com.example.myapplication.network.services;


import com.example.myapplication.entities.Route;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IRouteServices {

    @GET(value="/user/{user_id}")
    Call<List<Route>> getRoutesbyUser(@Path("user_id") String user_id);

    @GET(value = "/{routeId}")
    Call<Route> getRoute(@Path("/{routeId}") String routeId);

    @POST
    void saveRoute(@Body Route route);

    @GET
    Call<List<Route>> getAllRoutes();

}
