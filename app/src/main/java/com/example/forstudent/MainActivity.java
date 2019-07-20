package com.example.forstudent;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.objectbox.Box;

public class MainActivity<notesBox> extends AppCompatActivity {
    private TextView mTextMessage;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    //박스 선언은 여기에서 함. 유저정보, 시간표정보 등등 필요한 구성에 따라 나눌 예정
    private Box<UserData> userDataBox;

    //유저 선언
    private UserData user;

    public HomeFragment homeFragment= new HomeFragment();
    public TimetableFragment timetableFragment= new TimetableFragment();
    public CalendarFragment calendarFragment= new CalendarFragment();
    public TodoFragment todoFragment= new TodoFragment();
    public ExamFragment examFragment= new ExamFragment();
    public addNewExamSub addnewExamsub = new addNewExamSub();


    //for storage


    //store things
    //dday count
    int year;
    int month;
    int day;
    Date lastDay;

    long id=77;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectBox.init(this);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //박스를 가져오는 작업
        userDataBox = ObjectBox.get().boxFor(UserData.class);
        //test 유저 처음 저장 작업 (처음실행용)
        if(userDataBox.isEmpty()) {

            //user 생성
            user = new UserData(id, "DEFAULT", new Date(), 99);
            //박스에 user 객체 저장
            userDataBox.put(user);

            System.out.println("Initialize user "+id);
        }
        else{
            user = userDataBox.get(id);
            System.out.println("DAY "+user.lastDay);
        }

        if(userDataBox.get(id).getName().equals("DEFAULT")){
            System.out.println("Change Name");
            //디폴트인 경우 이름 바꿈, 텍스트박스안 텍스트를 받아오거나 하는 경우로 사용하면됨.
            user.setName("JIYOUNG");
            userDataBox.put(user);

        }

        this.loadData(user);



        //private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          //      = new BottomNavigationView.OnNavigationItemSelectedListener() {
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        transaction.replace(R.id.frame_layout, homeFragment,"Home Fragment").commitAllowingStateLoss();
                        break;

                    case R.id.navigation_timetable:
                        transaction.replace(R.id.frame_layout, timetableFragment, "Time Table Fragment").commitAllowingStateLoss();
                        break;
                    case R.id.navigation_calendar:
                        transaction.replace(R.id.frame_layout, calendarFragment, "Calendar Fragment").commitAllowingStateLoss();
                        break;
                    case R.id.navigation_todo:
                        transaction.replace(R.id.frame_layout, todoFragment, "Todo Fragment").commitAllowingStateLoss();
                        break;
                    case R.id.navigation_exam:
                        transaction.replace(R.id.frame_layout, examFragment, "ExamFragment").commitAllowingStateLoss();
                        break;

            }
                return true;
            }
        });







    }

    @Override
    protected void onStop() {
        super.onStop();
        //saveData();

    }


/*
    //나중에 objectbox 구현하면 삭제해야할 부분
    protected void saveData(){
        SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = store.edit();
        editor.putInt("D-dayYear", year);
        editor.putInt("D-dayMonth", month);
        editor.putInt("D-dayDay", day);

    }*/

    //나중에 objectbox 구현하면 삭제해야할 부분
    protected void loadData(UserData user){

        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(user.lastDay);

        year = lastDay.get(Calendar.YEAR);
        month = lastDay.get(Calendar.MONTH);
        day = lastDay.get(Calendar.DAY_OF_MONTH);
        /*
        SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(this);
        year = store.getInt("D-dayYear", 2000);
        month = store.getInt("D-dayMonth", 1);
        day = store.getInt("D-dayDay", 1);*/
    }




    public void FragmentAdd(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void FragmentRemove(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        fragmentManager.popBackStack();
        transaction.commit();

    }
    public Box getUserDataBox(){
        return userDataBox;
    }
    public void setLastDay(Date lastDay){
        this.lastDay = lastDay;
    }
    public UserData getUser(){
        return user;
    }

}

