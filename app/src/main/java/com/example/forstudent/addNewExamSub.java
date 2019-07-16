package com.example.forstudent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Calendar;

public class addNewExamSub extends Fragment {
    public String title;
    private Editable Subname;
    Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay;
    int mSHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mSMinute = calendar.get(Calendar.MINUTE);
    int mEHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mEMinute = calendar.get(Calendar.MINUTE);



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = (View)inflater.inflate(R.layout.add_new_exam_sub, container, false);
        TextView Title = (TextView)view.findViewById(R.id.fragTitle);
        EditText mSubname = (EditText) view.findViewById(R.id.subName);
        TextView Date = (TextView)view.findViewById(R.id.Tdate);
        final TextView mDate = (TextView)view.findViewById(R.id.date2);
        TextView Start = (TextView)view.findViewById(R.id.Tstart);
        final TextView mStart = (TextView)view.findViewById(R.id.time2);
        TextView End = (TextView)view.findViewById(R.id.Tend);
        final TextView mEnd = (TextView)view.findViewById(R.id.time2_1);
        TextView mCancle = (TextView)view.findViewById(R.id.cancle2);
        TextView mComplete = (TextView)view.findViewById(R.id.complete2);


        mDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datepick = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText((month+1)+"월 "+dayOfMonth+"일");
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                    }
                },mYear, mMonth, calendar.get(Calendar.DATE));
                datepick.show();
            }
        });

        Date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datepick = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText((month+1)+"월 "+dayOfMonth+"일");
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                    }
                },mYear, mMonth, calendar.get(Calendar.DATE));
                datepick.show();
            }
        });

        mStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TimePickerDialog timepick = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mStart.setText(hourOfDay+"시 "+minute+"분");
                        mSHour = hourOfDay;
                        mSMinute = minute;
                    }
                },mSHour,mSMinute,true);
                timepick.show();
            }
        });

        mEnd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TimePickerDialog timepick = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mEnd.setText(hourOfDay+"시 "+minute+"분");
                        mEHour = hourOfDay;
                        mEMinute = minute;
                    }
                },mEHour,mEMinute,true);
                timepick.show();
            }
        });

        End.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TimePickerDialog timepick = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mEnd.setText(hourOfDay+"시 "+minute+"분");
                        mEHour = hourOfDay;
                        mEMinute = minute;
                    }
                },mEHour,mEMinute,true);
                timepick.show();
            }
        });


        mCancle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity)getActivity();
                main.Fragmentchange(2);
            }
        });




        return view;

    }

}
