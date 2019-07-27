package com.example.forstudent.BoxClass;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Assignment_Model {

    @Id(assignable = true)
    long id;

    String subject;

    //Calendar 대체용
    int deadLineYear;
    int deadLineMonth;
    int deadLineDay;

    //나중을 위해 시, 분도 만들어 놓음.
    int deadLineHour;
    int deadLineMinute;


    String memo;

    public Assignment_Model(long id, String subject, int deadLineYear, int deadLineMonth, int deadLineDay, int deadLineHour, int deadLineMinute) {
        this.id = id;
        this.subject = subject;
        this.deadLineYear = deadLineYear;
        this.deadLineMonth = deadLineMonth;
        this.deadLineDay = deadLineDay;
        this.deadLineHour = deadLineHour;
        this.deadLineMinute = deadLineMinute;
    }

    public Assignment_Model(long id, String subject, int deadLineYear, int deadLineMonth, int deadLineDay, int deadLineHour, int deadLineMinute, String memo) {
        this.id = id;
        this.subject = subject;
        this.deadLineYear = deadLineYear;
        this.deadLineMonth = deadLineMonth;
        this.deadLineDay = deadLineDay;
        this.deadLineHour = deadLineHour;
        this.deadLineMinute = deadLineMinute;
        this.memo = memo;
    }


    public long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public int getDeadLineYear() {
        return deadLineYear;
    }

    public int getDeadLineMonth() {
        return deadLineMonth;
    }

    public int getDeadLineDay() {
        return deadLineDay;
    }

    public int getDeadLineHour() {
        return deadLineHour;
    }

    public int getDeadLineMinute() {
        return deadLineMinute;
    }

    public String getMemo() {
        return memo;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDeadLineYear(int deadLineYear) {
        this.deadLineYear = deadLineYear;
    }

    public void setDeadLineMonth(int deadLineMonth) {
        this.deadLineMonth = deadLineMonth;
    }

    public void setDeadLineDay(int deadLineDay) {
        this.deadLineDay = deadLineDay;
    }

    public void setDeadLineHour(int deadLineHour) {
        this.deadLineHour = deadLineHour;
    }

    public void setDeadLineMinute(int deadLineMinute) {
        this.deadLineMinute = deadLineMinute;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
