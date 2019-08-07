package com.example.forstudent.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.forstudent.DataClass.TestSub;
import com.example.forstudent.R;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeExamAdapter extends BaseAdapter {
    ArrayList<TestSub> testSubs = new ArrayList<>();

    public HomeExamAdapter(ArrayList<TestSub> tests) {
        testSubs = tests;
    }

    @Override
    public int getCount() {
        return testSubs.size();
    }

    @Override
    public Object getItem(int position) {
        return testSubs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_exam_list_view,parent, false);

        TextView mDate = (TextView)convertView.findViewById(R.id.period3);
        TextView mExam = (TextView)convertView.findViewById(R.id.assign3);

        TestSub sub = testSubs.get(position);

        mDate.setText(String.format("%d.%2d",sub.getTestDate().get(Calendar.MONTH)+1,sub.getTestDate().get(Calendar.DAY_OF_MONTH)));
        mExam.setText(sub.getName());


        return convertView;
    }
}
