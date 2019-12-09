package com.example.myapplication.network.services;

import com.example.myapplication.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProfileService {

    @GET(value = "v1/user/{user_id}")
    Call<User> getUser(@Path("user_id") String user_id);
}
