package com.example.forstudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.DataClass.Timetable;
import com.example.forstudent.ListViewAdapter.TimeTableListAdapater;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNewClass extends Fragment {

    /*** main ***/
    MainActivity main;
    InputMethodManager input;
    Schedule schedule;
    TimetableView timetable;

    /*** tmp ***/
    String Lecture;
    String Professor;
    Calendar start_cal;
    Calendar end_cal;
    int dayOfWeek;
    ArrayList<Calendar> startTimes = new ArrayList<>();
    ArrayList<Calendar> endTimes = new ArrayList<>();
    ArrayList<String> lectureRooms = new ArrayList<>();


    /*** view ***/
    Context context;
    EditText mLecture;
    EditText mProfessor;
    TextView mTimeAdd;
    ListView mTimeList;
    TimeTableListAdapater adapter;


    /*** flag ***/
    Boolean MOD = false;

    /*** Storage ***/
    Timetable lecture;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        main = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /*** view load ***/
        View view = (View) inflater.inflate(R.layout.add_new_class, container, false);

        mLecture = (EditText) view.findViewById(R.id.className);
        mProfessor = (EditText) view.findViewById(R.id.professor);
        mTimeAdd = (TextView) view.findViewById(R.id.addButton);
        mTimeList = (ListView)view.findViewById(R.id.timetable_time_list);
        adapter = new TimeTableListAdapater(startTimes, endTimes, lectureRooms);
        mTimeList.setAdapter(adapter);


        /***** toolbar *****/
        main.BACK_STACK = true;
        main.centerToolbarTitle.setText("과목 추가");
        main.toolbar.setTitle("");
        main.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hideKey();
                main.FragmentRemove(AddNewClass.this);
            }
        });



        mTimeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTime addFragment = AddNewTime.newInstance();
                MainActivity main = (MainActivity) getActivity();
                addFragment.addNewClass = getInstance();
                main.FragmentAdd(addFragment);

            }
        });

        mTimeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage("삭제하시겠습니까?");
                dialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startTimes.remove(pos);
                        endTimes.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.create().show();
            }
        });





        return view;

    }

    /***** toolbar *****/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_assignment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MainActivity main = (MainActivity) getActivity();
        if (id == R.id.check_icon) {
            lecture = new Timetable(mLecture.getText().toString(),mProfessor.getText().toString(),startTimes,endTimes,lectureRooms);
            main.timeTables.add(lecture);
            //main.timetableFragment.makeSticker(lecture);

            main.FragmentRemove(AddNewClass.this);

            System.out.println(lecture.toString());
        }

        return true;
    }

    public static AddNewClass newInstance() {
        return new AddNewClass();
    }

    /*** datainput ***/
    private void inputDataProcessing() {
        schedule.setClassTitle(mLecture.getText().toString());
        schedule.setProfessorName(mProfessor.getText().toString());

    }



    /*** spinner ***/
    private int spin(Spinner s) {
        String input;
        input = s.getSelectedItem().toString();

        if (input.equals("월")) return 1;
        else if (input.equals("화")) return 2;
        else if (input.equals("수")) return 3;
        else if (input.equals("목")) return 4;
        else if (input.equals("금")) return 5;
        else if (input.equals("토")) return 6;
        else return 0;
    }
    public AddNewClass getInstance(){
        return this;
    }

}


