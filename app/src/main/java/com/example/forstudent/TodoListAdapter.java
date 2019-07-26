package com.example.forstudent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class TodoListAdapter extends BaseAdapter {
    ArrayList<Assignment> data = new ArrayList<>();
     ViewHolder viewHolder;

    public TodoListAdapter(ArrayList<Assignment> input){
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.assign_list_view,parent, false);
        View v=convertView;

        TextView mPeriod = (TextView)convertView.findViewById(R.id.period);
        TextView mName = (TextView)convertView.findViewById(R.id.assign);
        CheckBox mCheck = (CheckBox)convertView.findViewById(R.id.checkBox);
        viewHolder = (ViewHolder) convertView.getTag();

        Assignment ass = data.get(position);
        mPeriod.setText(String.format("%d.%2d",(ass.getPeriod().get(Calendar.MONTH)+1),ass.getPeriod().get(Calendar.DAY_OF_MONTH)));
        mName.setText(ass.getName());





        return convertView;
    }

    public class ViewHolder{
        CheckBox Check;
    }
}
