package com.CAMPS.camps.DataClass;

import java.util.Calendar;

public class HomeTimeTable {
    public String classTitle="";
    public Calendar startTime;
    public Calendar endTime;
    public String classPlace;

    public HomeTimeTable(String name, Calendar start, Calendar end, String place){
        this.classTitle = name;
        this.startTime = start;
        this.endTime = end;
        this.classPlace = place;
    }
}
