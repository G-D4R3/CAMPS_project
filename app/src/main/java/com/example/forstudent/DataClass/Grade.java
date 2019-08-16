package com.example.forstudent.DataClass;

public class Grade {
    public String subject;
    public double grade;
    public double credit;

    public Grade(){

    }

    public Grade(String sub, double grade, double credit){
        this.subject = sub;
        this.grade = grade;
        this.credit = credit;
    }

    public void setSubject(String sub){
        this.subject = sub;
    }

    public void setGrade(double grade){
        this.grade = grade;
    }

    public void setCredit(double credit){
        this.credit = credit;
    }

    public String getSubject(){
        return subject;
    }

    public double getCredit() {
        return credit;
    }

    public double getGrade() {
        return grade;
    }
}
