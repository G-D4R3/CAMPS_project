package com.example.forstudent;

public class TestSub {


    //시험 정보 저장


    private String Name=null;

    private int Year=-1;
    private int Month;
    private int Day;

    private int startHour=-1;
    private int startMinute;

    private int endHour=-1;
    private int endMinute;

    private String Range=null;

    public TestSub(String name, int year, int month, int day, int starthour, int startminute, int endhour, int  endminute){
        this.Name=name;
        this.Year = year;
        this.Month = month;
        this.Day = day;
        this.startHour = starthour;
        this.startMinute = startminute;
        this.endHour = endhour;
        this.endMinute = endminute;
    }

    public TestSub(String name, int year, int month, int day, int starthour, int startminute, int endhour, int  endminute, String range){
        this.Name=name;
        this.Year = year;
        this.Month = month;
        this.Day = day;
        this.startHour = starthour;
        this.startMinute = startminute;
        this.endHour = endhour;
        this.endMinute = endminute;
        this.Range = range;
    }

    public String getName(){
        return Name;
    }

    public int getYear(){
        return Year;
    }

    public int getMonth(){
        return Month;
    }

    public int getDay(){
        return Day;
    }

    public int getStartHour(){
        return startHour;
    }

    public int getStartMinute(){
        return startMinute;
    }

    public int getEndHour(){
        return startHour;
    }

    public int getEndMinute(){
        return endMinute;
    }

    public String getRange(){
        if(Range!=null){
            return Range;
        }
        else{
            return "";
        }
    }



}
