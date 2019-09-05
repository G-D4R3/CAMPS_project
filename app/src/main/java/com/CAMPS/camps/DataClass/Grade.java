package com.CAMPS.camps.DataClass;

public class Grade {
    public String subject;
    public int credit;
    public double grade;

    public Grade(){

    }

    public Grade(String sub, int credit, double grade){
        this.subject = sub;
        this.credit = credit;
        this.grade = grade;
    }

    public void setSubject(String sub){
        this.subject = sub;
    }

    public void setGrade(double grade){
        this.grade = grade;
    }

    public void setCredit(int credit){
        this.credit = credit;
    }

    public String getSubject(){
        return subject;
    }

    public int getCredit() {
        return credit;
    }

    public double getGrade() {
        return grade;
    }
}
