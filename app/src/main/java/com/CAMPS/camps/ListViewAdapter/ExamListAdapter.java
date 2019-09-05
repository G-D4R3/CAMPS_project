package com.CAMPS.camps.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.CAMPS.camps.DataClass.TestSub;
import com.CAMPS.camps.MainActivity;
import com.CAMPS.camps.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ExamListAdapter extends BaseAdapter {
    public ArrayList<TestSub> data = null;
    LayoutInflater inflater=null;

    public ExamListAdapter(ArrayList<TestSub> data){
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
    public int getItemViewType(int position){
        if(getItem(position) instanceof TestSub){
            return 1;
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_list_view,parent, false);

        TextView mDate = (TextView)convertView.findViewById(R.id.examDate);
        TextView mPlace = (TextView)convertView.findViewById(R.id.examPlace);
        TextView mSubname = (TextView)convertView.findViewById(R.id.subname3);
        TextView mStart = (TextView)convertView.findViewById(R.id.startTime3);
        TextView mEnd = (TextView)convertView.findViewById(R.id.endTime3);

        TestSub sub = data.get(position);

        MainActivity main = MainActivity.getInstance();

        mSubname.setText(sub.getName());
        if(sub.getPlace()==null){
            mPlace.setVisibility(View.GONE);
        }
        else{
            mPlace.setVisibility(View.VISIBLE);
            mPlace.setText(sub.getPlace());
        }
        mDate.setText(String.format("%d월 %d일", sub.getTestDate().get(Calendar.MONTH)+1, sub.getTestDate().get(Calendar.DAY_OF_MONTH)));
        mStart.setText(sub.getStartHour()+"시 "+sub.getStartMinute()+"분");
        if(sub.getEndHour()==-1){
            mEnd.setVisibility(View.GONE);
        }
        else{
            mEnd.setVisibility(View.VISIBLE);
            mEnd.setText("~ "+sub.getEndHour()+"시 "+sub.getEndMinute()+"분");
        }



        return convertView;
    }

}
