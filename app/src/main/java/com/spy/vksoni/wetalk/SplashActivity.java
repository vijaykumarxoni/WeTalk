package com.spy.vksoni.wetalk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar=(ProgressBar)findViewById(R.id.progressBarSplashScreen);

        new Thread( new Runnable(){


            @Override
            public void run() {
                int i=0;
                progressBar.setMax(50);
                while(i<50){

                    try{

                        Thread.sleep(500);
                        progressBar.setProgress(i);
                    }catch (Exception e){}
                    i+=10;
                }
                if(i==50){
                    SharedPreferences sharedPreferencespre =getSharedPreferences("Login", Context.MODE_PRIVATE);
                    if(!(sharedPreferencespre.getString("Email","")=="" && sharedPreferencespre.getString("Password","")=="")){
                        startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                        finish();}
                    else{
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finish();

                    }

                }
            }
        }).start();

    }
}
