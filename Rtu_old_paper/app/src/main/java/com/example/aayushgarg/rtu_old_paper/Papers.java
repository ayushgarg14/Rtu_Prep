package com.example.aayushgarg.rtu_old_paper;


import android.app.ProgressDialog;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class Papers extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private DatabaseReference refrence;
    private List<Keytable> datafield;
    private RecyclerView recyclerView;
    private MyFunction instance;
    private String FIRST_YEAR_TAG;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers);
        progressDialog = new ProgressDialog(this);
        datafield = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recpaper);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
           FIRST_YEAR_TAG=getString(R.string.first_year_tag);
        instance=MyFunction.getInstance();


 }

    @Override
    protected void onStart() {
        super.onStart();
        if(!checkInternet())
            Toast.makeText(Papers.this,"Please connect Your Internet",Toast.LENGTH_SHORT).show();

        progressDialog.setMessage("progress....");
        progressDialog.show();
        String branch=instance.getBranch();
        String year=instance.getYear();
        String option=instance.getOpption();
        if(year.equals(FIRST_YEAR_TAG.toUpperCase()))
           branch="SAME";
        instance.setBranch(branch);
        refrence=FirebaseDatabase.getInstance().getReference(year+"_"+branch+"_"+option);
        refrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    datafield.clear();
                    for (DataSnapshot ayush : dataSnapshot.getChildren()) {
                        String aa= ayush.getKey();
                     Keytable paperTable = new Keytable(aa,"");
                        datafield.add(0,paperTable);

                    }
                    AddapterPaper adapter = new AddapterPaper(datafield, Papers.this);
                    recyclerView.setAdapter(adapter);
                    progressDialog.dismiss();
                } catch (Exception e)

                {

                    Toast.makeText(Papers.this, e.toString(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }

            }

            @Override
                public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Papers.this, "not connect to server", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }
    public boolean checkInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else
            return false;


    }


}

