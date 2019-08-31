package com.example.forstudent.BoxHelperClass;

import com.example.forstudent.BoxClass.Timetable_Model;
import com.example.forstudent.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class TimetableHelper {

    public ArrayList<Timetable_Model> timetable_models = new ArrayList<>();

    public TimetableHelper(long id, String classTitle, String profressor, ArrayList<String> classLocation, ArrayList<Calendar> starttime, ArrayList<Calendar> endtime){
        for(Calendar start: starttime) {
            int idx = starttime.indexOf(start);
            Timetable_Model timetable_model = new Timetable_Model(
                    id,
                    start.get(Calendar.HOUR),
                    start.get(Calendar.MINUTE),
                    endtime.get(idx).get(Calendar.HOUR),
                    endtime.get(idx).get(Calendar.MINUTE),
                    start.get(Calendar.DAY_OF_WEEK),
                    profressor,
                    classTitle,
                    classLocation.get(idx)
            );
            timetable_models.add(timetable_model);

        }
    }

    public static String getClassName(long id){
        Timetable_Model timetable_model = (Timetable_Model) MainActivity.getInstance().getTimetableBox().get(id);
        return timetable_model.getSubject();

    }
    public static String getProfessor(long id){
        Timetable_Model timetable_model = (Timetable_Model) MainActivity.getInstance().getTimetableBox().get(id);
        return timetable_model.getProfessor();

    }

    public static void putLecture(TimetableHelper timetable){
        for(Timetable_Model lecture: timetable.timetable_models) {
            MainActivity.getInstance().getTimetableBox().put(lecture);
        }
    }
    public static Timetable_Model getLecture(MainActivity main,long id){
        Timetable_Model timetable_model = (Timetable_Model) main.getTimetableBox().get(id);
        return timetable_model;
    }
}
