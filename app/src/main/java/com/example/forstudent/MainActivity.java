package com.example.forstudent;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private HomeFragment homeFragment= new HomeFragment();
    private TimetableFragment timetableFragment= new TimetableFragment();
    private CalendarFragment calendarFragment= new CalendarFragment();
    private TodoFragment todoFragment= new TodoFragment();
    private ExamFragment examFragment= new ExamFragment();

    public Calendar dcalendar = Calendar.getInstance(); // d-day count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          //      = new BottomNavigationView.OnNavigationItemSelectedListener() {
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
                        break;

                    case R.id.navigation_timetable:
                        transaction.replace(R.id.frame_layout, timetableFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_calendar:
                        transaction.replace(R.id.frame_layout, calendarFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_todo:
                        transaction.replace(R.id.frame_layout, todoFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_exam:
                        transaction.replace(R.id.frame_layout, examFragment).commitAllowingStateLoss();
                        break;

            }
                return true;
            }
        });

    }

}
