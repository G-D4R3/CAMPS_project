package com.CAMPS.camps.DataClass;

public class Event {
    String title;
    int hour;
    int minute;
    String memo;
    int type;
    // TYPE :
    // 1 Assignment
    // 2 Schedule
    // 3 Test

    public Event() {
    }

    public Event(String title, int hour, int minute, String memo,int type) {
        this.title = title;
        this.hour = hour;
        this.minute = minute;
        this.memo = memo;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
