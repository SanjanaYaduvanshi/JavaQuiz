package com.example.javaquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.javaquiz.Activites.SetsActivity;



public class MainActivity extends AppCompatActivity {

   CardView history,science,geography,mathematic,gk,economics,agriculture,sports;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        science = findViewById(R.id.Science);
science.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, SetsActivity.class);
        startActivity(intent);
        finish();
    }
});

        history = findViewById(R.id.history);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SetsActivity.class);
                startActivity(intent);
                finish();

            }
        });


        geography = findViewById(R.id.Geography);

        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SetsActivity.class);
                startActivity(intent);
                finish();

            }
        });

        mathematic = findViewById(R.id.Mathematic);

        mathematic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SetsActivity.class);
                startActivity(intent);
                finish();

            }
        });
        gk = findViewById(R.id.GK);

        gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SetsActivity.class);
                startActivity(intent);
                finish();

            }
        });


        economics = findViewById(R.id.Economics);

        economics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SetsActivity.class);
                startActivity(intent);
                finish();

            }
        });


        agriculture = findViewById(R.id.Agriculture);

        agriculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SetsActivity.class);
                startActivity(intent);
                finish();

            }
        });


        sports = findViewById(R.id.Sports);

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SetsActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}

