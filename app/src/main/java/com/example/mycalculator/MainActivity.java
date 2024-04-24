package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button openBtn;
        Button connect3_button;
        Button btnInsta;
        Button btnExercise;
        Button btnMidterm;
        Button btnPassing;
        Button btnMenus;

        openBtn = (Button) findViewById(R.id.openBtn);
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(activity2Intent);
            }
        });

        btnInsta = (Button) findViewById(R.id.btnInsta);
        btnInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent activity1Intent = new Intent(getApplicationContext(), MainActivityInsta.class);
                startActivity(activity1Intent);
            }
        });

        btnExercise = (Button) findViewById(R.id.btnExercise);
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent activityIntent = new Intent(getApplicationContext(), ButtonLayout.class);
                startActivity(activityIntent);
            }
        });



        connect3_button = (Button) findViewById(R.id.connect3_button);
        connect3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent connect3Intent = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(connect3Intent);
            }
        });

        btnMidterm = (Button) findViewById(R.id.btnMidterm);
        btnMidterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent connect3Intent = new Intent(getApplicationContext(), MainMidterm.class);
                startActivity(connect3Intent);
            }
        });

        btnPassing = (Button) findViewById(R.id.btnPassing);
        btnPassing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent connect3Intent = new Intent(getApplicationContext(), MainActivityPassingIntents.class);
                startActivity(connect3Intent);
            }
        });

        btnMenus = (Button) findViewById(R.id.btnMenus);
        btnMenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent menusIntent = new Intent(getApplicationContext(), MenuExercise.class);
                startActivity(menusIntent);
            }
        });





    }


}