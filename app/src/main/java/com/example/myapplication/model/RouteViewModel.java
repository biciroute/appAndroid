package com.example.myapplication.model;

import android.app.Application;

import com.example.myapplication.entities.Route;
import com.example.myapplication.network.RetrofitNetwork;
import com.example.myapplication.network.services.IRouteServices;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import retrofit2.Response;

public class RouteViewModel extends AndroidViewModel {


    private List<Route> mAllRoute;
    private IRouteServices iRouteServices;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);


    public RouteViewModel (Application application) {
        super(application);
        iRouteServices =  RetrofitNetwork.getIRouteServices();
        try {
            Response<List<Route>> routes = iRouteServices.getAllRoutes().execute();
            mAllRoute = routes.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Route> getAllRoutes() { return mAllRoute; }


}
