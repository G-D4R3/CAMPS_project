package com.example.forstudent;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExamListAdapter extends BaseAdapter {
    ArrayList<TestSub> data = null;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            Context context = parent.getContext();
            if(inflater==null){
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.exam_list_view,parent,false);

        }

        TextView mSubname = (TextView)convertView.findViewById(R.id.subname3);
        TextView mStart = (TextView)convertView.findViewById(R.id.startTime3);
        TextView mEnd = (TextView)convertView.findViewById(R.id.endTime3);

        TestSub sub = data.get(position);
        mSubname.setText(sub.getName());
        mStart.setText(sub.getStartHour()+"시 "+sub.getStartMinute()+"분");
        mEnd.setText(sub.getEndHour()+"시 "+sub.getEndMinute()+"분");


        return convertView;
    }

}
