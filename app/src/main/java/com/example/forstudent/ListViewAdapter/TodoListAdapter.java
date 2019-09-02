package com.example.forstudent.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.MainActivity;
import com.example.forstudent.R;

import java.util.ArrayList;
import java.util.Calendar;

public class TodoListAdapter extends BaseAdapter {
    public ArrayList<Assignment> data = new ArrayList<>();
    TextView mHeader;

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
        TextView mPeriod = (TextView)convertView.findViewById(R.id.period);
        TextView mName = (TextView)convertView.findViewById(R.id.assign);
        CheckBox mCheck = (CheckBox)convertView.findViewById(R.id.checkBox);
        MainActivity main = MainActivity.getInstance();


        Assignment ass = (Assignment)data.get(position);
        mPeriod.setText(String.format("%d.%2d",(ass.getPeriod().get(Calendar.MONTH)+1),ass.getPeriod().get(Calendar.DAY_OF_MONTH)));
        mName.setText(ass.getName());

        mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) { //나중에 애니메이션 추가
                    Toast.makeText(main.todoFragment.getContext(),"완료",Toast.LENGTH_SHORT).show();
                    for(int i=0; i<main.todoFragment.AssList.size(); i++){
                        if(ass.equals(main.todoFragment.AssList.get(i))){
                            main.todoFragment.RemoveAss(ass);
                            break;
                        }
                    }
                }
            }
        });




        return convertView;
    }


}
