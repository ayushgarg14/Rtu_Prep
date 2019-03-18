package com.example.aayushgarg.rtu_old_paper;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class aboutUs extends AppCompatActivity {

private Intent intent;
private ImageView gmail;
private ImageView linkin;
private String gmailId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        gmail=(ImageView)findViewById(R.id.Gmail);
        linkin=(ImageView)findViewById(R.id.LinkIn);
        final String linkinurl="google.com";
        final String gmailurl="aayushgarg14@gmail.com";

        intent=new Intent(Intent.ACTION_VIEW);
        linkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setData(Uri.parse(linkinurl));
                startActivity(intent);


            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",gmailurl, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Rtu_Paper");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "if you want append some text");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });




    }
}
