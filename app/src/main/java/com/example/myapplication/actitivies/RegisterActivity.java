package com.example.myapplication.actitivies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.entities.LoginWrapper;
import com.example.myapplication.entities.RegisterWrapper;
import com.example.myapplication.entities.Token;
import com.example.myapplication.network.RetrofitNetwork;
import com.example.myapplication.network.services.IAuthService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private IAuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://biciroute-api.herokuapp.com/") //localhost for emulator
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authService = retrofit.create(IAuthService.class);
    }

    public void register(View view) {
        final Intent intent = new Intent(this, NavActivity.class);
        EditText editName = (EditText) findViewById(R.id.editText_nameSignUp);
        EditText editPsw = (EditText) findViewById(R.id.editText_pwdSingUp);
        EditText editLastName = (EditText) findViewById(R.id.editText_lastSignUp);
        EditText editEmail = (EditText) findViewById(R.id.editText_emailSignUp);
        final String nameS = editName.getText().toString();
        final String pswS = editPsw.getText().toString();
        final String emailS = editEmail.getText().toString();
        final String lastNameS = editLastName.getText().toString();


        if(nameS.equalsIgnoreCase("") ){
            editName.setError("Debes escribir tu nombre");
        }
        if(pswS.equalsIgnoreCase("") ){
            editPsw.setError("Debes escribir tu contraseña");
        }
        if(emailS.equalsIgnoreCase("") ){
            editEmail.setError("Debes escribir tu correo");
        }
        if(lastNameS.equalsIgnoreCase("") ){
            editLastName.setError("Debes escribir tu apellido");
        }


        if (nameS.length() > 0 && pswS.length() > 0 && emailS.length() > 0  && lastNameS.length() > 0) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response<Token> response =
                                authService.register(new RegisterWrapper(emailS,pswS,nameS,lastNameS)).execute();
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

    }

    public void saveUserId(Token token){
        SharedPreferences sharedPref =
                getSharedPreferences(getString(R.string.id_user), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("LOGIN", token.getUserId());
        editor.commit();
    }
}
