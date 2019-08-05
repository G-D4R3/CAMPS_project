package com.example.forstudent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.DataClass.Event;
import com.example.forstudent.DataClass.Schedule;
import com.example.forstudent.ListViewAdapter.CalendarListAdapter;
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
    MaterialCalendarView calendarView = null;
    ArrayList<Schedule> schedules;
    ArrayList<Event> events;
    CalendarListAdapter adapter;
    ListView listView=null;
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//test

        /*점 안찍히는데 왠지 모르겟음 수정요망 */
        main = (MainActivity)getActivity();
        schedules = main.schedules;
        view = (View)inflater.inflate(R.layout.fragment_calendar,container,false);
        assignmentList = main.assignment;
        events = new ArrayList<>();
        if(calendarView == null) {
            calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        }
        for(Schedule tmp:schedules){
            Event event = new Event(tmp.getTitle(),tmp.getDate().get(Calendar.HOUR),tmp.getDate().get(Calendar.MINUTE),tmp.getMemo());
            events.add(event);
        }
        for(Assignment tmp:assignmentList){
            Event event = new Event(tmp.getName(),tmp.getPeriod().get(Calendar.HOUR),tmp.getPeriod().get(Calendar.MINUTE),tmp.getMemo());
            events.add(event);
        }
        adapter = new CalendarListAdapter(events);
        listView = view.findViewById(R.id.calendarListView);
        listView.setAdapter(adapter);
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                System.out.println(date.toString());
            }
        });
        calendarView.setOnDateLongClickListener(new OnDateLongClickListener() {
            @Override
            public void onDateLongClick(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date) {
                System.out.println("Long" + date.toString());
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
    public CalendarFragment getInstance(){
        return this;
    }

    public void dotSchedule(){
        Collection<CalendarDay> scheduleDaysList = new ArrayList<>();
        CalendarDay calendarDay;

        for(Schedule tmp : schedules){
            System.out.println(tmp.getDate().toString());
            Calendar calendar = tmp.getDate();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendarDay = CalendarDay.from(year,month,day);
            scheduleDaysList.add(calendarDay);
        }

        calendarView.addDecorators(new EventDecorator(Color.BLUE,scheduleDaysList));
    }

    public MaterialCalendarView getCalendarView() {
        return calendarView;
    }
}