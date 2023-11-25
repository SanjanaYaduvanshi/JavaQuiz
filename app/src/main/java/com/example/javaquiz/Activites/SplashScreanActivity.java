package com.example.javaquiz.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.javaquiz.MainActivity;
import com.example.javaquiz.R;

public class SplashScreanActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screan);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(SplashScreanActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);
    }
}