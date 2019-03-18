package com.example.aayushgarg.rtu_old_paper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class tranning extends AppCompatActivity {
ImageView imageView;
TextView data;
    TextView address;
    TextView contact;
    FloatingActionButton fab;
    Intent intent;
    String url="";
    final FirebaseRemoteConfig mFirebaseRemoteConfig =FirebaseRemoteConfig.getInstance();
    ProgressDialog progressDialog;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranning);
       imageView=(ImageView)findViewById(R.id.tranningadd);
        data=(TextView)findViewById(R.id.contant);
        address=(TextView)findViewById(R.id.address1);
        contact=(TextView)findViewById(R.id.con);

       fab=(FloatingActionButton)findViewById(R.id.fabfortranning);
       intent=new Intent(Intent.ACTION_VIEW);
       progressDialog=new ProgressDialog(tranning.this);
       fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
try{
        url=DataBaseConnectivity.getTranningUrl();
      if(!url.equals("")) {
          intent.setData(Uri.parse(url));
          startActivity(intent);
      }
}catch (Exception e){}
    }
});

long cacheExpiration=0;
progressDialog.setMessage("wait for faching data from server");
progressDialog.show();
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(tranning.this, "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show();

                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(tranning.this, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        displayWelcomeMessage();
                    }
                });

    }
   public void displayWelcomeMessage(){
try {
    progressDialog.dismiss();
    url = mFirebaseRemoteConfig.getString("TranningUrl");


    String temp[]=mFirebaseRemoteConfig.getString("TranningText").split("//.//");

    data .setText(Html.fromHtml(temp[1]));

    address .setText(Html.fromHtml(temp[1]));

    contact .setText(Html.fromHtml(temp[1]));

    String u = mFirebaseRemoteConfig.getString("TranningImage");
    Glide.with(getApplicationContext()).load(u).into(imageView);

}catch (Exception e){

}

   }

   }

