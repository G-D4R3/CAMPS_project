package com.example.forstudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;


public class TimetableFragment extends Fragment {

    ArrayList<Schedule> classes = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_timetable, container, false);
        TimetableView timetable = (TimetableView)view.findViewById(R.id.timetable);
        TextView mAdd = (TextView)view.findViewById(R.id.addClass);

        Schedule mClass = new Schedule();
        mClass.setClassTitle("현대 암호 이론");
        mClass.setClassPlace("팔410");
        mClass.setProfessorName("예홍진");
        mClass.setStartTime(new Time(12,0));
        mClass.setEndTime(new Time(13,15));
        mClass.setDay(0);
        classes.add(mClass);
        timetable.add(classes);


        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<Schedule> classes) {

            }
        });



        return view;
    }
}