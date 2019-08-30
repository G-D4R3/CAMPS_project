package com.example.forstudent.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.forstudent.DataClass.Timetable;
import com.example.forstudent.R;

import java.util.ArrayList;
import java.util.Calendar;

public class TimeTableListAdapater extends BaseAdapter {
    public Timetable tt;
    public ArrayList<Calendar> start;
    public ArrayList<Calendar> end;

    public TimeTableListAdapater(Timetable input){
        this.tt = input;
        this.start = input.getStartTime();
        this.end = input.getEndTime();
    }

    @Override
    public int getCount() {
        return start.size();
    }

    @Override
    public Object getItem(int position) {
        return start.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_list_view,parent, false);

        TextView mTime;
        TextView mDay;

        mTime = (TextView)convertView.findViewById(R.id.timetable_time);
        mDay = (TextView) convertView.findViewById(R.id.timetable_day_of_weeK);

        Calendar mStart = start.get(position);
        Calendar mEnd = end.get(position);

        mDay.setText(getDay(mStart.get(Calendar.DAY_OF_WEEK)));
        mTime.setText(String.format("%d:%d - %d:%d", mStart.get(Calendar.HOUR), mStart.get(Calendar.MINUTE), mEnd.get(Calendar.HOUR), mEnd.get(Calendar.MINUTE)));



        return convertView;
    }

    private String getDay(int day){
        switch(day){
            case 1:
                return "월";
            case 2:
                return "화";
            case 3:
                return "수";
            case 4:
                return "목";
            case 5:
                return "금";
            case 6:
                return "토";

        }
        return "일";
    }



}
