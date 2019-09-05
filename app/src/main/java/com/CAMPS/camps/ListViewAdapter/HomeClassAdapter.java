package com.CAMPS.camps.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.CAMPS.camps.DataClass.HomeTimeTable;
import com.CAMPS.camps.R;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeClassAdapter extends BaseAdapter {
    ArrayList<HomeTimeTable> data;

    public HomeClassAdapter(ArrayList<HomeTimeTable> data){
        this.data = data;
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_class_list_view,parent, false);
        TextView mTime = (TextView)convertView.findViewById(R.id.period4);
        TextView mClass = (TextView)convertView.findViewById(R.id.assign4);
        TextView mPlace = (TextView)convertView.findViewById(R.id.place4);

        HomeTimeTable htt = data.get(position);

        mTime.setText(String.format("%02d:%02d - %02d:%02d", htt.startTime.get(Calendar.HOUR_OF_DAY), htt.startTime.get(Calendar.MINUTE), htt.endTime.get(Calendar.HOUR_OF_DAY), htt.endTime.get(Calendar.MINUTE)));
        mClass.setText(htt.classTitle);
        mPlace.setText(htt.classPlace);


        return convertView;
    }
}
