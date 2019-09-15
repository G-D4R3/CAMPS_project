package com.CAMPS.camps;

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
        Calendar tmp1 = Calendar.getInstance();
        tmp1.set(tcalendar.get(Calendar.YEAR), tcalendar.get(Calendar.MONTH), tcalendar.get(Calendar.DAY_OF_MONTH), 0,0);
        Calendar tmp2 = Calendar.getInstance();
        tmp2.set(dcalendar.get(Calendar.YEAR), dcalendar.get(Calendar.MONTH), dcalendar.get(Calendar.DAY_OF_MONTH), 0,0);

        Today = tmp1.getTimeInMillis()/(24*60*60*1000);
        setday = tmp2.getTimeInMillis()/(24*60*60*1000);

        left = (setday - Today);
        result = (int)left;

        return result;
    }
}
