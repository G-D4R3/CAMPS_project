package com.example.forstudent;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;

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
    boolean[] layoutset={true,true,true,true};
    TextView mSchedule;
    TextView mClass;
    TextView mAssignment;
    TextView mExam;
    GridView mScheduleList;
    GridView mClassList;
    GridView mAssignList;
    GridView mTestList;

    private ArrayList<Assignment> ass = new ArrayList<>();
    private ArrayList<TestSub> tests = new ArrayList<>();


    public HomeAssignmentAdapter assignmentAdapter;
    public HomeExamAdapter examAdapter;


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
        Button mSetup = (Button)view.findViewById(R.id.setup);

        mSchedule = (TextView)view.findViewById(R.id.home_schedule);
        mClass = (TextView)view.findViewById(R.id.home_class);
        mAssignment = (TextView)view.findViewById(R.id.home_ass);
        mExam = (TextView)view.findViewById(R.id.home_exam);
        mScheduleList = (GridView)view.findViewById(R.id.home_schedulelistview);
        mClassList = (GridView)view.findViewById(R.id.home_classlistview);
        mAssignList = (GridView)view.findViewById(R.id.home_asslistview);
        mTestList = (GridView)view.findViewById(R.id.home_examlistview);


        datecount = new DateCount(Calendar.getInstance());

        //load data
        MainActivity main = (MainActivity)getActivity();
        ass = main.important;
        tests = main.testSub;

        assignmentAdapter = new HomeAssignmentAdapter(ass);
        mAssignList.setAdapter(assignmentAdapter);

        examAdapter = new HomeExamAdapter(tests);
        mTestList.setAdapter(examAdapter);




        /*objectBox에서 불러오기*/

        datecount.dcalendar=user.getLastDay();


        restDay = datecount.calcDday();
        today.setText(String.format("종강 : %d월 %d일",(datecount.dcalendar.get(Calendar.MONTH)+1),datecount.dcalendar.get(Calendar.DAY_OF_MONTH)));
        setDateView();


        mSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity)getActivity();
                main.FragmentAdd(main.homeFragmentSetup);
                layoutset = main.homeFragmentSetup.select;
            }
        });

        setListView(layoutset);


        //텍스트 터치 시 날짜 변경 가능
        Dday.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final DatePickerDialog datepick = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
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





        return view;
    }
    private void setListView(boolean[] layoutset) {

        if(layoutset[0]==true){
            mSchedule.setVisibility(View.VISIBLE);
            mScheduleList.setVisibility(View.VISIBLE);
        }
        else{
            mSchedule.setVisibility(View.GONE);
            mScheduleList.setVisibility(View.GONE);
        }
        if(layoutset[1]==true){
            mClass.setVisibility(View.VISIBLE);
            mClassList.setVisibility(View.VISIBLE);
        }
        else{
            mClass.setVisibility(View.GONE);
            mClassList.setVisibility(View.GONE);
        }
        if(layoutset[2]==true){
            mAssignment.setVisibility(View.VISIBLE);
            mAssignList.setVisibility(View.VISIBLE);
        }
        else{
            mAssignment.setVisibility(View.GONE);
            mAssignList.setVisibility(View.GONE);
        }
        if(layoutset[3]==true){
            mExam.setVisibility(View.VISIBLE);
            mTestList.setVisibility(View.VISIBLE);
        }
        else{
            mExam.setVisibility(View.GONE);
            mTestList.setVisibility(View.GONE);
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        assignmentAdapter.notifyDataSetChanged();
        examAdapter.notifyDataSetChanged();
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