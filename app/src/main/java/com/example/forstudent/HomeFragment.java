package com.example.forstudent;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.DataClass.TestSub;
import com.example.forstudent.ListViewAdapter.HomeAssignmentAdapter;
import com.example.forstudent.ListViewAdapter.HomeExamAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    private TextView Dday;
    private TextView today;
    protected DateCount datecount;
    private UserData user;
    final long id = 77;
    int restDay=0;

    private ArrayList<Assignment> ass = new ArrayList<>();
    private ArrayList<TestSub> tests = new ArrayList<>();

    private ListView mAssignList;
    private ListView mTestList;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = ((MainActivity)getActivity()).getUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //view 정의
        View view = (View) inflater.inflate(R.layout.fragment_home,container,false);
        Dday = (TextView)view.findViewById(R.id.Dday);
        today = (TextView)view.findViewById(R.id.Today);


        //load data
        MainActivity main = (MainActivity)getActivity();

        ass = main.assignment;
        tests = main.testSub;

        for(int i=3; i<ass.size(); i++){
            ass.remove(i);
            tests.remove(i);
        }

        mAssignList = (ListView)view.findViewById(R.id.home_asslistview);
        mTestList = (ListView)view.findViewById(R.id.home_examlistview);

        HomeAssignmentAdapter assignmentAdapter = new HomeAssignmentAdapter(ass);
        mAssignList.setAdapter(assignmentAdapter);

        HomeExamAdapter examAdapter = new HomeExamAdapter(tests);
        mTestList.setAdapter(examAdapter);








        datecount = new DateCount(Calendar.getInstance());

        /*objectBox에서 불러오기*/

        datecount.dcalendar=user.getLastDay();


        restDay = datecount.calcDday();
        today.setText(String.format("오늘은 %d월 %d일",(datecount.tcalendar.get(Calendar.MONTH)+1),datecount.tcalendar.get(Calendar.DAY_OF_MONTH)));
        setDateView();


        //텍스트 터치 시 날짜 변경 가능
        Dday.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final DatePickerDialog datepick = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast toast = Toast.makeText(getContext(), "pick date", Toast.LENGTH_SHORT);
                        datecount.dcalendar.set(Calendar.YEAR,year);
                        datecount.dcalendar.set(Calendar.MONTH,month);
                        datecount.dcalendar.set(Calendar.DATE,dayOfMonth);
                        restDay = datecount.calcDday();
                        setDateView();
                        user.setLastDay(new Date(datecount.dcalendar.getTimeInMillis()));
                        ((MainActivity)getActivity()).getUserDataBox().put(user);

                        user = (UserData) ((MainActivity)getActivity()).getUserDataBox().get(id);
                        //System.out.println(user);
                        //System.out.println((user.lastDay));
                    }
                },datecount.tcalendar.get(Calendar.YEAR), datecount.tcalendar.get(Calendar.MONTH), datecount.tcalendar.get(Calendar.DATE));
                datepick.show();
            }
        });
        //System.out.printf("today y: %d m : %d d : %d\nsetday y: %d m : %d d : %d\n",tyear,tmonth, tday, dyear, dmonth, dday);
        return view;
    }


    public void setDateView(){
        //D-day textview set
        if(restDay>0){
            Dday.setText(String.format("종강까지 D-%d",restDay));
        }
        else if(restDay==0){
            Dday.setText("종강 D-day");
        }
        else{
            int temp = restDay*(-1);
            Dday.setText(String.format("종강하고 D+%d",temp));
        }
    }




}