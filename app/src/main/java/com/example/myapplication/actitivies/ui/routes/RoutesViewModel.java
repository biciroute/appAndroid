package com.example.myapplication.actitivies.ui.routes;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.entities.Route;
import com.example.myapplication.network.RetrofitNetwork;
import com.example.myapplication.network.services.IRouteServices;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;

public class RoutesViewModel extends ViewModel {

    private List<Route> mAllRoute;
    private IRouteServices iRouteServices;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);




    List<Route> getAllRoutes() { return mAllRoute; }
}
