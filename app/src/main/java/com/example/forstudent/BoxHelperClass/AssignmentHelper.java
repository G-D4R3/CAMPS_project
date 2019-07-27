package com.example.forstudent.BoxHelperClass;

import com.example.forstudent.BoxClass.Assignment;
import com.example.forstudent.MainActivity;

import java.util.Calendar;

public class  AssignmentHelper{

    public static void setSubject(long id,String subject){
        Assignment assignment = (Assignment) MainActivity.getInstance().getAssignmentBox().get(id);
        assignment.setSubject(subject);
        MainActivity.getInstance().getAssignmentBox().put(assignment);
    }
    public static void setMemo(long id,String memo){
        Assignment assignment = (Assignment) MainActivity.getInstance().getAssignmentBox().get(id);
        assignment.setMemo(memo);
        MainActivity.getInstance().getAssignmentBox().put(assignment);
    }
    public static void setPeriod(long id,Calendar period){
        int year = period.get(Calendar.YEAR);
        int month = period.get(Calendar.MONTH);
        int day = period.get(Calendar.DATE);
        int hour = period.get(Calendar.HOUR);
        int minute = period.get(Calendar.MINUTE);

        Assignment assignment = (Assignment) MainActivity.getInstance().getAssignmentBox().get(id);
        assignment.setDeadLineYear(year);
        assignment.setDeadLineMonth(month);
        assignment.setDeadLineDay(day);
        assignment.setDeadLineHour(hour);
        assignment.setDeadLineMinute(minute);
        MainActivity.getInstance().getAssignmentBox().put(assignment);
    }
    public static void getSubject(long id,String subject){
        Assignment assignment = (Assignment) MainActivity.getInstance().getAssignmentBox().get(id);
        assignment.setSubject(subject);
        MainActivity.getInstance().getAssignmentBox().put(assignment);
    }
    public static void putAssignment(Assignment assignment){
        MainActivity.getInstance().getAssignmentBox().put(assignment);
    }
    public static String getSubject(long id){
        Assignment assignment = (Assignment) MainActivity.getInstance().getAssignmentBox().get(id);
        return assignment.getSubject();

    }
    public static String getMemo(long id){
        Assignment assignment = (Assignment) MainActivity.getInstance().getAssignmentBox().get(id);
        return assignment.getMemo();

    }
    public static Calendar getPeriod(long id){
        int year;
        int month;
        int day;
        int hour;
        int minute;
        Calendar period = Calendar.getInstance();
        Assignment assignment = (Assignment) MainActivity.getInstance().getAssignmentBox().get(id);
        period.set(assignment.getDeadLineYear(),assignment.getDeadLineMonth(),assignment.getDeadLineDay(),assignment.getDeadLineHour(),assignment.getDeadLineMinute());
        return period;
    }

    public static long getSorting(long id) {
        Calendar period = Calendar.getInstance();
        Assignment assignment = (Assignment) MainActivity.getInstance().getAssignmentBox().get(id);
        period.set(assignment.getDeadLineYear(),assignment.getDeadLineMonth(),assignment.getDeadLineDay(),assignment.getDeadLineHour(),assignment.getDeadLineMinute());
        return period.getTimeInMillis();
    }

}
