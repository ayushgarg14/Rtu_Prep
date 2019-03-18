package com.example.aayushgarg.rtu_old_paper;

import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private CardView cardView4;
    private CardView cardView5;
    private CardView cardView6;
    private CardView cardView7;
    private CardView cardView8;
    private CardView cardView9;
    private  ImageView add1;

    private Intent intentOfClassBranch;
    private Intent intentOfClassAboutus;
    private Intent intentOfClassDownload;
    private Intent intentOfClassYear;
    private Intent intentOfClassBooks;
    private Intent intentOfClassTranning;
    private Intent intentOfClassHelp;
    private MyFunction instance;
    private boolean connect;
    private FirebaseAuth auth;
    private  int out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("RTU_OLD_PAPER (BTech)");
          out=0;
        auth = FirebaseAuth.getInstance();



        cardView1 = (CardView) findViewById(R.id.paper);
        cardView2 = (CardView) findViewById(R.id.syllabus);
        cardView3 = (CardView) findViewById(R.id.book);
        cardView4 = (CardView) findViewById(R.id.traning);
        cardView5 = (CardView) findViewById(R.id.Home);
        cardView6 = (CardView) findViewById(R.id.Download1);
        cardView7 = (CardView) findViewById(R.id.Help1);
        cardView8 = (CardView) findViewById(R.id.Aboutus1);
        cardView9 = (CardView) findViewById(R.id.SignOut1);


        add1=(ImageView)findViewById(R.id.add1);


        intentOfClassBranch = new Intent(this, Branch.class);
        intentOfClassAboutus = new Intent(this, aboutUs.class);
        intentOfClassDownload = new Intent(this, folder.class);
        intentOfClassYear=new Intent(this,opption.class);
        intentOfClassBooks=new Intent(this,Books.class);
        intentOfClassTranning=new Intent(this,tranning.class);
        intentOfClassHelp=new Intent(this,Help.class);







        instance = MyFunction.getInstance();

/*
click listener for select old paper card view
 */
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setOpption("Paper".toUpperCase());

                startActivity(intentOfClassYear);

            }
        });

    /*
click listener for select syllabus card view
 */


        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.setOpption("syllabus".toUpperCase());
                startActivity(intentOfClassYear);
            }
        });

 /*
click listener for select books card view
 */
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(checkInternet())
                startActivity(intentOfClassBooks);
            else
                Toast.makeText(MainPage.this,"NoInternetConnection",Toast.LENGTH_SHORT).show();
            }
        });

  /*
click listener for select traning card view
 */
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInternet())
                    startActivity(intentOfClassTranning);
                else
                    Toast.makeText(MainPage.this,"NoInternetConnection",Toast.LENGTH_SHORT).show();
            }


        });
/*
click listener for select Home card view
 */
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
               }


        });



   /*
click listener for select Download card view
 */
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentOfClassDownload);
            }
        });
         /*
click listener for select Help card view
 */
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentOfClassHelp);
            }
        });


                /*
click listener for select Aboutus card view
 */
        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentOfClassAboutus);
            }
        });



              /*
click listener for select signout card view
 */
        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               signOut();
            }
        });







     /*   insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent=new Intent(Intent.ACTION_VIEW);
                final String url2="https://www.instagram.com/jokerz_z_z/";
                intent.setData(Uri.parse(url2));
                startActivity(intent);

            }
        });
*/

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.CONTROL_LOCATION_UPDATES) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED&& ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.CONTROL_LOCATION_UPDATES, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE}, 10);
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_NETWORK_STATE, }, 10);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CONTROL_LOCATION_UPDATES}, 10);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 10);

        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == 10) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "permission", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "permission denite", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onBackPressed() {
        out++;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (out == 1) {
                Toast.makeText(this, "Back again for exit", Toast.LENGTH_SHORT).show();


            } else {
                super.onBackPressed();

            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (checkInternet() == false)
            Toast.makeText(this, "Check Your Internet Cnnection", Toast.LENGTH_LONG).show();
   //     String url = "https://firebasestorage.googleapis.com/v0/b/rtuoldpaper.appspot.com/o/dit.png?alt=media&token=42f3e119-a801-46f5-a9e7-215cd3284266";
     //  Glide.with(getApplicationContext()).load(url).into(add1);
    }

// user Define function


    public boolean checkInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else
            return false;


    }

    public void signOut() {
        auth.signOut();
        startActivity(new Intent(MainPage.this, Login_Page.class));
        finish();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }




}








