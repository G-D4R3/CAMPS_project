package com.example.forstudent.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.forstudent.DataClass.Grade;
import com.example.forstudent.R;

import java.util.ArrayList;

public class GradeListAdapter extends BaseAdapter {
    ArrayList<Grade> data = new ArrayList<>();

    public GradeListAdapter(ArrayList<Grade> grades) {
        this.data = grades;
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_list_view,parent, false);
        Spinner mGrade = (Spinner)convertView.findViewById(R.id.grade_spinner);
        EditText mSubject = (EditText)convertView.findViewById(R.id.grade_subname);
        EditText mCredit = (EditText)convertView.findViewById(R.id.grade_list_credit);

        Grade grade = data.get(position);

        mGrade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:

                }
            }
        });

        if(grade.subject!=null){
            mSubject.setText(grade.subject);
            mCredit.setText((int)grade.credit);
        }

        return convertView;
    }

}
