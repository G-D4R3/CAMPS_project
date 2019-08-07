package com.example.forstudent.BoxHelperClass;

import com.example.forstudent.BoxClass.Schedule_Model;
import com.example.forstudent.DataClass.Schedule;
import com.example.forstudent.MainActivity;

import java.util.Calendar;

public class ScheduleHelper {
    public Schedule schedule;
    public Schedule_Model schedule_model;
    public ScheduleHelper(String title, Calendar date, String memo ,Boolean important){
        schedule = new Schedule(title,date,memo,important);
    }
    public ScheduleHelper(long id,String title, Calendar date, String memo ,Boolean important){
        schedule_model = new Schedule_Model(id,title,date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH),date.get(Calendar.HOUR),date.get(Calendar.MINUTE),memo,important);
    }
    static String getTitle(long id){
        Schedule_Model schedule_model = (Schedule_Model) MainActivity.getInstance().getScheduleBox().get(id);
        return schedule_model.getTitle();
    }
    static String getMemo(long id){
        Schedule_Model schedule_model = (Schedule_Model) MainActivity.getInstance().getScheduleBox().get(id);
        return schedule_model.getMemo();
    }
    static Boolean getImportant(long id){
        Schedule_Model schedule_model = (Schedule_Model) MainActivity.getInstance().getScheduleBox().get(id);
        return schedule_model.getImportant();
    }
    static Calendar getDate(long id){
        int year;
        int month;
        int day;
        int hour;
        int minute;
        Calendar cal = Calendar.getInstance();
        Schedule_Model schedule_model = (Schedule_Model) MainActivity.getInstance().getScheduleBox().get(id);
        year = schedule_model.getYear();
        month = schedule_model.getMonth();
        day = schedule_model.getDay();
        hour = schedule_model.getHour();
        minute = schedule_model.getMinute();
        cal.set(year,month,day,hour,minute);
        return cal;
    }
    public static Schedule getSchedule(long id){
        Schedule schedule = new Schedule(ScheduleHelper.getTitle(id),ScheduleHelper.getDate(id),ScheduleHelper.getMemo(id),ScheduleHelper.getImportant(id));
        return schedule;
    }
    public static void putSchedule(ScheduleHelper helper){
        MainActivity main = MainActivity.getInstance();
        MainActivity.getInstance().getScheduleBox().put(helper.schedule_model);
    }
}
