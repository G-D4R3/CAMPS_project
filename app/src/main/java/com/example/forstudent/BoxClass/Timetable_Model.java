package com.example.forstudent.BoxClass;


import java.sql.Time;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Timetable_Model {
    @Id(assignable = true)
    long id;

    int hour;
    int minute;
    int dayOfWeek;

    String professor;
    String lectureRoom;
    String subject;

}
