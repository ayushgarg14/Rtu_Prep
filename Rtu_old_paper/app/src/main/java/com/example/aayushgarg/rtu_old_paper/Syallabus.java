package com.example.aayushgarg.rtu_old_paper;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Syallabus extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private DatabaseReference refrence;
    private List<SyllabusTable> datafield;
    private RecyclerView recyclerView;
    private DownloadManager downloadManager;
    private MyFunction instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syallabus);
        progressDialog = new ProgressDialog(this);
        datafield = new ArrayList<>();
        instance=MyFunction.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recpapersyllabus);  //change this

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        instance.setDownloadManager(downloadManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if(!checkInternet())
                Toast.makeText(Syallabus.this,"Please connect Your Internet",Toast.LENGTH_SHORT).show();

            progressDialog.setMessage("progress....");
            progressDialog.show();
            refrence = FirebaseDatabase.getInstance().getReference(instance.getYear() +"_"+ instance.getBranch()+"_"+instance.getOpption());
            refrence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        datafield.clear();


                        for (DataSnapshot ayush : dataSnapshot.getChildren()) {

                            SyllabusTable syllabusTable = ayush.getValue(SyllabusTable.class);
                            datafield.add(0, syllabusTable);

                        }
                        AdapterSyllabus adapter = new AdapterSyllabus(datafield, Syallabus.this);
                        recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();
                    } catch (Exception e)

                    {

                        Toast.makeText(Syallabus.this, e.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                }

                @Override


                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Syallabus.this, "not connect to server", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });


        }
        catch (Exception e)
        { progressDialog.dismiss();
            Toast.makeText(Syallabus.this, e.toString(), Toast.LENGTH_SHORT).show();
        }















    }

    public boolean checkInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else
            return false;


    }
}

