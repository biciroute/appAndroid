package com.example.myapplication.actitivies.ui.share;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;
import com.example.myapplication.entities.User;

public class ShareViewModel extends ViewModel {

    public TextView trips, followers, following, name , traveled, email;

    public ShareViewModel(@NonNull View view) {
        name = view.findViewById(R.id.textViewProfileName);
        trips = view.findViewById(R.id.textViewTrips);
        followers = view.findViewById(R.id.textViewFollowers);
        following =  view.findViewById(R.id.textViewFollowing);
        traveled = view.findViewById(R.id.textViewTraveled);
        email = view.findViewById(R.id.textViewEmailProfile);
    }

    public void setModel(User profile){
        name.setText(profile.getFirstName());
        email.setText(profile.getEmail());
        trips.setText( Integer.toString( profile.getTrips() ) );
        followers.setText( Integer.toString( profile.getFollowers()));
        following.setText(Integer.toString(profile.getFollowing()));
        traveled.setText(Integer.toString(profile.getTraveled()));
    }
}