package com.example.aayushgarg.rtu_old_paper;

/**
 * Created by aayushgarg on 6/7/18.
 */

public class PaperTable {
    private String nameofsub;
    private String year;
public PaperTable() {
    }

    public PaperTable(String nameofsub, String year) {
        this.nameofsub = nameofsub;
        this.year = year;
    }


    public String getNameofsub() {
        return nameofsub;
    }

    public String getYear() {
        return year;
    }
}
