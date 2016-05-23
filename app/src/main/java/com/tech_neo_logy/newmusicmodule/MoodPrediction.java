package com.tech_neo_logy.newmusicmodule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MoodPrediction extends AppCompatActivity  {
    RadioButton radioQ1yes, radioQ2yes, radioQ3yes, radioQ1no, radioQ2no, radioQ3no;
    Button getMood;
    private static int sad = 0;
    private static int cheerful = 0;
    private String mood = "cheerful";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_prediction);
        radioQ1yes = (RadioButton) findViewById(R.id.radioQ1yes);
        radioQ2yes = (RadioButton) findViewById(R.id.radioQ2yes);
        radioQ3yes = (RadioButton) findViewById(R.id.radioQ3yes);
        radioQ1no = (RadioButton) findViewById(R.id.radioQ1no);
        radioQ2no = (RadioButton) findViewById(R.id.radioQ2no);
        radioQ3no = (RadioButton) findViewById(R.id.radioQ3no);
        getMood = (Button) findViewById(R.id.btnGetMood);
        radioQ1no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sad = sad +1;
            }
        });
        radioQ2no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sad = sad +1;
            }
        });

        radioQ3no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sad = sad +1;
            }
        });


        radioQ1yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheerful = cheerful +1;
            }
        });

        radioQ2yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheerful = cheerful +1;
            }
        });

        radioQ2yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheerful = cheerful +1;
            }
        });

        if (cheerful > sad)
            mood = "cheerful";
        else
            mood = "sad";


        getMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Navigation.class);
                intent.putExtra("mood",mood);
                startActivity(intent);
            }
        });

    }



}
