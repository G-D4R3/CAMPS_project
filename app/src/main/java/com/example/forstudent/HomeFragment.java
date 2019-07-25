package com.example.forstudent;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment {

    private TextView Dday;
    private TextView today;
    static dateCount datecount = new dateCount();
    private UserData user;
    final long id = 77;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = ((MainActivity)getActivity()).getUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = (View) inflater.inflate(R.layout.fragment_home,container,false);
        Dday = (TextView)view.findViewById(R.id.Dday);
        today = (TextView)view.findViewById(R.id.Today);

        datecount.dyear = ((MainActivity)getActivity()).year;
        datecount.dmonth = ((MainActivity)getActivity()).month;
        datecount.dday = ((MainActivity)getActivity()).day;


        datecount.calcDday();
        datecount.setView(Dday, today);


        datecount.refreshDate(datecount.result);


        //텍스트 터치 시 날짜 변경 가능
        Dday.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DatePickerDialog datepick = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast toast = Toast.makeText(getContext(), "pick date", Toast.LENGTH_SHORT);
                        datecount.dyear = year;
                        datecount.dmonth = month;
                        datecount.dday = dayOfMonth;
                        datecount.resultion();
                        datecount.refreshDate(datecount.result);
                        ((MainActivity)getActivity()).year = year;
                        ((MainActivity)getActivity()).month = month;
                        ((MainActivity)getActivity()).day = dayOfMonth;
                        user.setLastDay(new GregorianCalendar(datecount.dyear, datecount.dmonth, datecount.dday).getTime());
                        ((MainActivity)getActivity()).getUserDataBox().put(user);

                        user = (UserData) ((MainActivity)getActivity()).getUserDataBox().get(id);
                        System.out.println(user);
                        System.out.println((user.lastDay));
                    }
                },datecount.tyear, datecount.tmonth, datecount.tcalendar.get(Calendar.DATE));
                datepick.show();
            }
        });
        //System.out.printf("today y: %d m : %d d : %d\nsetday y: %d m : %d d : %d\n",tyear,tmonth, tday, dyear, dmonth, dday);
        return view;
    }




}