package com.example.forstudent.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.forstudent.DataClass.HomeTimeTable;
import com.example.forstudent.R;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeClassAdapter extends BaseAdapter {
    ArrayList<HomeTimeTable> data;

    public HomeClassAdapter(ArrayList<HomeTimeTable> data){
        this.data = data;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_class_list_view,parent, false);
        TextView mTime = (TextView)convertView.findViewById(R.id.period4);
        TextView mClass = (TextView)convertView.findViewById(R.id.assign4);
        TextView mPlace = (TextView)convertView.findViewById(R.id.place4);

        HomeTimeTable htt = data.get(position);

        mTime.setText(String.format("%d:%2d - %d:%2d", htt.startTime.get(Calendar.HOUR), htt.startTime.get(Calendar.MINUTE), htt.endTime.get(Calendar.HOUR), htt.endTime.get(Calendar.MINUTE)));
        mClass.setText(htt.classTitle);
        mPlace.setText(htt.classPlace);


        return convertView;
    }
}
