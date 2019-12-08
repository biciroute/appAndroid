package com.example.myapplication.actitivies.ui.routes;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.actitivies.adapter.RouteListAdapter;
import com.example.myapplication.entities.Route;

import java.util.ArrayList;

public class RoutesFragment extends Fragment {


    private RoutesViewModel mViewModel;
    RecyclerView recyclerView;
    ArrayList<Route> routeArrayList;

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
        RouteListAdapter routeListAdapter = new RouteListAdapter(routeArrayList);
        recyclerView.setAdapter(routeListAdapter);
        return view;
    }

    private void getAllRoutes() {
        routeArrayList.add(new Route());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RoutesViewModel.class);
        // TODO: Use the ViewModel
    }

}
