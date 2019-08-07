package com.example.forstudent.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.forstudent.R;
import com.github.tlaabs.timetableview.Schedule;

import java.util.ArrayList;

public class HomeExamAdapter extends BaseAdapter {
    ArrayList<Schedule> classes = new ArrayList<>();

    public HomeExamAdapter(ArrayList<Schedule> classes) {
        this.classes = classes;
    }

    @Override
    public int getCount() {
        return classes.size();
    }

    @Override
    public Object getItem(int position) {
        return classes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_exam_list_view,parent, false);

        TextView mDate = (TextView)convertView.findViewById(R.id.classday);
        TextView mtime = (TextView)convertView.findViewById(R.id.classtime);





        return convertView;
    }
}
