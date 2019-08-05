package com.example.forstudent.DataClass;

public class Event {
    String title;
    int hour;
    int minute;
    String memo;

    public Event() {
    }

    public Event(String title, int hour, int minute, String memo) {
        this.title = title;
        this.hour = hour;
        this.minute = minute;
        this.memo = memo;
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
}
