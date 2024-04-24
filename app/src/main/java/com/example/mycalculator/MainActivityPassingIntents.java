package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityPassingIntents extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_passing_intents);

        Button btnClear;
        Button btnSubmit;

        EditText Fname = findViewById(R.id.Fname);
        EditText Lname = findViewById(R.id.Lname);
        RadioButton rbMale = findViewById(R.id.rbMale);
        RadioButton rbFemale = findViewById(R.id.rbFemale);
        RadioButton rbOthers = findViewById(R.id.rbOthers);
        EditText Bdate = findViewById(R.id.Bdate);
        EditText Pnum = findViewById(R.id.Pnum);
        EditText Eaddress = findViewById(R.id.Eaddress);
        EditText Haddress = findViewById(R.id.Haddress);
        EditText Height = findViewById(R.id.Height);
        EditText Weight = findViewById(R.id.Weight);
        EditText Ylvl = findViewById(R.id.Ylvl);
        EditText Course = findViewById(R.id.Course);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear EditText fields
                Fname.setText("");
                Lname.setText("");
                Bdate.setText("");
                Pnum.setText("");
                Eaddress.setText("");
                Haddress.setText("");
                Height.setText("");
                Weight.setText("");
                Course.setText("");
                Ylvl.setText("");

                // Clear RadioButton selection
                rbMale.setChecked(false);
                rbFemale.setChecked(false);
                rbOthers.setChecked(false);
            }
        });

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                String fname= Fname.getText().toString();
                String lname= Lname.getText().toString();

                String gender;

                if(rbMale.isChecked()){
                    gender = "Male";
                } else if (rbFemale.isChecked()) {
                    gender = "Female";
                }
                else if(rbOthers.isChecked()){
                    gender = "Others";
                }else {
                    gender="Not Specified";
                }

                String bdate = Bdate.getText().toString();
                String pnum = Pnum.getText().toString();
                String eadd = Eaddress.getText().toString();
                String hadd = Haddress.getText().toString();
                String height = Height.getText().toString();
                String weight = Weight.getText().toString();
                String course = Course.getText().toString();
                String ylvl = Ylvl.getText().toString();

                Intent intent = new Intent(MainActivityPassingIntents.this, MainActivityPassingIntents2.class);
                intent.putExtra("fname_key", fname);
                intent.putExtra("lname_key", lname);
                intent.putExtra("gender_key", gender);
                intent.putExtra("bdate_key", bdate);
                intent.putExtra("pnum_key", pnum);
                intent.putExtra("eadd_key", eadd);
                intent.putExtra("hadd_key", hadd);
                intent.putExtra("height_key", height);
                intent.putExtra("weight_key", weight);
                intent.putExtra("course_key", course);
                intent.putExtra("ylvl_key", ylvl);

                startActivity(intent);
            }
        });



    }
}