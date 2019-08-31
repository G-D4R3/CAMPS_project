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
    public ArrayList<String> place;

    public TimeTableListAdapater(ArrayList<Calendar> start, ArrayList<Calendar> end, ArrayList<String> place){
        this.start = start;
        this.end = end;
        this.place = place;
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
        TextView mPlace;

        mTime = (TextView)convertView.findViewById(R.id.timetable_time);
        mDay = (TextView) convertView.findViewById(R.id.timetable_day_of_weeK);
        mPlace = (TextView)convertView.findViewById(R.id.timetable_place);

        Calendar mStart = start.get(position);
        Calendar mEnd = end.get(position);
        String mRoom = place.get(position);

        mDay.setText(getDay(mStart.get(Calendar.DAY_OF_WEEK)));
        mTime.setText(String.format("%d:%2d - %d:%2d", mStart.get(Calendar.HOUR), mStart.get(Calendar.MINUTE), mEnd.get(Calendar.HOUR), mEnd.get(Calendar.MINUTE)));
        mPlace.setText(mRoom);


        return convertView;
    }

    private String getDay(int day){
        switch(day){
            case 2:
                return "월";
            case 3:
                return "화";
            case 4:
                return "수";
            case 5:
                return "목";
            case 6:
                return "금";
            case 7:
                return "토";

        }
        return "일";
    }



}
