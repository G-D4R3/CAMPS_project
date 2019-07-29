package com.example.forstudent;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment {

    private TextView Dday;
    private TextView today;
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//test
        MainActivity main = (MainActivity)getActivity();

//or if you want to specify event label color
        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.ic_arrow_left));
        calendar.set(2019,8,14);
        events.add(new EventDay(calendar, R.drawable.ic_launcher_background));
        //EventDay event = new EventDay(calendar, R.drawable.ic_arrow_left);

        View view = (View)inflater.inflate(R.layout.fragment_calendar,container,false);
        CalendarView calendarView = main.getCalendarView();

        calendarView.setEvents(events);
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