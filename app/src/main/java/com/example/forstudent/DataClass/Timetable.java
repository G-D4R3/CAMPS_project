package com.example.forstudent.DataClass;

import java.util.ArrayList;
import java.util.Calendar;

public class Timetable{

    String classTitle="";
    String professorName="";
    ArrayList<Calendar> startTime;
    ArrayList<Calendar> endTime;
    ArrayList <String> classPlace;

    public Timetable(String classTitle, String professorName, ArrayList<Calendar> startTime, ArrayList<Calendar> endTime, ArrayList<String> classPlace) {
        this.classTitle = classTitle;
        this.professorName = professorName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classPlace = classPlace;
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

    public void setClassPlace(ArrayList<String> classPlace) {
        this.classPlace = classPlace;
    }

    public ArrayList<Calendar> getStartTime() {
        return startTime;
    }

    public void setStartTime(ArrayList<Calendar> startTime) {
        this.startTime = startTime;
    }

    public ArrayList<Calendar> getEndTime() {
        return endTime;
    }

    public void setEndTime(ArrayList<Calendar> endTime) {
        this.endTime = endTime;
    }






}
