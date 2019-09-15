package com.CAMPS.camps;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    String lectureRoom;


    /*** view ***/
    Context context;

    TextView mStartTime;
    TextView mEndTime;
    Spinner mDay;
    EditText mLectureRoom;



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

        /***** view load *****/
        View view = (View)inflater.inflate(R.layout.add_new_time, container, false);
        mStartTime = (TextView)view.findViewById(R.id.start_time2);
        mEndTime = (TextView)view.findViewById(R.id.end_time2);
        mDay = (Spinner)view.findViewById(R.id.day_spinner2);
        mLectureRoom = (EditText)view.findViewById(R.id.location_2);
        start_cal = Calendar.getInstance();
        end_cal = Calendar.getInstance();


        /***** toolbar *****/
        main.BACK_STACK=true;
        main.centerToolbarTitle.setText("과목 추가");
        main.toolbar.setTitle("");
        main.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hideKey();
                main.internalFragmentRemove(AddNewTime.this);
            }
        });



        mDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                start_cal.set(Calendar.DAY_OF_WEEK, spin(mDay));
                end_cal.set(Calendar.DAY_OF_WEEK, spin(mDay));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getContext(),listener, 10, 00, true);
                dialog.show();
            }

            private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mStartTime.setText(String.format("%02d:%02d", hourOfDay,minute));
                    start_cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    start_cal.set(Calendar.MINUTE, minute);
                }
            };
        });

        mEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getContext(),listener, 13, 30, true);
                dialog.show();
            }

            private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mEndTime.setText(String.format("%02d:%02d", hourOfDay,minute));
                    end_cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    end_cal.set(Calendar.MINUTE, minute);
                }
            };
        });





        return view;

    }

    public boolean checkOverlap(){
        MainActivity main = (MainActivity)getActivity();
        TimetableFragment timetableFragment = main.timetableFragment;
        ArrayList<Schedule> stickers = timetableFragment.timetable.getAllSchedulesInStickers();
        int tar_start_hour = start_cal.get(Calendar.HOUR_OF_DAY);
        int tar_start_minute = start_cal.get(Calendar.MINUTE);
        int tar_start = tar_start_hour*60+tar_start_minute;
        int tar_end_hour = end_cal.get(Calendar.HOUR_OF_DAY);
        int tar_end_minute = end_cal.get(Calendar.MINUTE);
        int tar_end = tar_end_hour*60+tar_end_minute;
        int tar_day_of_week = start_cal.get(Calendar.DAY_OF_WEEK)-2;
        System.out.println(tar_end_hour+" "+ tar_end_minute);
        for(Schedule tmp:stickers){
            int ori_start_hour = tmp.getStartTime().getHour();
            int ori_start_minute = tmp.getStartTime().getMinute();
            int ori_start = ori_start_hour*60+ori_start_minute;
            int ori_end_hour = tmp.getEndTime().getHour();
            int ori_end_minute = tmp.getEndTime().getMinute();
            int ori_end = ori_end_hour*60+ori_end_minute;
            int ori_day_of_week = tmp.getDay();
            System.out.println(ori_day_of_week);
            if(tar_day_of_week == ori_day_of_week){
                System.out.println(tar_start+":"+ori_start+"/"+tar_end+":"+ori_end);
                if((ori_end > tar_start && ori_start < tar_start) || (ori_end > tar_end && ori_start < tar_end) || (ori_end == tar_end) || (ori_start == tar_start)){
                    return true;
                }
            }

        }
        return false;
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
            if(checkOverlap()){
                try{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setTitle("시간표 중복입니다.");
                    dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
            else {
                addNewClass.startTimes.add(start_cal);
                addNewClass.endTimes.add(end_cal);
                addNewClass.lectureRooms.add(mLectureRoom.getText().toString());
                addNewClass.adapter.notifyDataSetChanged();
                main.FragmentRemove(AddNewTime.this);
            }

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

    public String getLectureRoom() {
        return lectureRoom;
    }

    public ArrayList<Calendar> getStartTime() {
        return startTime;
    }

    public ArrayList<Calendar> getEndTime() {
        return endTime;
    }

    /***** toolbar *****/


}
