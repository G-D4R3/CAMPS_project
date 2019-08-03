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
        System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCc");
        CalendarDay calendarDay;
        Collection<CalendarDay> assignmentDaysList= new ArrayList<>();
        Collection<CalendarDay> scheduleDaysList = new ArrayList<>();
        main = (MainActivity)getActivity();
        schedules = main.schedules;
        view = (View)inflater.inflate(R.layout.fragment_calendar,container,false);
        assignmentList = main.assignment;
        for(Assignment tmp : assignmentList){
            System.out.println(tmp.getPeriod());
            Calendar calendar = tmp.getPeriod();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendarDay = CalendarDay.from(year,month,day);
            assignmentDaysList.add(calendarDay);
        }
        for(Schedule tmp : schedules){
            System.out.println(tmp.getDate());
            Calendar calendar = tmp.getDate();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendarDay = CalendarDay.from(year,month,day);
            scheduleDaysList.add(calendarDay);
        }


        /*
        calendarDay = CalendarDay.from(2019,8,14);

        calendarsList.add(calendarDay);
        calendarDay = calendarDay.today();
        calendarsList.add(calendarDay);*/
        calendarView = (MaterialCalendarView)view.findViewById(R.id.calendarView);
        calendarView.addDecorator(new EventDecorator(Color.RED,assignmentDaysList));
        calendarView.addDecorator(new EventDecorator(Color.BLUE,scheduleDaysList));
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


    /*    List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.three_icons));
        calendar.set(2019,8,14);
        events.add(new EventDay(calendar, R.drawable.three_icons));
        //EventDay event = new EventDay(calendar, R.drawable.ic_arrow_left);


        CalendarView calendarView = (CalendarView)view.findViewById(R.id.calendarView);

        calendarView.setEvents(events);*/
       //main.setContentView(R.layout.fragment_calendar);

       /* Box<UserData> userBox = boxStore.boxFor(UserData.class);
        MainActivity main = (MainActivity)getActivity();
        UserData testUser = new UserData(0,"TESTUSER",new Date(),999);
        main.getUserDataBox().put(testUser);
        long id = testUser.id;
        String name = testUser.name;

        System.out.println(id+" "+testUser.getName());
        testUser.setName("NEW TEST NAME");
        main.getUserDataBox().put(testUser);
        UserData getUser = (UserData) main.getUserDataBox().get(id);

        System.out.println( getUser.id+" "+getUser.getName());*/
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPp");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
    }
    public void dotSchedule(){

    }

    public MaterialCalendarView getCalendarView() {
        return calendarView;
    }
}