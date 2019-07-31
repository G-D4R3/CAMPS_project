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

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class CalendarFragment extends Fragment {

    private TextView Dday;
    private TextView today;
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//test
        CalendarDay calendarDay;

        MainActivity main = (MainActivity)getActivity();
        View view = (View)inflater.inflate(R.layout.fragment_calendar,container,false);
        calendarDay = CalendarDay.from(2019,8,14);
        Collection<CalendarDay> calendarsList= new ArrayList<>();
        calendarsList.add(calendarDay);
        calendarDay = calendarDay.today();
        calendarsList.add(calendarDay);
        MaterialCalendarView calendarView = (MaterialCalendarView)view.findViewById(R.id.calendarView);
        calendarView.addDecorator(new EventDecorator(Color.RED,calendarsList));

//or if you want to specify event label color
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
}