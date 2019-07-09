package com.example.forstudent;

import java.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExamFragment extends Fragment{


    private TextView Dday;
    private TextView today;

    //오늘 날짜
    private int tyear;
    private int tmonth;
    private int tday;

    //dday 설정 날짜
    private int dyear=2019;
    private int dmonth=9;
    private int dday=9;

    private long Today;
    private long setday;
    private long left;
    private int result=0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_exam,container,false);
        Dday = (TextView)view.findViewById(R.id.Dday);
        today = (TextView)view.findViewById(R.id.Today);

        //텍스트 터치 시 날짜 변경 가능
        Dday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDate();
            }
        });



        //현재 날짜
        Calendar tcalendar = Calendar.getInstance();
        tyear = tcalendar.get(Calendar.YEAR);
        tmonth = tcalendar.get(Calendar.MONTH);
        tday = tcalendar.get(Calendar.DAY_OF_MONTH);


        //설정한 날짜. 현재 날짜 받아올때 월수가 부족한걸 감안
        Calendar dcalendar = Calendar.getInstance();
        dcalendar.set(dyear, dmonth-1, dday);

        Today = tcalendar.getTimeInMillis();
        setday = dcalendar.getTimeInMillis();
        left = (setday - Today)/(24*60*60*1000);
        //System.out.printf("today y: %d m : %d d : %d\nsetday y: %d m : %d d : %d\n",tyear,tmonth, tday, dyear, dmonth, dday);

        result = (int)left;

        refreshDate();

        return view;
    }

    public void refreshDate(){

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

    public void setDate(){

    }



}