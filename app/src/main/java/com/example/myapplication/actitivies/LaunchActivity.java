package com.example.myapplication.actitivies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.example.myapplication.R;


public class LaunchActivity extends AppCompatActivity {

    public static final String TOKEN_KEY = "TOKEN_KEY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref =
                getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
        Log.e("Token" , "Value is " +  sharedPref.contains(TOKEN_KEY));
        Intent intent = new Intent(this,  sharedPref.contains(TOKEN_KEY)  ? NavActivity.class : LoginActivity.class );;
        startActivity(intent);
    }
}