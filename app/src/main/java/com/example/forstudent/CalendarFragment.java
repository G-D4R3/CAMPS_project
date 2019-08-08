package com.example.forstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.forstudent.BoxHelperClass.ScheduleHelper;
import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.DataClass.Event;
import com.example.forstudent.DataClass.Schedule;
import com.example.forstudent.DataClass.TestSub;
import com.example.forstudent.ListViewAdapter.CalendarListAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class CalendarFragment extends Fragment{
    ArrayList<Assignment> assignmentList;
    Collection<CalendarDay> assignmentDaysList= new ArrayList<>();

    private TextView Dday;
    private TextView today;
    MainActivity main;
    View view;
    TextView mTitle;
    MaterialCalendarView calendarView;
    ArrayList<Schedule> schedules = new ArrayList<>();
    ArrayList<TestSub> testList = new ArrayList<>();
    ArrayList<Event> events = new ArrayList<>();
    ArrayList<Event> dayEvent;
    ArrayList<Event> scheduleDayEvent;
    CalendarListAdapter upperAdapter;
    CalendarListAdapter lowerAdapter;
    ListView upperListView;
    ListView lowerListView;
    int colorAccent;

    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        colorAccent = getResources().getColor(R.color.colorAccent);
        //ArrayList<Event> events = new ArrayList<>();
        main = (MainActivity)getActivity();
        schedules = main.schedules;
        view = (View)inflater.inflate(R.layout.fragment_calendar,container,false);
        assignmentList = main.assignment;
        testList = main.testSub;
        upperListView = view.findViewById(R.id.upperCalendarListView);
        lowerListView = view.findViewById(R.id.lowerCalendarListView);
        calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        main.setActionBarTitle("캘린더");
        for(Schedule tmp:schedules){
            //Event event = new Event(tmp.getTitle(),tmp.getDate().get(Calendar.HOUR),tmp.getDate().get(Calendar.MINUTE),tmp.getMemo(),2);
            tmp.setHour(tmp.getDate().get(Calendar.HOUR));
            tmp.setMinute(tmp.getDate().get(Calendar.MINUTE));
            tmp.setType(2);
            System.out.println(tmp.toString());
            if(!events.contains(tmp)) events.add(tmp);
        }
        for(Assignment tmp:assignmentList){
            //Event event = new Event(tmp.getName(),tmp.getPeriod().get(Calendar.HOUR),tmp.getPeriod().get(Calendar.MINUTE),tmp.getMemo(),1);
            tmp.setTitle(tmp.getName());
            tmp.setHour(tmp.getPeriod().get(Calendar.HOUR));
            tmp.setMinute(tmp.getPeriod().get(Calendar.MINUTE));
            tmp.setType(1);
            if(!events.contains(tmp)) events.add(tmp);
        }
        for(TestSub tmp:testList){
            //Event event = new Event(tmp.getName(),tmp.getStartHour(),tmp.getStartMinute(),tmp.getRange(),3);
            tmp.setTitle(tmp.getName());
            tmp.setHour(tmp.getStartHour());
            tmp.setMinute(tmp.getStartMinute());
            tmp.setMemo(tmp.getRange());
            tmp.setType(3);
            if(!events.contains(tmp)) events.add(tmp);
        }

        dotAssignment();
        dotSchedule();
        dotTest();
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                //System.out.println(date.toString());
                dayEvent = new ArrayList<>();
                scheduleDayEvent = new ArrayList<>();
                for(Event tmp:events){

                    switch(tmp.getType()){
                        case 1:
                            Assignment assignment = (Assignment)tmp;

                            if(assignment.getPeriod().get(Calendar.YEAR)!=date.getYear()) break;
                            if(assignment.getPeriod().get(Calendar.MONTH)+1!=date.getMonth()) break;
                            if(assignment.getPeriod().get(Calendar.DAY_OF_MONTH)!=date.getDay())break;
                            dayEvent.add(tmp);

                            break;
                        case 2:
                            Schedule schedule = (Schedule)tmp;
                            if(schedule.getDate().get(Calendar.YEAR)!=date.getYear()) break;
                            if(schedule.getDate().get(Calendar.MONTH)!=date.getMonth()) break;
                            if(schedule.getDate().get(Calendar.DAY_OF_MONTH)!=date.getDay())break;
                            scheduleDayEvent.add(tmp);
                            //System.out.println(tmp.toString());
                            break;
                        case 3:
                            TestSub testSub = (TestSub)tmp;
                            if(testSub.getTestDate().get(Calendar.YEAR)!=date.getYear())break;
                            if(testSub.getTestDate().get(Calendar.MONTH)+1!=date.getMonth()) break;
                            if(testSub.getTestDate().get(Calendar.DAY_OF_MONTH)!=date.getDay())break;
                            dayEvent.add(tmp);
                            //System.out.println(tmp.toString());
                            break;
                    }
                    upperAdapter = new CalendarListAdapter(dayEvent);
                    lowerAdapter = new CalendarListAdapter(scheduleDayEvent);
                    //listView = view.findViewById(R.id.upperCalendarListView);
                    upperListView.setAdapter(upperAdapter);
                    lowerListView.setAdapter(lowerAdapter);
                }

            }
        });

        lowerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int pos = position;
                try{
                    String name = lowerAdapter.eventList.get(position).getTitle();
                    String[] menu = {"수정", "삭제"};


                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setTitle(name);
                    dialog.setItems(menu, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    dialog.dismiss();
                                    modifySchedule(lowerAdapter.eventList.get(pos),lowerAdapter);
                                    break;
                                case 1:
                                    AlertDialog.Builder remove = new AlertDialog.Builder(getContext());
                                    remove.setTitle("삭제");
                                    remove.setMessage("일정을 삭제 합니다.");
                                    remove.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            removeSchedule(lowerAdapter.eventList.get(pos),lowerAdapter);
                                            //mTitle.setText(name);
                                            dialog.dismiss();
                                        }
                                    });
                                    remove.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();

                                        }
                                    });
                                    remove.show();
                                    break;

                            }
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }

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

    public CalendarFragment getInstance(){
        return this;
    }
    public void dotTest(){
        Collection<CalendarDay> testDaysList = new ArrayList<>();
        CalendarDay calendarDay;

        for(TestSub tmp : testList){
            //System.out.println(tmp.getPeriod());
            Calendar calendar = tmp.getTestDate();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendarDay = CalendarDay.from(year,month,day);
            testDaysList.add(calendarDay);
        }
        calendarView.addDecorators(new EventDecorator(colorAccent,testDaysList));
    }
    public void dotAssignment(){

        CalendarDay calendarDay;

        for(Assignment tmp : assignmentList){
            System.out.println(tmp.getPeriod());
            Calendar calendar = tmp.getPeriod();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendarDay = CalendarDay.from(year,month,day);
            assignmentDaysList.add(calendarDay);
        }
        calendarView.addDecorators(new EventDecorator(colorAccent,assignmentDaysList));
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

        calendarView.addDecorators(new EventDecorator(colorAccent,scheduleDaysList));
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity main = (MainActivity)getActivity();
        main.getScheduleBox().removeAll();
        for(int i=0; i<schedules.size(); i++){
            ScheduleHelper helper = new ScheduleHelper((long)i+1, schedules.get(i).getTitle(), schedules.get(i).getDate(), schedules.get(i).getMemo(),false);
            ScheduleHelper.putSchedule(helper);
        }
    }
    public void addTransparent(){
        TransparentFragment addFragment = TransparentFragment.newInstance();
        MainActivity main = (MainActivity)getActivity();
        main.FragmentAdd(addFragment);
    }
    public void removeSchedule(Event event,CalendarListAdapter adapter){
        schedules.remove((Schedule)event);
        scheduleDayEvent.remove(event);
        events.remove(event);
        addTransparent();
        adapter.notifyDataSetChanged();
    }
    public void modifySchedule(Event event,CalendarListAdapter adapter){
        MainActivity main = (MainActivity)getActivity();
        AddNewSchedule addFragment = AddNewSchedule.newInstance();
        Schedule schedule = (Schedule)event;
        addFragment.year = schedule.getDate().get(Calendar.YEAR);
        addFragment.month = schedule.getDate().get(Calendar.MONTH);
        addFragment.day = schedule.getDate().get(Calendar.DAY_OF_MONTH);
        addFragment.removeTarget = event;
        main.FragmentAdd(addFragment);
        adapter.notifyDataSetChanged();
        addTransparent();

    }
    public MaterialCalendarView getCalendarView() {
        return calendarView;
    }



}