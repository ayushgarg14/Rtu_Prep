package com.example.aayushgarg.rtu_old_paper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class opption extends AppCompatActivity {
    private CardView first_Year;
    private CardView secound_Year;
    private CardView third_Year;
    private CardView fourth_Year;
    private MyFunction instance;
    private Intent intent;
    private String FIRST_YEAR_TAG;
    private String SECOUND_YEAR_TAG;
    private String THIRD_YEAR_TAG;
    private String FORTH_YEAR_TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opption);

        FIRST_YEAR_TAG=getString(R.string.first_year_tag);
        SECOUND_YEAR_TAG=getString(R.string.secound_year_tag);
        THIRD_YEAR_TAG=getString(R.string.third_year_tag);
        FORTH_YEAR_TAG=getString(R.string.forth_year_tag);

        first_Year=(CardView)findViewById(R.id.First_Year);
        secound_Year=(CardView)findViewById(R.id.Second_Year);
        third_Year=(CardView)findViewById(R.id.Third_Year);
        fourth_Year=(CardView)findViewById(R.id.Fourth_Year);

        instance = MyFunction.getInstance();
        intent=new Intent(opption.this,Branch.class);

        first_Year.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          instance.setYear(FIRST_YEAR_TAG.toUpperCase());
        startActivity(intent);
            }
        });
        secound_Year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setYear(SECOUND_YEAR_TAG.toUpperCase());
                startActivity(intent);
            }
        });
        third_Year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setYear(THIRD_YEAR_TAG.toUpperCase());
                startActivity(intent);
            }
        });
        fourth_Year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setYear(FORTH_YEAR_TAG.toUpperCase());
                startActivity(intent);
            }
        });

    }
}
