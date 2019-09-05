package com.CAMPS.camps.BoxHelperClass;

import com.CAMPS.camps.BoxClass.Grade_Model;
import com.CAMPS.camps.DataClass.Grade;
import com.CAMPS.camps.MainActivity;

public class GradeHelper {

    public Grade_Model grade_model;

    public GradeHelper(long id,String name, int credit, double grade){
        grade_model = new Grade_Model(id, name, credit, grade);
    }


    public static void setname(long id, String name){
        Grade_Model grade_model = (Grade_Model) MainActivity.getInstance().getGradeBox().get(id);
        grade_model.setName(name);
        MainActivity.getInstance().getGradeBox().put(grade_model);
    }

    public static void setcredit(long id, int credit){
        Grade_Model grade_model = (Grade_Model) MainActivity.getInstance().getGradeBox().get(id);
        grade_model.setCredit(credit);
        MainActivity.getInstance().getGradeBox().put(grade_model);
    }

    public static void setgrade(long id, double grade){
        Grade_Model grade_model = (Grade_Model) MainActivity.getInstance().getGradeBox().get(id);
        grade_model.setGrade(grade);
        MainActivity.getInstance().getGradeBox().put(grade_model);
    }

    public static void putGrade(GradeHelper gradeHelper){
        MainActivity main = MainActivity.getInstance();
        MainActivity.getInstance().getGradeBox().put(gradeHelper.grade_model);
    }

    public static String getname(long id){
        Grade_Model grade_model = (Grade_Model) MainActivity.getInstance().getGradeBox().get(id);
        return grade_model.getName();
    }

    public static int getcredit(long id){
        Grade_Model grade_model = (Grade_Model) MainActivity.getInstance().getGradeBox().get(id);
        return grade_model.getCredit();
    }

    public static double getgrade(long id){
        Grade_Model grade_model = (Grade_Model) MainActivity.getInstance().getGradeBox().get(id);
        return grade_model.getGrade();
    }

    public static Grade getGrade(long id) {
        Grade grade = new Grade(getname(id),getcredit(id),getgrade(id));
        return grade;
    }
}
