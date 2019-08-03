package com.example.forstudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddNewSchedule extends Fragment {

    int year;
    int month;
    int day;
    int hour;
    int minute;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = (View)inflater.inflate(R.layout.add_new_schedule, container, false);
        final TextView dateText  = (TextView)view.findViewById(R.id.schedule_date);
        final TextView cancleButton = (TextView)view.findViewById(R.id.cancle_add_schedule);
        final LinearLayout layout = (LinearLayout)view.findViewById(R.id.schedule_layout);
        dateText.setText(String.format("%d년 %d월 %d일",year,month,day));
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hideKey();
            }
        });
        return view;
    }
    /*
    private void hideKey() {
        imm.hideSoftInputFromWindow(mSubname.getWindowToken(),0);
        imm.hideSoftInputFromWindow(mRange.getWindowToken(),0);
    }*/

    public static AddNewSchedule newInstance(){
        return new AddNewSchedule();
    }
}
