package com.example.myapplication.actitivies.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteListAdapter  extends RecyclerView.Adapter<RouteListAdapter.RouteViewModel> {

    ArrayList<Route> routeArrayList;

    public RouteListAdapter(ArrayList<Route> routeArrayList) {
        this.routeArrayList = routeArrayList;
    }

    public class RouteViewModel extends  RecyclerView.ViewHolder{

        public TextView origen, destino , usuarioNombre;

        public RouteViewModel(View view) {
            super(view);
            origen =  view.findViewById(R.id.origen);
            destino = view.findViewById(R.id.destino);
        }
    }

    @NonNull
    @Override
    public RouteViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item , null , false);
        return new RouteViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewModel holder, int position) {
        holder.origen.setText(routeArrayList.get(position).getOrigin().getAddress());
        holder.destino.setText(routeArrayList.get(position).getDestination().getAddress());
    }

    @Override
    public int getItemCount() {
        return routeArrayList.size();
    }

}