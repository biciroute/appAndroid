package com.example.myapplication.actitivies.ui.share;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.myapplication.R;
import com.example.myapplication.entities.Route;
import com.example.myapplication.entities.User;
import com.example.myapplication.network.RetrofitNetwork;
import com.example.myapplication.network.services.IProfileService;
import com.example.myapplication.network.services.IRouteServices;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    IProfileService profileServices = RetrofitNetwork.getIProfileServices();

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        shareViewModel =  new ShareViewModel(view);
        getAllRoutes();
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
                    Response<User> response = profileServices.getUser(id_user).execute();
                    final User profile = response.body();
                    Random random =  new Random();
                    profile.setTrips(random.nextInt(500));
                    profile.setFollowers(random.nextInt(100));
                    profile.setFollowing(random.nextInt(100));
                    profile.setTraveled(random.nextInt(100));
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            shareViewModel.setModel(profile);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}