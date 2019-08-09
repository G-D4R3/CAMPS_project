package com.example.forstudent;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import com.example.forstudent.DataClass.Event;
import com.example.forstudent.DataClass.Schedule;

import java.util.Calendar;

public class AddNewSchedule extends Fragment {

    int year;
    int month;
    int day;
    int hour;
    int minute;
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
        final TextView cancleButton = (TextView) view.findViewById(R.id.cancle_add_schedule);
        final TextView completeButton = (TextView) view.findViewById(R.id.complete_add_schedule);
        final TextView editDate = (TextView)view.findViewById(R.id.timeText);
        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.schedule_layout);
        titleText = (EditText) view.findViewById(R.id.title);
        memoText = (EditText) view.findViewById(R.id.memo);

        MainActivity main = (MainActivity)getActivity();
        inputMethodManager=main.keypad;
        dateText.setText(String.format("%d년 %d월 %d일", year, month, day));
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
            }
        });


        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                MainActivity main = (MainActivity) getActivity();
                main.showActionBar();
                main.FragmentRemove(AddNewSchedule.this);
            }
        });

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                TimePickerDialog timepick = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editDate.setText(hourOfDay + "시 " + minute + "분");
                        hour = hourOfDay;
                        minute = minute;
                        TIME_PICKED = true;
                    }
                }, hour, minute, true);
                timepick.show();
            }

        });

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(memoText.getText().toString().length() == 0){ //no memo
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year,month,day,hour,minute);
                    schedule = new Schedule(titleText.getText().toString(),calendar,false);
                 //   System.out.println(schedule.toString());
                }
                else{ //memo
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year,month,day,hour,minute);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity main = (MainActivity) getActivity();
        main.hideActionBar();
    }
}
