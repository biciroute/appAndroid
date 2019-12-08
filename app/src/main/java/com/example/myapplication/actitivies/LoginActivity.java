package com.example.myapplication.actitivies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.entities.LoginWrapper;
import com.example.myapplication.entities.Token;
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
        final String userS = "camilo@localhost.com"; //editUser.getText().toString();
        final String pswS = "Camilo123";//editPsw.getText().toString();
        Log.e("Si entro", "No se");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<Token> response =
                            authService.login(new LoginWrapper(userS, pswS)).execute();
                    final Token token = response.body();

                        /*
                            Testing if this's works
                            RetrofitNetwork retrofit1 = new RetrofitNetwork(token.getAccessToken());
                            ITaskService taskService =  retrofit1.getTaskService();
                            final Response<List<Task>> response1 =  taskService.getAllTask().execute();
                        */
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
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
}