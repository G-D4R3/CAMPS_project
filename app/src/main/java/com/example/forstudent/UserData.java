package com.example.forstudent;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class UserData {
    @Id
    long id;

    String name;
    Date date;

    public UserData(long id,String name,Date date){
        this.id = id;
        this.name = name;
        this.date = date;
    }
}
