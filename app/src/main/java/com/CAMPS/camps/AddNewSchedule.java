package com.CAMPS.camps;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.CAMPS.camps.DataClass.Event;
import com.CAMPS.camps.DataClass.Schedule;

import java.util.Calendar;

public class AddNewSchedule extends Fragment {

    int year;
    int month;
    int day;
    int hour;
    int minute;
    boolean MOD = false;
    CalendarFragment calendarFragment;
    Boolean TIME_PICKED = false;
    InputMethodManager inputMethodManager;
    EditText titleText;
    EditText memoText;
    Schedule schedule;
    Event removeTarget = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.add_new_schedule, container, false);
        final TextView dateText = (TextView) view.findViewById(R.id.schedule_date);
        final TextView editDate = (TextView)view.findViewById(R.id.timeText);
        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.schedule_layout);
        titleText = (EditText) view.findViewById(R.id.title);
        memoText = (EditText) view.findViewById(R.id.memo);

        MainActivity main = (MainActivity)getActivity();
        main.BACK_STACK=true;
        inputMethodManager=main.keypad;

        main.centerToolbarTitle.setText("일정 추가");
        main.toolbar.setTitle("");
        main.invalidateOptionsMenu();

        main.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();

                main.FragmentRemove(AddNewSchedule.this);
            }
        });




        dateText.setText(String.format("%d년 %d월 %d일", year, month, day));
        if(MOD){
           titleText.setText(removeTarget.getTitle());
           memoText.setText(removeTarget.getMemo());
           editDate.setText(removeTarget.getHour() + "시 " + removeTarget.getMinute()+ "분");
        }
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
            }
        });
/*

        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                MainActivity main = (MainActivity) getActivity();
                main.showActionBar();
                main.FragmentRemove(AddNewSchedule.this);
            }
        });*/

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                TimePickerDialog timepick = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
                        editDate.setText(hourOfDay + "시 " + minute + "분");
                        hour = hourOfDay;
                        minute = minutes;
                        TIME_PICKED = true;
                    }
                }, hour, minute, false);
                timepick.show();
            }

        });
/*
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(memoText.getText().toString().length() == 0){ //no memo
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year,month-1,day,hour,minute);
                    schedule = new Schedule(titleText.getText().toString(),calendar,false);
                 //   System.out.println(schedule.toString());
                }
                else{ //memo
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year,month-1,day,hour,minute);
                    schedule = new Schedule(titleText.getText().toString(),calendar,memoText.getText().toString(),false);
                 //   System.out.println(schedule.toString());
                }
                if(titleText.getText().toString().length()==0 || TIME_PICKED == false){
                    setYetDialog();
                }
                else{
                    main.calendarFragment.schedules.add(schedule);
                    if(removeTarget != null) main.calendarFragment.schedules.remove(removeTarget);
                    main.showActionBar();
                    main.FragmentRemove(AddNewSchedule.this);

                }
            }
        });
*/
        return view;
    }
    private void hideKey() {
        inputMethodManager.hideSoftInputFromWindow(titleText.getWindowToken(),0);
        inputMethodManager.hideSoftInputFromWindow(memoText.getWindowToken(),0);

    }

    public static AddNewSchedule newInstance(){
        return new AddNewSchedule();
    }
    public void setYetDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("알림");
        dialog.setMessage("설정이 완료되지 않았습니다.");
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_assignment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MainActivity main = (MainActivity)getActivity();
        if (id == R.id.check_icon) {
            if(memoText.getText().toString().length() == 0){ //no memo
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month-1,day,hour,minute);
                schedule = new Schedule(titleText.getText().toString(),calendar,false);
                //   System.out.println(schedule.toString());
            }
            else{ //memo
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month-1,day,hour,minute);
                schedule = new Schedule(titleText.getText().toString(),calendar,memoText.getText().toString(),false);
                //   System.out.println(schedule.toString());
            }
            if(titleText.getText().toString().length()==0 ){
                setYetDialog();
            }
            else{
                if(TIME_PICKED == false){
                    schedule.setImportant(true);
                }
                main.calendarFragment.schedules.add(schedule);

                if(removeTarget != null) {

                    Schedule removeSchedule = (Schedule)removeTarget;
                    schedule.setImportant(removeSchedule.isImportant());
                    main.alarmDelete(removeTarget);
                    main.calendarFragment.schedules.remove(removeTarget);
                    main.calendarFragment.events.remove(removeTarget);
                }
                main.alarmSet(schedule);
                main.showActionBar();
                main.FragmentRemove(AddNewSchedule.this);

            }

        }

        return true;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
