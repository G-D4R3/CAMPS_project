package com.example.forstudent.BoxHelperClass;

import com.example.forstudent.BoxClass.TestSub_Model;
import com.example.forstudent.DataClass.TestSub;
import com.example.forstudent.MainActivity;

import java.util.Calendar;

public class TestSubHelper {

    public TestSub_Model testSub_model;

    public TestSubHelper(long id, String name, Calendar date, int startHour, int startMinute, int endHour, int endMinute, String Range){
        testSub_model = new TestSub_Model(id, name, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), startHour, startMinute, endHour, endMinute, Range);
    }

    public static void setName(long id, String name){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getAssignmentBox().get(id);
        testSub_model.setName(name);
        MainActivity.getInstance().getTestSubBox().put(testSub_model);
    }

    public static void setYear(long id, int year){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getAssignmentBox().get(id);
        testSub_model.setYear(year);
        MainActivity.getInstance().getTestSubBox().put(testSub_model);
    }

    public static void setMonth(long id,int month){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getAssignmentBox().get(id);
        testSub_model.setMonth(month);
        MainActivity.getInstance().getTestSubBox().put(testSub_model);
    }

    public static void setDay(long id,int day){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getAssignmentBox().get(id);
        testSub_model.setDay(day);
        MainActivity.getInstance().getTestSubBox().put(testSub_model);
    }

    public static void setStartHour(long id,int sh){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getAssignmentBox().get(id);
        testSub_model.setStartHour(sh);
        MainActivity.getInstance().getTestSubBox().put(testSub_model);
    }

    public static void setStartMinute(long id, int sm){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getAssignmentBox().get(id);
        testSub_model.setStartMinute(sm);
        MainActivity.getInstance().getTestSubBox().put(testSub_model);
    }

    public static void setEndHour(long id, int eh){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getAssignmentBox().get(id);
        testSub_model.setEndHour(eh);
        MainActivity.getInstance().getTestSubBox().put(testSub_model);
    }

    public static void setEndMinute(long id, int em){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getAssignmentBox().get(id);
        testSub_model.setEndMinute(em);
        MainActivity.getInstance().getTestSubBox().put(testSub_model);
    }

    public static void setRange(long id, String range){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getAssignmentBox().get(id);
        testSub_model.setRange(range);
        MainActivity.getInstance().getTestSubBox().put(testSub_model);
    }

    public static void putTestSub(TestSubHelper helper){
        MainActivity main = MainActivity.getInstance();
        MainActivity.getInstance().getTestSubBox().put(helper.testSub_model);
    }

    public static String getName(long id){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getTestSubBox().get(id);
        return testSub_model.getName();
    }

    public static Calendar getDate(long id){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getTestSubBox().get(id);
        Calendar calendar = Calendar.getInstance();
        calendar.set(testSub_model.getYear(),testSub_model.getMonth(),testSub_model.getDay());
        return calendar;
    }

    public static int getStartHour(long id){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getTestSubBox().get(id);
        return testSub_model.getStartHour();
    }

    public static int getStartMinute(long id){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getTestSubBox().get(id);
        return testSub_model.getStartMinute();
    }

    public static int getEndHour(long id){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getTestSubBox().get(id);
        return testSub_model.getEndHour();
    }

    public static int getEndMinute(long id){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getTestSubBox().get(id);
        return testSub_model.getEndMinute();
    }

    public static String getRange(long id){
        TestSub_Model testSub_model = (TestSub_Model) MainActivity.getInstance().getTestSubBox().get(id);
        return testSub_model.getRange();
    }

    public static TestSub getTestSub(long id){
        TestSub testsub = new TestSub(getName(id), getDate(id), getStartHour(id), getStartMinute(id), getEndHour(id), getStartMinute(id), getRange(id));
        return testsub;
    }
}
