package com.example.forstudent;

import java.util.Calendar;

public class Assignment implements Comparable<Assignment>{
    private String Name;
    private Calendar Period;
    private String Sub; // 시간표 과목 class 생기면 대체할 것 & 생성자 오버로드
    String Memo;
    long sorting;


    public Assignment(String name, Calendar period){
        this.Name = name;
        this.Period = period;
        this.sorting = period.getTimeInMillis();
    }

    public Assignment(String name, Calendar period, String sub){
        this.Name = name;
        this.Period = period;
        this.Sub = sub;
    }

    public void setName(String name){
        this.Name = name;
    }

    public void setPeriod(Calendar per){
        this.Period = per;
    }

    public String getName(){
        return Name;
    }

    public Calendar getPeriod(){
        return Period;
    }


    @Override
    public int compareTo(Assignment a) {
        if(this.sorting<a.sorting){
            return -1;
        }
        else if(this.sorting==a.sorting){
        }
        return 1;
    }
}
