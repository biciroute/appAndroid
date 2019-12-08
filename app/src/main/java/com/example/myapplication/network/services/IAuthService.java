package com.example.myapplication.network.services;

import com.example.myapplication.entities.LoginWrapper;
import com.example.myapplication.entities.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthService {

    @POST("v1/login/")
    Call<Token> login(@Body LoginWrapper login);
}