package com.example.forstudent.DataClass;

import com.github.tlaabs.timetableview.Time;

public class Timetable{
    static final int MON = 0;
    static final int TUE = 1;
    static final int WED = 2;
    static final int THU = 3;
    static final int FRI = 4;
    static final int SAT = 5;
    static final int SUN = 6;

    String classTitle="";

    String classPlace_1="";
    String classPlace_2="";
    String classPlace_3="";

    String professorName="";
    private int day = 0;
    private Time startTime_1;
    private Time startTime_2;
    private Time startTime_3;
    private Time endTime_1;
    private Time endTime_2;
    private Time endTime_3;

    public Timetable() {
        this.startTime_1 = new Time();
        this.endTime_1 = new Time();
        this.startTime_2 = new Time();
        this.endTime_2 = new Time();
        this.startTime_3 = new Time();
        this.endTime_3 = new Time();
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getClassPlace_1() {
        return classPlace_1;
    }
    public String getClassPlace_2(){
        return classPlace_2;
    }

    public String getClassPlace_3(){
        return classPlace_3;
    }

    public void setClassPlace_1(String classPlace) {
        this.classPlace_1 = classPlace;
    }

    public void setClassPlace_2(String classPlace) {
        this.classPlace_2 = classPlace;
    }

    public void setClassPlace_3(String classPlace){
        this.classPlace_3 = classPlace;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Time getStartTime1() {
        return startTime_1;
    }

    public Time getStartTime2(){
        return startTime_2; }

    public Time getStartTime3(){
        return startTime_3; }

    public void setStartTime1(Time startTime) {
        this.startTime_1 = startTime;
    }

    public void setStartTime2(Time startTime) {this.startTime_2 = startTime;}

    public void setStartTime3(Time startTime) {this.startTime_3 = startTime;}

    public Time getEndTime1() {
        return endTime_1;
    }

    public Time getEndTime2(){ return endTime_2;}

    public Time getEndTime3() { return endTime_3; }

    public void setEndTime1(Time endTime) {
        this.endTime_1 = endTime;
    }

    public void setEndTime2(Time endTime) { this.endTime_2 = endTime; }

    public void setEndTime3(Time endTime) { this.endTime_3 = endTime; }






}
