package com.example.aayushgarg.rtu_old_paper;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Page extends AppCompatActivity {
    private Button signupButton,loginButton;
    private EditText emailId,password ;
    private TextView btnReset;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);
        signupButton = (Button) findViewById(R.id.signupButton);
            emailId = (EditText) findViewById(R.id.usernameLogin);
            password = (EditText) findViewById(R.id.passwordLogin);
            loginButton = (Button) findViewById(R.id.loginbutton);
            btnReset = (TextView) findViewById(R.id.forgetButton);
            auth = FirebaseAuth.getInstance();
            progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (auth.getCurrentUser() != null) {
               startActivity(new Intent(Login_Page.this, MainPage.class));      //id allready login
            finish();
        }

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Page.this,Sign_up.class));
            finish();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Page.this,Reset_Password.class));

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInternet()) {

                    String email = emailId.getText().toString();
                    final String passwordI = password.getText().toString();

                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(passwordI)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);
try {


                    //authenticate user
                    auth.signInWithEmailAndPassword(email, passwordI)
                            .addOnCompleteListener(Login_Page.this, new OnCompleteListener<AuthResult>() {

                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    progressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (passwordI.length() < 6) {
                                            password.setError("Weak Password");
                                        } else {
                                            Toast.makeText(Login_Page.this, "Incorrect PassWord Or Email", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Intent intent = new Intent(Login_Page.this, MainPage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }catch (Exception e)
                   {
                       Toast.makeText(Login_Page.this, "Unable to Login at this time", Toast.LENGTH_LONG).show();

                   }
                   finally {

                   }
                }

                else {
                    Toast.makeText(Login_Page.this,"Check Your Internet Cnnection",Toast.LENGTH_LONG).show();

                }

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