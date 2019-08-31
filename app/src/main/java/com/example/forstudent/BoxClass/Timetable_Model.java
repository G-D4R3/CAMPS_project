package com.example.forstudent.BoxClass;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Timetable_Model {
    @Id(assignable = true)
    long id;

    int start_hour;
    int start_minute;
    int end_hour;
    int end_minute;

    int dayOfWeek;


    String professor;
    String subject;

    String lectureRoom;


    public Timetable_Model(long id, int start_hour, int start_minute, int end_hour, int end_minute,  int dayOfWeek, String professor, String subject, String lectureRoom) {
        this.id = id;
        this.start_hour = start_hour;
        this.start_minute = start_minute;
        this.end_hour = end_hour;
        this.end_minute = end_minute;
        this.dayOfWeek = dayOfWeek;
        this.professor = professor;
        this.subject = subject;
        this.lectureRoom = lectureRoom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLectureRoom() {
        return lectureRoom;
    }

    public void setLectureRoom(String lectureRoom) {
        this.lectureRoom = lectureRoom;
    }

    public int getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(int start_hour) {
        this.start_hour = start_hour;
    }

    public int getStart_minute() {
        return start_minute;
    }

    public void setStart_minute(int start_minute) {
        this.start_minute = start_minute;
    }

    public int getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(int end_hour) {
        this.end_hour = end_hour;
    }

    public int getEnd_minute() {
        return end_minute;
    }

    public void setEnd_minute(int end_minute) {
        this.end_minute = end_minute;
    }

    @Override
    public String toString() {
        return "Timetable_Model{" + '\n' +
                "id=" + id + '\n' +
                ", start_hour=" + start_hour + '\n' +
                ", start_minute=" + start_minute + '\n' +
                ", end_hour=" + end_hour + '\n' +
                ", end_minute=" + end_minute + '\n' +
                ", dayOfWeek=" + dayOfWeek + '\n' +
                ", professor='" + professor + '\n' +
                ", subject='" + subject + '\n' +
                ", lectureRoom='" + lectureRoom + '\n' +
                '}';
    }
}

