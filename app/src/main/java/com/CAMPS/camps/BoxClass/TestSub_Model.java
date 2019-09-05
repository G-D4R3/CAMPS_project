package com.CAMPS.camps.BoxClass;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class TestSub_Model {

    @Id(assignable = true)
    long id;

    private String Name=null;

    private int Year=-1;
    private int Month;
    private int Day;

    private String Place=null;

    private int startHour=-1;
    private int startMinute;

    private int endHour=-1;
    private int endMinute;

    private String Range=null;

    public TestSub_Model (long id, String name, int year, int month, int day, String place, int starthour, int startminute, int endhour, int  endminute, String range){
        this.id =id;
        this.Name=name;
        this.Year = year;
        this.Month = month;
        this.Day = day;
        this.Place = place;
        this.startHour = starthour;
        this.startMinute = startminute;
        this.endHour = endhour;
        this.endMinute = endminute;
        this.Range = range;
    }
    public TestSub_Model(){}
    public void setName(String name){
        this.Name = name;
    }

    public void setYear(int year){
        this.Year = year;
    }

    public void setMonth(int month){
        this.Month = month;
    }

    public void setDay(int day){
        this.Day = day;
    }

    public void setPlace(String place){
        this.Place = place;
    }

    public void setStartHour(int sh){
        this.startHour = sh;
    }

    public void setStartMinute(int sm){
        this.startMinute = sm;
    }

    public void setEndHour(int eh){
        this.endHour = eh;
    }

    public void setEndMinute(int em){
        this.endMinute = em;
    }

    public void setRange(String range){
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

    public String getPlace(){
        return Place;
    }

    public int getStartHour(){
        return startHour;
    }

    public int getStartMinute(){
        return startMinute;
    }

    public int getEndHour(){
        return endHour;
    }

    public int getEndMinute(){
        return endMinute;
    }

    public String getRange(){
        return Range;
    }

}
