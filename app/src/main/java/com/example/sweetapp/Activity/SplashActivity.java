package com.example.sweetapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.sweetapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5*1000);

                    Intent intent=new Intent(SplashActivity.this, ChooseTypeActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e){

                }

            }
        }; thread.start();









    }
}