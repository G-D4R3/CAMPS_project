package com.example.forstudent;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    private TextView Dday;
    private TextView today;

    //오늘 날짜
    private int tyear;
    private int tmonth;
    private int tday;

    //dday 설정 날짜
    private int dyear;
    private int dmonth;
    private int dday;

    private long Today;
    private long setday;
    private long left;
    private int result=0;


    final Calendar tcalendar = Calendar.getInstance();
    final Calendar dcalendar = Calendar.getInstance();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_home,container,false);
        Dday = (TextView)view.findViewById(R.id.Dday);
        today = (TextView)view.findViewById(R.id.Today);

        //현재 날짜
        tyear = tcalendar.get(Calendar.YEAR);
        tmonth = tcalendar.get(Calendar.MONTH);
        tday = tcalendar.get(Calendar.DAY_OF_MONTH);


        dcalendar.set(dyear, dmonth-1, dday);

        //텍스트 터치 시 날짜 변경 가능
        Dday.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DatePickerDialog datepick = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast toast = Toast.makeText(getContext(), "pick date", Toast.LENGTH_SHORT);
                        dyear = year;
                        dmonth = month;
                        dday = dayOfMonth;
                        dcalendar.set(dyear,dmonth,dday);
                        refreshDate();
                    }
                },tyear, tmonth, tcalendar.get(Calendar.DATE));
                datepick.show();
            }
        });






        Today = tcalendar.getTimeInMillis();
        setday = dcalendar.getTimeInMillis();

        //System.out.printf("today y: %d m : %d d : %d\nsetday y: %d m : %d d : %d\n",tyear,tmonth, tday, dyear, dmonth, dday);

        result = (int)left;

        refreshDate();

        return view;
    }

    public void refreshDate(){

        setday = dcalendar.getTimeInMillis();
        left = (setday - Today)/(24*60*60*1000);
        result = (int)left;

        today.setText(String.format("오늘은 %d월 %d일", tmonth+1, tday));
        if(result>0){
            Dday.setText(String.format("종강까지 D-%d", result));
        }
        else if(result==0){
            Dday.setText(new String("종강 D-day"));
        }
        else{
            int temp = Math.abs(result);
            Dday.setText(String.format("종강하고 D+%d", temp));
        }

    }



}