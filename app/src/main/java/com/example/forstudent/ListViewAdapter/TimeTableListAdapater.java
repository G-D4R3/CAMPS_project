package com.example.forstudent.ListViewAdapter;

import android.app.TimePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.DataClass.Timetable;
import com.example.forstudent.MainActivity;
import com.example.forstudent.R;

import java.util.ArrayList;
import java.util.Calendar;

public class TimeTableListAdapater extends BaseAdapter {
    public ArrayList<Timetable> data = new ArrayList<>();
    TextView mHeader;

    public TimeTableListAdapater(ArrayList<Timetable> input){
        this.data = input;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_list_view,parent, false);

        TextView startTime2;
        TextView endTime2;

        Spinner day2;

        EditText mLectureRoom2;

        startTime2 = (TextView)convertView.findViewById(R.id.start_time2);
        endTime2 = (TextView)convertView.findViewById(R.id.end_time2);

        day2 = (Spinner)convertView.findViewById(R.id.day_spinner2);

        mLectureRoom2 = (EditText)convertView.findViewById(R.id.location_2);

        Timetable tt = data.get(position);



        return convertView;
    }



}
