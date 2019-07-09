package com.example.forstudent;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class dateCount {

    protected TextView Dday;
    protected TextView today;

    //오늘 날짜
    protected int tyear;
    protected int tmonth;
    protected int tday;

    //dday 설정 날짜
    protected int dyear=2019;
    protected int dmonth=12;
    protected int dday=31;

    protected long Today;
    protected long setday;
    protected long left;
    protected int result=0;

    Calendar tcalendar = Calendar.getInstance();
    Calendar dcalendar = Calendar.getInstance();

    public dateCount(TextView Dday, TextView today){
        this.Dday = Dday;
        this.today = today;
        //현재 날짜

        tyear = tcalendar.get(Calendar.YEAR);
        tmonth = tcalendar.get(Calendar.MONTH);
        tday = tcalendar.get(Calendar.DAY_OF_MONTH);

        dcalendar.set(dyear, dmonth, dday);

        Today = tcalendar.getTimeInMillis();
        setday = dcalendar.getTimeInMillis();
        left = (setday - Today)/(24*60*60*1000);
        result = (int)left;

        today.setText(String.format("오늘은 %d월 %d일", tmonth+1, tday));
    }


    public void refreshDate(int result){

        today.setText(String.format("오늘은 %d월 %d일", tmonth+1, tday));
        if(result>0){
            Dday.setText(String.format("종강까지 D-%d", result));
        }
        else if(result==0){
            Dday.setText(new String("종강 D-day"));
        }
        else{
            int temp = Math.abs(result);
            Dday.setText(String.format("종강하고 D+%d", temp));
        }

    }


    public void resultion() { //여기서 캐시 접근해서 저장해놔야함
        dcalendar.set(dyear, dmonth, dday);
        setday = dcalendar.getTimeInMillis();
        left = (setday - Today)/(24*60*60*1000);
        this.result = (int)left;
    }
}
