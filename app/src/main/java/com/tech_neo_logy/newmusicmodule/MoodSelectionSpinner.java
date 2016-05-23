package com.tech_neo_logy.newmusicmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MoodSelectionSpinner extends AppCompatActivity {

    private Spinner spnr;
    String[] moods = {
            "NONE",
            "HappY",
            "Sad",
            "studious"};

    private Button tellMood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_selection_spinner);


        setContentView(R.layout.activity_mood_selection_spinner);

        spnr = (Spinner)findViewById(R.id.spinner);
        tellMood = (Button) findViewById(R.id.tellMood);
        tellMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MoodPrediction.class);
                startActivity(intent);
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, moods);

        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = spnr.getSelectedItemPosition();

                        //  Toast.makeText(getApplicationContext(),"You have selected "+celebrities[+position],Toast.LENGTH_LONG).show();

                        // TODO Auto-generated method stub

                        if(position==1)
                        {
                            Intent intra = new Intent(getApplicationContext(),Navigation.class);
                            intra.putExtra("mood","cheerful");
                            startActivity(intra);
                            Toast.makeText(getApplicationContext()," please choose books",Toast.LENGTH_LONG).show();


                        }
                        if(position==2)
                        {
                            Intent intr = new Intent(getApplicationContext(),Navigation.class);
                            intr.putExtra("mood","sad");
                            startActivity(intr);
                            Toast.makeText(getApplicationContext()," Sad mood pdf here",Toast.LENGTH_LONG).show();

                        }
                        if(position==3)
                        {
                            Intent in = new Intent(getApplicationContext(),Navigation.class);
                            in.putExtra("mood","studious");
                            startActivity(in);
                            Toast.makeText(getApplicationContext()," studious mood pdf",Toast.LENGTH_LONG).show();
                        }


                    }



                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );
        
    }
}
