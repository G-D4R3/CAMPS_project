package com.CAMPS.camps.BoxClass;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Grade_Model {

    @Id(assignable = true)
    long id;

    String name;
    int credit;
    double grade;

    public Grade_Model(long id,String name, int credit, double grade){
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCredit(int credit){
        this.credit = credit;
    }

    public void setGrade(double grade){
        this.grade = grade;
    }

    public String getName(){
        return name;
    }

    public int getCredit(){
        return credit;
    }

    public double getGrade(){
        return grade;
    }


}
