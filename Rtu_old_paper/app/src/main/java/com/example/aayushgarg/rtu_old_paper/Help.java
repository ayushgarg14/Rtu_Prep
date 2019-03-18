package com.example.aayushgarg.rtu_old_paper;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity {
private Button button;
private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    email=getString(R.string.email);
    button=(Button)findViewById(R.id.mail_us);
    button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Rtu_Prep");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }
});

 }

}
