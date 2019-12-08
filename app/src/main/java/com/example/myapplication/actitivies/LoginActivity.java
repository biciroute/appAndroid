package com.example.myapplication.actitivies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.entities.LoginWrapper;
import com.example.myapplication.entities.Token;
import com.example.myapplication.network.RetrofitNetwork;
import com.example.myapplication.network.services.IAuthService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String userLogin = "CrkJohn";
    private static final String pswLogin = "12345";
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private IAuthService authService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://biciroute-api.herokuapp.com/") //localhost for emulator
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authService = retrofit.create(IAuthService.class);
    }

    public void login(View view) {
        final Intent intent = new Intent(this, NavActivity.class);
        //EditText editUser = (EditText) findViewById(R.id.userText);
        //EditText editPsw = (EditText) findViewById(R.id.userPassword);
        final String userS = "admin@localhost.com"; //editUser.getText().toString();
        final String pswS = "Admin123";//editPsw.getText().toString();
        Log.e("Si entro", "No se");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<Token> response =
                            authService.login(new LoginWrapper(userS, pswS)).execute();
                    final Token token = response.body();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RetrofitNetwork.createRetrofitNetwork(token.getAccessToken());
                            saveUserId(token);
                            System.out.println(token);                         //saveToken(token);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        startActivity(intent);

    }

    public void saveUserId(Token token){
        SharedPreferences sharedPref =
                getSharedPreferences(getString(R.string.id_user), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("LOGIN", token.getUserId());
        editor.commit();
    }
}