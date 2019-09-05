package com.CAMPS.camps;

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
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.CAMPS.camps.DataClass.Assignment;

import java.util.Calendar;

public class AddNewAssignment extends Fragment {

    /*** main ***/
    MainActivity main;
    InputMethodManager input; //키보드

    /*** tmp ***/
    String Name;
    String Date;
    String range; //메모

    /*** view ***/
    EditText mName; //이름 입력
    EditText mRange; //메모 입력

    /*** flag ***/
    Boolean Flag=false; //중요도
    Boolean MOD = false; //수정인지 아닌지
    Boolean DATE_CHECKED=false; //false면 설정 완료하지 못함

    /*** storage ***/
    Calendar today = Calendar.getInstance(); //오늘 날짜
    Calendar period = Calendar.getInstance(); //과제 기한
    Assignment ass;


    /*** instanciate ***/
    public static AddNewAssignment newInstance(){
        return new AddNewAssignment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        main = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /***** view load *****/
        View view = (View)inflater.inflate(R.layout.add_new_assignment, container, false);
        mName = (EditText)view.findViewById(R.id.assName);
        TextView mDate = (TextView)view.findViewById(R.id.pdate2);
        TextView mTime = (TextView)view.findViewById(R.id.pdate4);
        mRange = (EditText)view.findViewById(R.id.Range3);
        input = main.keypad;

        /***** toolbar *****/
        main.BACK_STACK=true;
        main.centerToolbarTitle.setText("과제 추가");
        main.toolbar.setTitle("");
        main.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                main.FragmentRemove(AddNewAssignment.this);
            }
        });


        /***** modify면 textview set *****/
        if(MOD==true){
            DATE_CHECKED=true;
            mName.setText(ass.getName());
            Flag = ass.getFlag();
            mTime.setText(String.format("%02d시 %02d분",ass.getPeriod().get(Calendar.HOUR_OF_DAY),ass.getPeriod().get(Calendar.MINUTE)));
            mDate.setText(String.format("%d월 %d일",ass.getPeriod().get(Calendar.MONTH)+1,ass.getPeriod().get(Calendar.DAY_OF_MONTH)));
            mRange.setText(ass.getMemo());
        } //if문


        /***** 기한 설정 *****/
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText((month+1)+"월 "+dayOfMonth+"일");
                        period.set(year,month,dayOfMonth); //기한 설정
                        DATE_CHECKED=true; //flag true
                    }
                },period.get(Calendar.YEAR), period.get(Calendar.MONTH), period.get(Calendar.DATE));
                dialog.show();
            }
        });


        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                TimePickerDialog dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        period.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), period.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                        mTime.setText(hourOfDay+"시 "+minute+"분");
                    }
                }, period.get(Calendar.HOUR_OF_DAY), period.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });


        return view;

    }

    /***** 설정 미완 dialog *
     * 이름, 기한 미설정시 띄움 *****/
    public void setYetDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("알림");
        dialog.setMessage("설정이 완료되지 않았습니다.");
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /***setter***/
    public void setAss(Assignment a){
        this.ass = a;
    }


    /***키보드 숨김***/
    private void hideKey() {
        input.hideSoftInputFromWindow(mName.getWindowToken(),0);
        input.hideSoftInputFromWindow(mRange.getWindowToken(),0);
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
            if (Flag == true) {
                main.alarmDelete(ass);
                main.todoFragment.ImpList.remove(ass);
            }

            if (mName.getText().length() == 0 || DATE_CHECKED == false) {
                setYetDialog();
            }

            else {
                ass = new Assignment(mName.getText().toString(), period, mRange.getText().toString(), Flag);
                System.out.println(ass.toString());
                main.todoFragment.AssList.add(ass);
                main.alarmSet(ass);
                main.todoFragment.setView();
                if (Flag == true) {
                    main.todoFragment.ImpList.add(ass);
                }

                main.FragmentRemove(AddNewAssignment.this);
            }
        }

        return true;
    }


}
