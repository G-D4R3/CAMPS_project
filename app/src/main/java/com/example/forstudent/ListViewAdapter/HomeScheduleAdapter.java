package com.example.forstudent.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.forstudent.DataClass.Schedule;
import com.example.forstudent.R;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeScheduleAdapter extends BaseAdapter {
    ArrayList<Schedule> schedules = new ArrayList<>();
    public HomeScheduleAdapter(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public int getCount() {
        return schedules.size();
    }

    @Override
    public Object getItem(int position) {
        return schedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_schedule_list_view,parent, false);
        TextView mDate = (TextView)convertView.findViewById(R.id.period5);
        TextView mSchedule = (TextView)convertView.findViewById(R.id.assign5);

        Schedule s = schedules.get(position);

        System.out.println("Title "+s.getTitle());

        mDate.setText(String.format("%d.%2d",s.getDate().get(Calendar.MONTH), s.getDate().get(Calendar.DAY_OF_MONTH)));
        mSchedule.setText(s.getTitle());

        return convertView;
    }
}
