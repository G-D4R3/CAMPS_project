package com.example.forstudent;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
    Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay;
    int mSHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mSMinute = calendar.get(Calendar.MINUTE);
    int mEHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mEMinute = calendar.get(Calendar.MINUTE);
    public TestSub subject=null;


    Boolean DATE_PICKED = false;
    Boolean START_PICKED = false;
    Boolean END_PICKED = false;





    public static addNewExamSub newInstance(){
        return new addNewExamSub();
    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = (View)inflater.inflate(R.layout.add_new_exam_sub, container, false);
        TextView Title = (TextView)view.findViewById(R.id.fragTitle);
        final EditText mSubname = (EditText) view.findViewById(R.id.subName);
        TextView Date = (TextView)view.findViewById(R.id.Tdate);
        final TextView mDate = (TextView)view.findViewById(R.id.date2);
        TextView Start = (TextView)view.findViewById(R.id.Tstart);
        final TextView mStart = (TextView)view.findViewById(R.id.time2);
        final TextView End = (TextView)view.findViewById(R.id.Tend);
        final TextView mEnd = (TextView)view.findViewById(R.id.time2_1);
        TextView mCancle = (TextView)view.findViewById(R.id.cancle2);
        TextView mComplete = (TextView)view.findViewById(R.id.complete2);
        final EditText mRange = (EditText)view.findViewById(R.id.Range2);


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
                        DATE_PICKED=true;
                    }
                },mYear, mMonth, calendar.get(Calendar.DATE));
                calendar.set(mYear,mMonth,calendar.get(Calendar.DATE));
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
                        DATE_PICKED=true;
                    }
                },mYear, mMonth, calendar.get(Calendar.DATE));
                calendar.set(mYear,mMonth,calendar.get(Calendar.DATE));
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
                        START_PICKED=true;
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
                        END_PICKED=true;
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
                main.FragmentRemove(addNewExamSub.this);
            }
        });

        mComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mRange.getText().toString().length()==0){
                    subject = new TestSub(mSubname.getText().toString(),mYear,mMonth,mDay,mSHour,mSMinute,mEHour,mEMinute);
                }
                else{
                    subject = new TestSub(mSubname.getText().toString(),mYear,mMonth,mDay,mSHour,mSMinute,mEHour,mEMinute,mRange.getText().toString());
                }

                if(subject.getName()==null||DATE_PICKED==false||START_PICKED==false||END_PICKED==false){
                    setYetDialog();
                }
                else{
                    MainActivity main = (MainActivity)getActivity();
                    main.examFragment.addNewsub(subject);
                    main.FragmentRemove(addNewExamSub.this);
                }

            }
        });


        return view;

    }

    public void setYetDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("알림");
        dialog.setMessage("설정이 완료되지 않았습니다.");
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        dialog.show();
    }

}
