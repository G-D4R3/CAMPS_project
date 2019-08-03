package com.example.forstudent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.DataClass.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class CalendarFragment extends Fragment{
    ArrayList<Assignment> assignmentList;
    private TextView Dday;
    private TextView today;
    MainActivity main;
    View view;
    MaterialCalendarView calendarView;
    ArrayList<Schedule> schedules;
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//test

        main = (MainActivity)getActivity();
        schedules = main.schedules;
        view = (View)inflater.inflate(R.layout.fragment_calendar,container,false);
        assignmentList = main.assignment;


        /*
        calendarDay = CalendarDay.from(2019,8,14);

        calendarsList.add(calendarDay);
        calendarDay = calendarDay.today();
        calendarsList.add(calendarDay);*/
        calendarView = (MaterialCalendarView)view.findViewById(R.id.calendarView);


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                System.out.println(date.toString());
            }
        });
        calendarView.setOnDateLongClickListener(new OnDateLongClickListener() {
            @Override
            public void onDateLongClick(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date) {
                //System.out.println("Long" + date.toString());
                AddNewSchedule addFragment = AddNewSchedule.newInstance();
                MainActivity main = (MainActivity)getActivity();
                addFragment.year = date.getYear();
                addFragment.month = date.getMonth();
                addFragment.day = date.getDay();

                main.FragmentAdd(addFragment);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    public void dotAssignment(){
        CalendarDay calendarDay;
        Collection<CalendarDay> assignmentDaysList= new ArrayList<>();

        for(Assignment tmp : assignmentList){
            System.out.println(tmp.getPeriod());
            Calendar calendar = tmp.getPeriod();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendarDay = CalendarDay.from(year,month,day);
            assignmentDaysList.add(calendarDay);
        }
        calendarView.addDecorator(new EventDecorator(Color.RED,assignmentDaysList));
    }

    public void dotSchedule(){
        Collection<CalendarDay> scheduleDaysList = new ArrayList<>();
        CalendarDay calendarDay;

        for(Schedule tmp : schedules){
            System.out.println(tmp.getDate());
            Calendar calendar = tmp.getDate();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendarDay = CalendarDay.from(year,month,day);
            scheduleDaysList.add(calendarDay);
        }

        calendarView.addDecorator(new EventDecorator(Color.BLUE,scheduleDaysList));
    }

    public MaterialCalendarView getCalendarView() {
        return calendarView;
    }
}