package com.CAMPS.camps.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.CAMPS.camps.DataClass.Event;
import com.CAMPS.camps.DataClass.Schedule;
import com.CAMPS.camps.DataClass.TestSub;
import com.CAMPS.camps.R;

import java.util.ArrayList;
public class CalendarListAdapter extends BaseAdapter{
    public ArrayList<Event> eventList = null;
    LayoutInflater inflater = null;

    public CalendarListAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position) instanceof TestSub){
            return 1;
        }
        return 0;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_list_view,parent, false);
        TextView title = (TextView)convertView.findViewById(R.id.calendar_title);
        TextView icon = (TextView)convertView.findViewById(R.id.calendar_icon);
        TextView time = (TextView)convertView.findViewById(R.id.calendar_time);
        int typeFlag=0;
        Event event = eventList.get(position);
        typeFlag = event.getType();
        title.setText(event.getTitle());
        time.setText(event.getHour()+"시 "+event.getMinute()+"분");
        switch (typeFlag){
            case 1:
                icon.setText("과제");
                break;
            case 2:
                icon.setText("일정");
                Schedule tmp = (Schedule) event;
                if(tmp.isImportant()) time.setVisibility(View.INVISIBLE);
                break;
            case 3:
                icon.setText("시험");
                break;
            default:
                icon.setText("에러");
        }
        return convertView;
    }
}
/*
public class CalendarListAdapter extends BaseAdapter {
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
        TextView mSubname = (TextView)convertView.findViewById(R.id.subname3);
        TextView mStart = (TextView)convertView.findViewById(R.id.startTime3);
        TextView mEnd = (TextView)convertView.findViewById(R.id.endTime3);

        TestSub sub = data.get(position);

        mSubname.setText(sub.getName());
        mDate.setText(String.format("%d월 %d일", sub.getTestDate().get(Calendar.MONTH)+1, sub.getTestDate().get(Calendar.DAY_OF_MONTH)));
        mStart.setText(sub.getStartHour()+"시 "+sub.getStartMinute()+"분");
        mEnd.setText("~ "+sub.getEndHour()+"시 "+sub.getEndMinute()+"분");



        return convertView;
    }

}
*/
