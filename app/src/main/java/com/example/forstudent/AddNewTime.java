package com.example.forstudent;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.tlaabs.timetableview.Schedule;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNewTime extends Fragment {

    /*** main ***/
    MainActivity main;
    InputMethodManager input;
    Schedule schedule;
    AddNewClass addNewClass;

    /*** tmp ***/
    String Lecture;
    String Professor;
    Calendar start_cal;
    Calendar end_cal;

    ArrayList<Calendar> startTime;
    ArrayList<Calendar> endTime;
    ArrayList<Calendar> dayOfWeek;

    String lectureRoom2;


    /*** view ***/
    Context context;
    EditText mLecture;
    EditText mProfessor;


    TextView startTime2;
    TextView endTime2;

    Spinner day2;



    EditText mLectureRoom2;


    Button add_button;



    /*** flag ***/
    Boolean MOD = false;

    /*** Storage ***/




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity main = (MainActivity)getActivity();
        startTime = new ArrayList<Calendar>();
        endTime = new ArrayList<Calendar>();

        /*** view load ***/
        View view = (View)inflater.inflate(R.layout.add_new_time, container, false);

        final TextView completeButton = (TextView) view.findViewById(R.id.complete_add_time);

        startTime2 = (TextView)view.findViewById(R.id.start_time2);
        endTime2 = (TextView)view.findViewById(R.id.end_time2);

        day2 = (Spinner)view.findViewById(R.id.day_spinner2);

        mLectureRoom2 = (EditText)view.findViewById(R.id.location_2);

        start_cal = Calendar.getInstance();
        end_cal = Calendar.getInstance();

        day2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                start_cal.set(Calendar.DAY_OF_WEEK, spin(day2));
                end_cal.set(Calendar.DAY_OF_WEEK, spin(day2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        startTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getContext(),listener, 10, 00, true);
                dialog.show();
            }

            private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    startTime2.setText(hourOfDay+" : " + minute);
                    start_cal.set(Calendar.HOUR, hourOfDay);
                    start_cal.set(Calendar.MINUTE, minute);
                }
            };
        });

        endTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getContext(),listener, 13, 30, true);
                dialog.show();
            }

            private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    endTime2.setText(hourOfDay+" : " + minute);
                    end_cal.set(Calendar.HOUR, hourOfDay);
                    end_cal.set(Calendar.MINUTE, minute);
                }
            };
        });

        completeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                startTime.add(start_cal);
                endTime.add(end_cal);

                lectureRoom2 = mLectureRoom2.getText().toString();
                main.FragmentRemove(AddNewTime.this);

                /* for (int i = 0; i < startTime.size(); i++){

                }

                ArrayList<Schedule> schedules = new ArrayList<Schedule>();
                Schedule schedule = new Schedule();
                schedule.setClassTitle("Data Structure"); // sets subject
                schedule.setClassPlace("IT-601"); // sets place
                schedule.setProfessorName("Won Kim"); // sets professor
                schedule.setStartTime(new Time(10, 0)); // sets the beginning of class time (hour,minute)
                schedule.setEndTime(new Time(13,30)); // sets the end of class time (hour,minute)
                schedules.add(schedule);*/

            }
        });






        /***** toolbar *****/
        main.BACK_STACK=true;
        main.centerToolbarTitle.setText("과목 추가");
       main.toolbar.setTitle("");
        main.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // hideKey();
                main.FragmentRemove(AddNewTime.this);
            }
        });
        return view;

    }

    /***** toolbar *****/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_add_assignment,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MainActivity main = (MainActivity)getActivity();
        if (id == R.id.check_icon) {
            //start_cal.set(Calendar.DAY_OF_WEEK,day2.getSelectedItemPosition());
            //end_cal.set(Calendar.DAY_OF_WEEK,day2.getSelectedItemPosition());
            System.out.println("AAA"+start_cal.get(Calendar.HOUR));
            addNewClass.startTimes.add(start_cal);
            addNewClass.endTimes.add(end_cal);
            addNewClass.lectureRooms.add(mLectureRoom2.getText().toString());
            main.FragmentRemove(AddNewTime.this);
        }

        return true;
    }

    public static AddNewTime newInstance(){
        return new AddNewTime();
    }

    /*** spinner ***/
    private int spin(Spinner s){
        String input;
        input = s.getSelectedItem().toString();

        if (input.equals("월")) return 2;
        else if (input.equals("화")) return 3;
        else if (input.equals("수")) return 4;
        else if (input.equals("목")) return 5;
        else if (input.equals("금")) return 6;
        else if (input.equals("토")) return 7;
        else return 0;
    }

    /*** getter ***/

    public String getLectureRoom2() {
        return lectureRoom2;
    }

    public ArrayList<Calendar> getStartTime() {
        return startTime;
    }

    public ArrayList<Calendar> getEndTime() {
        return endTime;
    }

    /***** toolbar *****/


}
