package com.example.forstudent;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.IndexType;
import io.objectbox.annotation.NameInDb;

@Entity
public class UserData {
    @Id
    long id;

    @NameInDb("USERNAME")
    @Index(type = IndexType.VALUE)
    String name;
    Date date;

    public UserData(long id,String name,Date date){
        this.id = id;
        this.name = name;
        this.date = date;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
