package com.example.aayushgarg.rtu_old_paper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Branch extends AppCompatActivity {
    private CardView cardView1;
    private  CardView cardView2;
    private  CardView cardView3;
    private  CardView cardView4;
    private  CardView cardView5;
    private  CardView cardView6;
    private  Intent intent1;
    private Intent intent;
    private MyFunction instance;
    private String PAPER_TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        instance=MyFunction.getInstance();
        PAPER_TAG=getString(R.string.paper_tag);
        intent=new Intent(this,Papers.class);
        intent1=new Intent(this,Syallabus.class);
        cardView1=(CardView)findViewById(R.id.cs);
        cardView2=(CardView)findViewById(R.id.it);
        cardView3=(CardView)findViewById(R.id.mc);
        cardView4=(CardView)findViewById(R.id.cicil);
        cardView5=(CardView)findViewById(R.id.ec);
        cardView6=(CardView)findViewById(R.id.ee);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setBranch("CS");
        if(instance.getOpption().equals(PAPER_TAG.toUpperCase()))
              startActivity(intent);
        else
                startActivity(intent1);

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setBranch("IT");
                if(instance.getOpption().equals(PAPER_TAG.toUpperCase()))
                    startActivity(intent);
                else
                    startActivity(intent1);


            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setBranch("ME");
                if(instance.getOpption().equals(PAPER_TAG.toUpperCase()))
                    startActivity(intent);
                else
                    startActivity(intent1);


            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setBranch("CE");
                if(instance.getOpption().equals(PAPER_TAG.toUpperCase()))
                    startActivity(intent);
                else
                    startActivity(intent1);

            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setBranch("EC");;
                if(instance.getOpption().equals(PAPER_TAG.toUpperCase()))
                    startActivity(intent);
                else
                    startActivity(intent1);

            }
        });cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setBranch("EE");
                if(instance.getOpption().equals(PAPER_TAG.toUpperCase()))
                    startActivity(intent);
                else
                    startActivity(intent1);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
