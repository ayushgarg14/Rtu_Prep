package com.example.aayushgarg.rtu_old_paper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by aayushgarg on 2/1/19.
 */

public class DataBaseConnectivity {
   private DatabaseReference Appdatarefrence;
   private static final FirebaseRemoteConfig remoteConfig =FirebaseRemoteConfig.getInstance();
   public static  float getVersion(){
     return (float) 3.0333;
    }


    public static String getTranningImage(){
       return remoteConfig.getString("TranningImage");
    }

    public static String getTranningText(){
        return remoteConfig.getString("TranningText");
    }


    public static String getTranningUrl() {
         return remoteConfig.getString("TranningUrl");
    }
}
