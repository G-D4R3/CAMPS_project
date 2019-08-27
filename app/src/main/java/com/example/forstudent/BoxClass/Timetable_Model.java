package com.example.forstudent.BoxClass;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Timetable_Model {
    @Id(assignable = true)
    long id;

    int hour1;
    int hour2;
    int hour3;

    int minute1;
    int minute2;
    int minute3;

    int dayOfWeek1;
    int dayOfWeek2;
    int dayOfWeek3;


    String professor;
    String subject;

    String lectureRoom1;
    String lectureRoom2;
    String lectureRoom3;

    public Timetable_Model(long id, int hour1, int hour2, int hour3, int minute1, int minute2, int minute3, int dayOfWeek1, int dayOfWeek2, int dayOfWeek3, String professor, String subject, String lectureRoom1, String lectureRoom2, String lectureRoom3) {
        this.id = id;
        this.hour1 = hour1;
        this.hour2 = hour2;
        this.hour3 = hour3;
        this.minute1 = minute1;
        this.minute2 = minute2;
        this.minute3 = minute3;
        this.dayOfWeek1 = dayOfWeek1;
        this.dayOfWeek2 = dayOfWeek2;
        this.dayOfWeek3 = dayOfWeek3;
        this.professor = professor;
        this.subject = subject;
        this.lectureRoom1 = lectureRoom1;
        this.lectureRoom2 = lectureRoom2;
        this.lectureRoom3 = lectureRoom3;
    }

    public void setHour1(int hour){
        this.hour1 = hour;
    }

    public void setHour2(int hour) {
        this.hour2 = hour;
    }

    public void setHour3(int hour){
        this.hour3 = hour;
    }

    public void setMinute1(int minute){
        this.minute1 = minute;
    }

    public void setMinute2(int minute){
        this.minute2 = minute;
    }

    public void setMinute3(int minute){
        this.minute3 = minute;
    }

    public void setDayOfWeek1(int dayOfWeek){
        this.dayOfWeek1 = dayOfWeek;
    }

    public void setDayOfWeek2(int dayOfWeek){
        this.dayOfWeek2 = dayOfWeek;
    }

    public void setDayOfWeek3(int dayOfWeek){
        this.dayOfWeek3 = dayOfWeek;
    }

    public void setProfessor(String professor){
        this.professor = professor;
    }

    public void setLectureRoom1(String lectureRoom){
        this.lectureRoom1 = lectureRoom;
    }

    public void setLectureRoom2(String lectureRoom){
        this.lectureRoom2 = lectureRoom;
    }

    public void setLectureRoom3(String lectureRoom){
        this.lectureRoom3 = lectureRoom;
    }

    public void setSubject(String subject){
        this.subject = subject;

    }

    public int getHour1(){
        return hour1;
    }

    public int getHour2(){
        return hour2;
    }

    public int getHour3(){
        return hour3;
    }

    public int getMinute1(){
        return minute1;
    }

    public int getMinute2(){
        return minute2;
    }

    public int getMinute3(){
        return minute3;
    }

    public int getDayOfWeek1() {
        return dayOfWeek1;
    }

    public int getDayOfWeek2() {
        return dayOfWeek2;
    }

    public int getDayOfWeek3() {
        return dayOfWeek3;
    }

    public String getProfessor(){
        return professor;
    }

    public String getLectureRoom1(){
        return lectureRoom1;
    }

    public String getLectureRoom2(){
        return lectureRoom2;
    }

    public String getLectureRoom3(){
        return lectureRoom3;
    }

    public String getSubject(){
        return subject;
    }
}
