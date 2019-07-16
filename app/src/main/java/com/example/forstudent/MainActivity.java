package com.example.forstudent;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private HomeFragment homeFragment= new HomeFragment();
    private TimetableFragment timetableFragment= new TimetableFragment();
    private CalendarFragment calendarFragment= new CalendarFragment();
    private TodoFragment todoFragment= new TodoFragment();
    private ExamFragment examFragment= new ExamFragment();


    //for storage


    //store things
    //dday count
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        this.loadData();


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
                        transaction.replace(R.id.frame_layout, examFragment, "ExamFragment");
                        transaction.commitAllowingStateLoss();
                        break;

            }
                return true;
            }
        });







    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();

    }



    //나중에 objectbox 구현하면 삭제해야할 부분
    protected void saveData(){
        SharedPreferences store = getSharedPreferences("storage", MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.putInt("D-dayYear", year);
        editor.putInt("D-dayMonth", month);
        editor.putInt("D-dayDay", day);

    }

    //나중에 objectbox 구현하면 삭제해야할 부분
    protected void loadData(){
        SharedPreferences store = getSharedPreferences("storage", MODE_PRIVATE);
        year = store.getInt("D-dayYear", 2000);
        month = store.getInt("D-dayMonth", 1);
        day = store.getInt("D-dayDay", 1);
    }




    public void Fragmentchange(int index){

        switch(index){
            case 1: // exam/addDirectly
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                addNewExamSub mFrag = new addNewExamSub();
                transaction.replace(R.id.frame_layout, mFrag,"AddDirectly");
                transaction.commit();
            default:
                return;
        }

    }
}
