package com.example.myapplication.model;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Route;

import java.util.List;

public class RouteListAdapter  extends RecyclerView.Adapter<RouteListAdapter.RouteViewModel> {


    class RouteViewModel extends RecyclerView.ViewHolder {
        private final TextView routeItemView;

        private RouteViewModel(View itemView) {
            super(itemView);
            routeItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Route> mRoute; // Cached copy of words

    public RouteListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public RouteViewModel onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RouteViewModel(itemView);
    }

    @Override
    public void onBindViewHolder(RouteViewModel holder, int position) {
        if (mRoute != null) {
            Route current = mRoute.get(position);
            holder.routeItemView.setText(current.getUser().getEmail());
        } else {
            // Covers the case of data not being ready yet.
            holder.routeItemView.setText("No Route");
        }
    }

    void setRoute(List<Route> routes){
        mRoute = routes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRoute != null)
            return mRoute.size();
        else return 0;
    }


}