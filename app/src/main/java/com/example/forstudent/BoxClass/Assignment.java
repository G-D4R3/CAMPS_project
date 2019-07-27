package com.example.forstudent.BoxClass;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Assignment {

    @Id(assignable = true)
    long id;

    String subject;

    //Calendar 대체용
    int deadLineYear;
    int deadLineMonth;
    int deadLineDay;

    //나중을 위해 시, 분도 만들어 놓음.
    int deadLineHour;
    int deadLineMinute;

    String memo;

    public Assignment(long id, String subject, int deadLineYear, int deadLineMonth, int deadLineDay, int deadLineHour, int deadLineMinute, String memo) {
        this.id = id;
        this.subject = subject;
        this.deadLineYear = deadLineYear;
        this.deadLineMonth = deadLineMonth;
        this.deadLineDay = deadLineDay;
        this.deadLineHour = deadLineHour;
        this.deadLineMinute = deadLineMinute;
        this.memo = memo;
    }


}
