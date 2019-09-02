package com.example.forstudent;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.DataClass.Grade;
import com.example.forstudent.DataClass.TestSub;

import java.util.Calendar;

public class addNewExamSub extends Fragment {
    /*** main ***/
    MainActivity main;
    InputMethodManager imm;

    /*** tmp ***/
    public String title;
    public String range;
    public String place;
    String mName=null;

    /*** view ***/
    EditText mSubname;
    EditText mPlace;
    EditText mRange;

    /*** flag ***/
    public boolean MOD = false;
    public boolean LOAD = false;
    boolean DATE_PICKED = false;
    boolean START_PICKED = false;

    /*** tmp ***/
    Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay;
    int mSHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mSMinute = calendar.get(Calendar.MINUTE);
    int mEHour = -1;
    int mEMinute = calendar.get(Calendar.MINUTE);
    public TestSub subject=null;


    /***** instanciate *****/
    public static addNewExamSub newInstance(){
        return new addNewExamSub();
    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        main = (MainActivity)getActivity();
        main.BACK_STACK=true;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /***** view load *****/
        View view = (View)inflater.inflate(R.layout.add_new_exam_sub, container, false);
        LinearLayout layout = (LinearLayout)view.findViewById(R.id.examlayout);
        mSubname = (EditText) view.findViewById(R.id.subName);
        mPlace = (EditText) view.findViewById(R.id.testPlace);
        mRange = (EditText)view.findViewById(R.id.Range2);
        TextView Title = (TextView)view.findViewById(R.id.fragTitle);
        TextView mDate = (TextView)view.findViewById(R.id.date2);
        TextView mStart = (TextView)view.findViewById(R.id.time2);
        TextView mEnd = (TextView)view.findViewById(R.id.time2_1);
        imm = main.keypad;


        /***** toolbar *****/
        main.centerToolbarTitle.setText("시험 추가");
        main.toolbar.setTitle("");
        main.invalidateOptionsMenu();
        main.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                MainActivity main = (MainActivity)getActivity();
                main.showActionBar();
                main.FragmentRemove(addNewExamSub.this);
            }
        });

        /***** 키보드 숨기기 *****/
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
            }
        });


        /***** modify면 textview set *****/
        if(MOD==true){
            mSubname.setText(subject.getName());
            mPlace.setText(subject.getPlace());
            mDate.setText((mMonth+1)+"월 "+mDay+"일");
            mStart.setText(mSHour+"시 "+mSMinute+"분");
            mEnd.setText(mEHour+"시 "+mEMinute+"분");
            mRange.setText(range);
            DATE_PICKED=true;
            START_PICKED=true;
        }

        if(LOAD==true){
            mSubname.setText(mName);
        }

        /***** 날짜 설정 *****/
        mDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hideKey();
                DatePickerDialog datepick = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText((month+1)+"월 "+dayOfMonth+"일");
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                        DATE_PICKED=true; //flag true
                    }
                },mYear, mMonth, calendar.get(Calendar.DATE));
                calendar.set(mYear,mMonth,calendar.get(Calendar.DATE)); //calendar에 set해서 기한 저장
                datepick.show();
            }
        });


        /***** 시험 시작 시간 *****/
        mStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                hideKey();
                TimePickerDialog timepick = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mStart.setText(hourOfDay+"시 "+minute+"분");
                        mSHour = hourOfDay;
                        mSMinute = minute;
                        START_PICKED=true; //flag true
                    }
                },mSHour,mSMinute,true);
                timepick.show();
            }
        });

        /***** 시험 종료 시간 *****/
        mEnd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                hideKey();
                TimePickerDialog timepick = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mEnd.setText(hourOfDay+"시 "+minute+"분");
                        mEHour = hourOfDay;
                        mEMinute = minute;
                    }
                },mEHour,mEMinute,true);
                timepick.show();
            }
        });

        return view;

    }

    /***** 키보드 숨기기 *****/
    private void hideKey() {
        imm.hideSoftInputFromWindow(mSubname.getWindowToken(),0);
        imm.hideSoftInputFromWindow(mRange.getWindowToken(),0);
        imm.hideSoftInputFromWindow(mPlace.getWindowToken(),0);
    }

    /***** toolbar *****/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_assignment,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MainActivity main = (MainActivity)getActivity();
        if (id == R.id.check_icon) {
            hideKey();
            Calendar calendar = Calendar.getInstance();
            calendar.set(mYear,mMonth,mDay);

            if(MOD==true){ //if this fragment is modify, then remove original object and add new one
                main.alarmDelete(subject);
                main.examFragment.ExamList.remove(subject);
                for(int i=0; i<main.grades.size(); i++){
                    if(main.grades.get(i)==mName){
                        main.grades.remove(i);
                    }
                }
                if(mRange.getText().toString().length()==0){
                    range = null;
                }
                else{
                    range = mRange.getText().toString();
                }
                if(mPlace.getText().toString().length()==0){
                    place = null;
                }
                else{
                    place = mPlace.getText().toString();
                }
                subject = new TestSub(mSubname.getText().toString(),calendar,place,mSHour,mSMinute,mEHour,mEMinute,range);
                main.alarmSet(subject);
                main.examFragment.ExamList.add(subject);
                main.FragmentRemove(addNewExamSub.this);
                Grade tmp = new Grade(subject.Name, 0, 4.5);
                main.grades.add(tmp);
            }
            else{
                if(mRange.getText().toString().length()==0){
                    range = null;
                }
                else{
                    range = mRange.getText().toString();
                }
                if(mPlace.getText().toString().length()==0){
                    place = null;
                }
                else{
                    place = mPlace.getText().toString();
                }

                if(mSubname.getText().toString().length()==0||DATE_PICKED==false||START_PICKED==false){
                    setYetDialog();
                }
                else{
                    subject = new TestSub(mSubname.getText().toString(),calendar,place,mSHour,mSMinute,mEHour,mEMinute,range);
                    main.examFragment.ExamList.add(subject);
                    main.alarmSet(subject);
                    main.showActionBar();
                    main.FragmentRemove(addNewExamSub.this);
                    Grade tmp = new Grade(subject.Name, 0, 4.5);
                    main.grades.add(tmp);
                }

            }

        }

        return true;
    }

    /***** 설정 미완료시 dialog *****/
    public void setYetDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("알림");
        dialog.setMessage("설정이 완료되지 않았습니다.");
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });

        dialog.show();
    }

    public void setName(String s) {
        mSubname.setText(s);
    }
}
