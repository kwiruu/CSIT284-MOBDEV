package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityPassingIntents2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_passing_intents2);

        Button btnReturn = findViewById(R.id.btnReturn);

        TextView tFname = findViewById(R.id.Fname_text);
        TextView tLname = findViewById(R.id.Lname_text);
        TextView tGender = findViewById(R.id.Gender_text);
        TextView tBdate = findViewById(R.id.Bdate_text);
        TextView tPnum = findViewById(R.id.Pnum_text);
        TextView tEaddress = findViewById(R.id.Eaddress_text);
        TextView tHaddress = findViewById(R.id.Haddress_text);
        TextView tHeight = findViewById(R.id.Height_text);
        TextView tWeight = findViewById(R.id.Weight_text);
        TextView tCourse = findViewById(R.id.Course_text);
        TextView tYlvl = findViewById(R.id.Ylvl_text);

        Intent intent = getIntent();

        String fname = intent.getStringExtra("fname_key");
        String lname = intent.getStringExtra("lname_key");
        String gender = intent.getStringExtra("gender_key");
        String bdate = intent.getStringExtra("bdate_key");
        String pnum = intent.getStringExtra("pnum_key");
        String eadd = intent.getStringExtra("eadd_key");
        String hadd = intent.getStringExtra("hadd_key");
        String height = intent.getStringExtra("height_key");
        String weight = intent.getStringExtra("weight_key");
        String course = intent.getStringExtra("course_key");
        String ylvl = intent.getStringExtra("ylvl_key");


        tFname.setText(fname);
        tLname.setText(lname);
        tGender.setText(gender);
        tBdate.setText(bdate);
        tPnum.setText(pnum);
        tEaddress.setText(eadd);
        tHaddress.setText(hadd);
        tHeight.setText(height);
        tWeight.setText(weight);
        tCourse.setText(course);
        tYlvl.setText(ylvl);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent connect3Intent = new Intent(getApplicationContext(), MainActivityPassingIntents.class);
                startActivity(connect3Intent);
            }
        });
    }
}