package com.example.forstudent.DataClass;

import com.github.tlaabs.timetableview.Time;

import java.util.ArrayList;
import java.util.Calendar;

public class Timetable{

    String classTitle="";

    ArrayList <String> classPlace;

    String professorName="";

    ArrayList<Calendar> startTime;
    ArrayList<Calendar> endTime;

    public Timetable() {
        startTime = new ArrayList<Calendar>();
        endTime = new ArrayList<Calendar>();
        classPlace = new ArrayList<String>();
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

    public ArrayList<String> getClassPlace_1() {
        return classPlace;
    }

    public void setClassPlace_1(ArrayList<String> classPlace) {
        this.classPlace = classPlace;
    }

    public ArrayList<Calendar> getStartTime1() {
        return startTime;
    }

    public void setStartTime(ArrayList<Calendar> startTime) {
        this.startTime = startTime;
    }

    public ArrayList<Calendar> getEndTime() {
        return endTime;
    }

    public void setEndTime1(ArrayList<Calendar> endTime) {
        this.endTime = endTime;
    }






}
