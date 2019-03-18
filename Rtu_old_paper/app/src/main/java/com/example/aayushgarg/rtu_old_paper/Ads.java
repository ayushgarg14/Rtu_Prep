package com.example.aayushgarg.rtu_old_paper;

/**
 * Created by aayushgarg on 6/24/18.
 */

public class Ads {
    private static String[] banner;
    private static String[] full;
private Ads instance=new Ads();
    private  Ads() {
  }

    public Ads getInstance() {
        return instance;
    }

    public static String[] getBanner() {
        return banner;
    }

    public static String[] getFull() {
        return full;
    }
}
