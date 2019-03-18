package com.example.aayushgarg.rtu_old_paper;

import android.app.DownloadManager;

import com.google.android.gms.ads.AdRequest;

/**
 * Created by aayushgarg on 6/7/18.
 */

public class MyFunction {
private DownloadManager downloadManager;
private String Branch;
private String Year;
private String subname;
static MyFunction instance=new MyFunction();
    AdRequest adRequest ;
    private  String opption ;

    public static void setInstance(MyFunction instance) {
        MyFunction.instance = instance;
    }

    public String getOpption() {
        return opption;
    }

    public void setOpption(String opption) {
        this.opption = opption;
    }

    public AdRequest getAdRequest() {
        return adRequest;
    }

    public void setAdRequest(AdRequest adRequest) {
        this.adRequest = adRequest;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    private  MyFunction() {
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public static MyFunction getInstance() {
        return instance;
    }

    public DownloadManager getDownloadManager() {
        return downloadManager;
    }

    public void setDownloadManager(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }











}
