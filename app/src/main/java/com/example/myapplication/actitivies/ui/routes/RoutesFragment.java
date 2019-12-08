package com.example.myapplication.actitivies.ui.routes;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.actitivies.adapter.RouteListAdapter;
import com.example.myapplication.entities.LoginWrapper;
import com.example.myapplication.entities.Route;
import com.example.myapplication.entities.Token;
import com.example.myapplication.network.RetrofitNetwork;
import com.example.myapplication.network.services.IRouteServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;

import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;

public class RoutesFragment extends Fragment {


    private RoutesViewModel mViewModel;
    RecyclerView recyclerView;
    ArrayList<Route> routeArrayList;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    IRouteServices routeServices = RetrofitNetwork.getIRouteServices();

    public static RoutesFragment newInstance() {
        return new RoutesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.routes_fragment, container, false);
        routeArrayList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getAllRoutes();
        System.out.println("Before adapater " + this.routeArrayList);

        RouteListAdapter routeListAdapter = new RouteListAdapter(routeArrayList);
        recyclerView.setAdapter(routeListAdapter);
        return view;
    }

    private void getAllRoutes() {
        SharedPreferences myPrefs = getActivity().
                getSharedPreferences(getString(R.string.id_user), Context.MODE_PRIVATE);

        String id_user = myPrefs.getString("LOGIN" , "EMPTY");
        System.out.println(id_user);
        if(id_user.equals("EMPTY")){
           return;
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<List<Route>> response =
                            routeServices.getRoutesbyUser(id_user).execute();
                    final List<Route> routes = response.body();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fillUpList(routes);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void fillUpList(List<Route> routes){
        this.routeArrayList =new ArrayList<Route>(routes);
        System.out.println(this.routeArrayList);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RoutesViewModel.class);
        // TODO: Use the ViewModel
    }

}
