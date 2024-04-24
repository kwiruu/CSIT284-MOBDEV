package com.example.mycalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ButtonLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openBtn;
        Button toastBtn;
        Button changeButtonBGbtn;
        Button changeBGbtn;
        View activity_main;
        Button disappearBtn;
        Button connect3_button;

        activity_main = findViewById(R.id.Main_Constraint);

        openBtn = (Button) findViewById(R.id.openBtn);
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(activity2Intent);
            }
        });

        toastBtn = (Button) findViewById(R.id.toastBtn);
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ButtonLayout.this, "Bold ni val", Toast.LENGTH_LONG).show();
            }
        });

        changeButtonBGbtn = (Button) findViewById(R.id.changeButtonBGbtn);
        changeButtonBGbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random color = new Random();
                changeButtonBGbtn.setBackgroundColor(Color.argb(255, color.nextInt(255), color.nextInt(255), color.nextInt(255)));
            }
        });

        changeBGbtn = (Button) findViewById(R.id.changeBGbtn);
        changeBGbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random color = new Random();
                int red = color.nextInt(255);
                int blue = color.nextInt(255);
                int green = color.nextInt(255);
                int a = color.nextInt(255);
                activity_main.setBackgroundColor(Color.argb(a, red, green, blue));
            }
        });

        disappearBtn = (Button) findViewById(R.id.dissapearBtn);
        disappearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View b = findViewById(R.id.dissapearBtn);
                b.setVisibility(View.GONE);

                int delayMillis = 5000;

                Handler handler = new Handler();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        b.setVisibility(View.VISIBLE);
                    }
                };
                handler.postDelayed(runnable, delayMillis);
            }
        });






    }


}