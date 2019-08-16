package com.example.forstudent;

import com.example.forstudent.DataClass.Grade;

import java.util.ArrayList;

public class CalcGrade{

    ArrayList<Grade> data = new ArrayList<>();
    int size;
    int totalCredit = 0;
    double grade=0;

    public CalcGrade(ArrayList<Grade> grade){
        this.data = grade;
        size = grade.size();
    }

    public void Calculate(){
        for(Grade tmp : data){
            totalCredit += tmp.credit;
            grade += (tmp.grade * tmp.credit);
        }
        grade = grade / totalCredit;
    }

    public double getGrade(){
        return grade;
    }

    public double getCredit(){
        return totalCredit;
    }

}
