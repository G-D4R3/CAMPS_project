package com.example.forstudent;

import java.util.Calendar;

public class DateCount {

    protected long Today;
    protected long setday;

    protected long left;
    protected int result=0;

    Calendar tcalendar = Calendar.getInstance(); //오늘 날짜
    Calendar dcalendar = Calendar.getInstance(); //D-day 날짜

    public DateCount(){
    }

    public DateCount(Calendar dcalendar){
        this.dcalendar = dcalendar;
    }


    public int calcDday(){
        Today = tcalendar.getTimeInMillis()/(24*60*60*1000);
        setday = dcalendar.getTimeInMillis()/(24*60*60*1000);

        left = (setday - Today);
        result = (int)left;

        return result;
    }
}
