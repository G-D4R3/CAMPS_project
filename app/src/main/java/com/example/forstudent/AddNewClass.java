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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.DataClass.Timetable;
import com.github.tlaabs.timetableview.Schedule;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNewClass extends Fragment {

    /*** main ***/
    MainActivity main;
    InputMethodManager input;
    Schedule schedule;

    /*** tmp ***/
    String Lecture;
    String Professor;
    Calendar start_cal;
    Calendar end_cal;

    ArrayList<Calendar> startTime;
    ArrayList<Calendar> endTime;
    ArrayList<Calendar> dayOfWeek;

    String lectureRoom1;
    String LectureRoom2;
    String LectureRoom3;

    /*** view ***/
    Context context;
    EditText mLecture;
    EditText mProfessor;

    TextView startTime1;
    TextView endTime1;

    TimePicker time1;
    TimePicker time2;
    TimePicker time3;
    Spinner day1;
    Spinner day2;
    Spinner day3;

    EditText mLectureRoom1;
    EditText mLectureRoom2;
    EditText mLectureRoom3;

    /*** flag ***/
    Boolean MOD = false;

    /*** Storage ***/




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity main = (MainActivity)getActivity();

        startTime = new ArrayList<Calendar>();

        /*** view load ***/
        View view = (View)inflater.inflate(R.layout.add_new_class, container, false);
        mLecture = (EditText)view.findViewById(R.id.className);
        mProfessor = (EditText)view.findViewById(R.id.professor);
        final TextView completeButton = (TextView) view.findViewById(R.id.complete_add_timetable);

        //time1 = (TimePicker)view.findViewById(R.id.time_spinner1);
        startTime1 = (TextView)view.findViewById(R.id.start_time1);
        endTime1 = (TextView)view.findViewById(R.id.end_time1);

        day1 = (Spinner)view.findViewById(R.id.day_spinner1);

        mLectureRoom1 = (EditText)view.findViewById(R.id.location_1);

        start_cal = Calendar.getInstance();
        end_cal = Calendar.getInstance();

        day1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                start_cal.set(Calendar.DAY_OF_WEEK, spin(day1));
                end_cal.set(Calendar.DAY_OF_WEEK, spin(day1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        startTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getContext(),listener, 10, 00, true);
                dialog.show();
            }

            private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    startTime1.setText(hourOfDay+" : " + minute);
                    start_cal.set(Calendar.HOUR, hourOfDay);
                    start_cal.set(Calendar.MINUTE, minute);
                }
            };
        });

        endTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getContext(),listener, 13, 30, true);
                dialog.show();
            }

            private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    endTime1.setText(hourOfDay+" : " + minute);
                    end_cal.set(Calendar.HOUR, hourOfDay);
                    end_cal.set(Calendar.MINUTE, minute);
                }
            };
        });




        /*time1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
            }
        });*/

        completeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Lecture = mLecture.getText().toString();
                Professor = mProfessor.getText().toString();
                startTime.add(start_cal);
                endTime.add(end_cal);
                System.out.println(startTime.get(0));
                System.out.println(endTime.get(0));
                lectureRoom1 = mLectureRoom1.getText().toString();

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
                main.FragmentRemove(AddNewClass.this);
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

        }

        return true;
    }

    public static AddNewClass newInstance(){
        return new AddNewClass();
    }

    /*** datainput ***/
    private void inputDataProcessing(){
        schedule.setClassTitle(mLecture.getText().toString());
        schedule.setProfessorName(mProfessor.getText().toString());

    }

    /*** spinner ***/
    private int spin(Spinner s){
        String input;
        input = s.getSelectedItem().toString();

        if (input.equals("월")) return 1;
        else if (input.equals("화")) return 2;
        else if (input.equals("수")) return 3;
        else if (input.equals("목")) return 4;
        else if (input.equals("금")) return 5;
        else if (input.equals("토")) return 6;
        else return 0;
    }

}
