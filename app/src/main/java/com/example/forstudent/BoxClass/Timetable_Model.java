package com.example.forstudent.BoxClass;


import java.sql.Time;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Timetable_Model {
    @Id(assignable = true)
    long id;

    int hour;
    int minute;
    int dayOfWeek;

    String professor;
    String lectureRoom;
    String subject;

    public Timetable_Model(long id, int hour, int minute, int dayOfweek, String professor, String lectureRoom, String subject){
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.dayOfWeek = dayOfweek;
        this.professor = professor;
        this.lectureRoom = lectureRoom;
        this.subject = subject;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public void setDayOfWeek(int dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    public void setProfessor(String professor){
        this.professor = professor;
    }

    public void setLectureRoom(String lectureRoom){
        this.lectureRoom = lectureRoom;
    }

    public void setSubject(String subject){
        this.subject = subject;

    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public String getProfessor(){
        return professor;
    }

    public String getLectureRoom(){
        return lectureRoom;
    }

    public String getSubject(){
        return subject;
    }
}
