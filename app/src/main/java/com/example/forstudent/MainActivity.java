package com.example.forstudent;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.forstudent.BoxClass.Assignment_Model;
import com.example.forstudent.BoxClass.Schedule_Model;
import com.example.forstudent.BoxClass.TestSub_Model;
import com.example.forstudent.BoxHelperClass.AssignmentHelper;
import com.example.forstudent.BoxHelperClass.ScheduleHelper;
import com.example.forstudent.BoxHelperClass.TestSubHelper;
import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.DataClass.Schedule;
import com.example.forstudent.DataClass.TestSub;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.objectbox.Box;

public class MainActivity<notesBox> extends AppCompatActivity {
    private static MainActivity instance;

    private TextView mTextMessage;
    public FragmentManager fragmentManager = getSupportFragmentManager();

    //박스 선언은 여기에서 함. 유저정보, 시간표정보 등등 필요한 구성에 따라 나눌 예정
    private Box<UserData> userDataBox;
    private Box<Assignment_Model> assignmentBox;
    private Box<TestSub_Model> testBox;
    private Box<Schedule_Model> scheduleBox;

    //박스에 들어갈 객체 선언
    private UserData user;
    private Assignment_Model assignment_model;
    private TestSub_Model testSub_model;
    private Schedule_Model schedule_model;

    public HomeFragment homeFragment= new HomeFragment();
    public TimetableFragment timetableFragment= new TimetableFragment();
    public CalendarFragment calendarFragment= new CalendarFragment();
    public TodoFragment todoFragment= new TodoFragment();
    public ExamFragment examFragment= new ExamFragment();
    public addNewExamSub addnewExamsub = new addNewExamSub();
    public AddNewAssignment addNewAssignment = new AddNewAssignment();
    public HomeFragmentSetup homeFragmentSetup = new HomeFragmentSetup();
    public ActionBar actionBar;
    public Menu menu;

    //for storage
    ArrayList<Assignment> assignment = new ArrayList<>();
    ArrayList<Assignment> important = new ArrayList<>();
    ArrayList<TestSub> testSub = new ArrayList<>();
    ArrayList<Schedule> schedules = new ArrayList<>();

    InputMethodManager keypad;


    //store things
    //dday count
    int year;
    int month;
    int day;
    Date lastDay;

    final long id=77;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar= getSupportActionBar() ;

        //


        instance = this;
        ObjectBox.init(this);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //박스를 가져오는 작업
        userDataBox = ObjectBox.get().boxFor(UserData.class);
        assignmentBox = ObjectBox.get().boxFor(Assignment_Model.class);
        testBox = ObjectBox.get().boxFor(TestSub_Model.class);
        scheduleBox = ObjectBox.get().boxFor(Schedule_Model.class);

        //test 유저 처음 저장 작업 (처음실행용)
        if(userDataBox.isEmpty()) {

            //user 생성
            user = new UserData(id, "DEFAULT", new Date(), 99, true, true,  true,  true);
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
        //schedule data load
        for(long i=1; i<=scheduleBox.count(); i++){
            schedules.add(ScheduleHelper.getSchedule(i));
        }

        //assignment data load
        for(long i=1; i<=assignmentBox.count(); i++){
            assignment.add(AssignmentHelper.getAssignment(i));
        }
        for(int i=0; i<assignment.size(); i++){
            if(assignment.get(i).getFlag()==true){
                important.add(assignment.get(i));
            }
        }

        //Exam data load
        for(long i=1; i<=testBox.count(); i++){
            testSub.add(TestSubHelper.getTestSub(i));
        }






        //private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          //      = new BottomNavigationView.OnNavigationItemSelectedListener() {
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //transaction.setCustomAnimations(R.anim.replace_in,R.anim.replace_out,R.anim.replace_in,R.anim.replace_out);
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


        keypad = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ObjectBox.get().close();
        //
        //
        //
        //
        // ObjectBox.boxStore.deleteAllFiles();
        //
        // saveData();

    }



    //나중에 objectbox 구현하면 삭제해야할 부분
    protected void saveData(){
        SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = store.edit();
        editor.putInt("D-dayYear", year);
        editor.putInt("D-dayMonth", month);
        editor.putInt("D-dayDay", day);

    }

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
        transaction.setCustomAnimations(R.anim.popup,R.anim.slide_down,0,R.anim.slide_down);
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
    public Box getScheduleBox(){return scheduleBox;}
    public void setLastDay(Date lastDay){
        this.lastDay = lastDay;
    }
    public UserData getUser(){
        return user;
    }

    public Box<Assignment_Model> getAssignmentBox() {
        return assignmentBox;
    }

    public Assignment_Model getAssignment() {
        return assignment_model;
    }

    public Box<TestSub_Model> getTestSubBox(){
        return testBox;
    }

    public TestSub_Model getTestSub(){
        return testSub_model;
    }

    private void setActionbarTextColor(ActionBar actBar, int color) {

        String title = actBar.getTitle().toString();
        Spannable spannablerTitle = new SpannableString(title);
        spannablerTitle.setSpan(new ForegroundColorSpan(color), 0, spannablerTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        actBar.setTitle(spannablerTitle);

    }
    public void setActionBarTitle(String title) {
        Drawable backGround = getDrawable(R.drawable.actionbar_background);
        actionBar.setTitle(title);
        setActionbarTextColor(actionBar, Color.BLACK);
        actionBar.setBackgroundDrawable(backGround);
    }
    public void hideActionBar(){
        actionBar.hide();
    }
    public void showActionBar(){
        actionBar.show();
    }

    public static MainActivity getInstance() {
        return instance;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.setting_icon) {
            FragmentAdd(homeFragmentSetup);
            homeFragment.layoutset = homeFragmentSetup.select;
            homeFragment.setListView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting_icon) {

            Toast.makeText(this, "홈아이콘 클릭", Toast.LENGTH_SHORT).show();
            return true;

         }
            return super.onOptionsItemSelected(item);
        }*/


}

/*
ObjectBox 사용 안내


저장 하고자 하는 객체를 생성함 ( UserData 참고 : id 부분은 그대로 복사해서 사용하고 나머지 요소들은 원하는대로 추가)         {UserData}
**모든 요소에 대해서 생성자가 있어야함**
그 뒤 build를 한번 실행 ( Build -> Rebuild 해야 되는거 같음 ) ( 모델 파일을 생성하는 과정 )
MainActivity 최상단에 박스 선언 하는 부분에 방금 생성했던 객체를 제네릭으로 Box 멤버를 하나 생성함.                     {private Box<UserData> userDataBox;}
그 바로 아래 방금 작성한 객체를 담을 수 있는 변수를 하나 추가함.                                                   {private UserData user;}
추가한 두가지 멤버에 대해 MainActivity 최하단에 getter를 생성함.                                               {getUserDataBox(),getUser()}
onCreate 부분에 박스를 가져오는 과정 부분에 젤 위에서 만든 Box멤버에 ObjectBox.get().boxFor(객체.class);를 할당해줌.  {userDataBox = ObjectBox.get().boxFor(UserData.class);}
해당 멤버를 isEmpty를 통해 첫 실행의 경우 디폴트값을 설정하는 과정을 거쳐야함.                                       {if(userDataBox.isEmpty())}
윗 부분에서 생성함 객체를 담을 수 있는 변수를 instantiate해서 하나 생성해줌.                                       {user = new UserData(id, "DEFAULT", new Date(), 99);}
그리고 가져온 박스 멤버에 .put(id)를 하면 박스에 객체가 저장됨. (id는 고정된 final 멤버로 통일할 것임.)                 {userDataBox.put(user);}
만약 객체를 업데이트 하고 싶다면, 아까 생성한 객체 담는 멤버에 setter로 값을 바꿔주고 다시 .put(id)하면 됨.               {user.setName("JIYOUNG");userDataBox.put(user);}
객체를 상자에서 받아오고 싶다면 Box변수.get(id)하면 됨.                                                        {user = userDataBox.get(id);}
fragment에서 사용하고 싶은경우,((MainActivity)getActivity())를 통해 mainactivity를 받아온 뒤, 위에 생성한 박스    {user = (UserData) ((MainActivity)getActivity()).getUserDataBox().get(id);}
getter를 통해, 박스를 가져온 뒤, 위와 동일하게 진행하면 됨.
*/
