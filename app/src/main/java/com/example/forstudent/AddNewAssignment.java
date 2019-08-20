package com.example.forstudent;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.DataClass.Assignment;

import java.util.Calendar;

public class AddNewAssignment extends Fragment {

    Calendar today = Calendar.getInstance();
    Calendar period = Calendar.getInstance();
    Assignment ass;
    String Name;
    String Date;
    String range;
    Boolean Flag=false;  //중요도
    Boolean MOD = false;
    Boolean DATE_CHECKED=false;
    InputMethodManager input;
    EditText mName;
    EditText mRange;
    static boolean iscanceled;



    public static AddNewAssignment newInstance(){
        return new AddNewAssignment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.add_new_assignment, container, false);
        TextView mTitle = (TextView)view.findViewById(R.id.assTitle);
        mName = (EditText)view.findViewById(R.id.assName);
        TextView mDate = (TextView)view.findViewById(R.id.pdate2);

        mRange = (EditText)view.findViewById(R.id.Range3);

        MainActivity main = (MainActivity)getActivity();
        main.BACK_STACK=true;

        input = main.keypad;

        main.centerToolbarTitle.setText("과제 추가");
        main.toolbar.setTitle("");

        main.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                main.FragmentRemove(AddNewAssignment.this);
            }
        });



        if(MOD==true){
            DATE_CHECKED=true;
            mName.setText(ass.getName());
            Flag = ass.getFlag();
            mDate.setText(String.format("%d월 %d일",ass.getPeriod().get(Calendar.MONTH)+1,ass.getPeriod().get(Calendar.DAY_OF_MONTH)));
            //System.out.println(ass.getMemo());
            mRange.setText(ass.getMemo());
        }

        mDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hideKey();
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText((month+1)+"월 "+dayOfMonth+"일");
                        period.set(year,month,dayOfMonth);
                        DATE_CHECKED=true;
                    }
                },today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE));
                dialog.show();
            }
        });

        /*
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                MainActivity main = (MainActivity)getActivity();
                main.showActionBar();
                main.FragmentRemove(AddNewAssignment.this);
            }
        });

        mComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKey();
                if(Flag==true){
                    main.todoFragment.ImpList.remove(ass);
                }

                if(mName.getText().length()==0 || DATE_CHECKED==false){
                    setYetDialog();
                }
                else{
                    ass = new Assignment(mName.getText().toString(),period,mRange.getText().toString(),Flag);
                    MainActivity main = (MainActivity)getActivity();
                    main.todoFragment.AssList.add(ass);
                    if(Flag==true){
                        main.todoFragment.ImpList.add(ass);

                    }

                    main.FragmentRemove(AddNewAssignment.this);
                }

               // main.calendarFragment.dotAssignment();

            }

        });*/









        return view;

    }


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

    public void setAss(Assignment a){
        this.ass = a;
    }

    public void hideKeyBoard(View v){
        input.hideSoftInputFromWindow(mName.getWindowToken(),0);
        input.hideSoftInputFromWindow(mRange.getWindowToken(),0);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_assignment,menu);
    }

    private void hideKey() {
        input.hideSoftInputFromWindow(mName.getWindowToken(),0);
        input.hideSoftInputFromWindow(mRange.getWindowToken(),0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MainActivity main = (MainActivity)getActivity();
        if (id == R.id.check_icon) {
            hideKey();
            if (Flag == true) {
                main.todoFragment.ImpList.remove(ass);
            }

            if (mName.getText().length() == 0 || DATE_CHECKED == false) {
                setYetDialog();
            }

            else {
                ass = new Assignment(mName.getText().toString(), period, mRange.getText().toString(), Flag);
                System.out.println(ass.toString());
                main.todoFragment.AssList.add(ass);
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
