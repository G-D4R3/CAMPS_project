package com.CAMPS.camps;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.CAMPS.camps.DataClass.Assignment;
import com.CAMPS.camps.DataClass.HomeTimeTable;
import com.CAMPS.camps.DataClass.Schedule;
import com.CAMPS.camps.DataClass.TestSub;
import com.CAMPS.camps.DataClass.Timetable;
import com.CAMPS.camps.ListViewAdapter.HomeAssignmentAdapter;
import com.CAMPS.camps.ListViewAdapter.HomeClassAdapter;
import com.CAMPS.camps.ListViewAdapter.HomeExamAdapter;
import com.CAMPS.camps.ListViewAdapter.HomeScheduleAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class HomeFragment extends Fragment {

    /*** main ***/
    MainActivity main;

    /*** tmp ***/
    public boolean BAD_SCHOOL_NAME = false;
    /*** view ***/
    private TextView Dday;
    private TextView today;
    protected DateCount datecount;
    int restDay=0;

    boolean[] layoutset={true,true,true,true};

    TextView mSchedule;
    TextView mClass;
    TextView mAssignment;
    TextView mExam;
    TextView mNoSchedule;
    TextView mNoClass;
    TextView mNoAssignment;
    TextView mNoExam;

    ListView mScheduleList;
    ListView mClassList;
    ListView mAssignList;
    ListView mTestList;

    LinearLayout mScheduleLayout;
    LinearLayout mClassLayout;
    LinearLayout mAssignmentLayout;
    LinearLayout mExamLayout;

    public HomeAssignmentAdapter assignmentAdapter;
    public HomeClassAdapter homeClassAdapter;
    public HomeExamAdapter examAdapter;
    public HomeScheduleAdapter scheduleAdapter;


    /*** flag ***/
    boolean mSetAssignment=true; //true면 전부,  false면 중요한 과제만 표시

    /*** storage ***/

    private UserData user;
    static final long id = 77;

    public ArrayList<Assignment> ass = new ArrayList<>();
    public ArrayList<Timetable> classes = new ArrayList<>();
    public ArrayList<HomeTimeTable> homeTimeTables = new ArrayList<>();
    public ArrayList<TestSub> tests = new ArrayList<>();
    public ArrayList<Schedule> schedules = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        main = (MainActivity)getActivity();
        user = main.getUser();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /***** view load *****/
        View view = (View) inflater.inflate(R.layout.fragment_home,container,false);
        Dday = (TextView)view.findViewById(R.id.Dday);
        today = (TextView)view.findViewById(R.id.Today);

        /*** layout ***/
        mScheduleLayout = (LinearLayout)view.findViewById(R.id.home_schedule_layout);
        mClassLayout = (LinearLayout)view.findViewById(R.id.home_class_layout);
        mAssignmentLayout = (LinearLayout)view.findViewById(R.id.home_assignment_layout);
        mExamLayout = (LinearLayout)view.findViewById(R.id.home_exam_layout);

        /*** section header ***/
        mSchedule = (TextView)view.findViewById(R.id.home_schedule);
        mClass = (TextView)view.findViewById(R.id.home_class);
        mAssignment = (TextView)view.findViewById(R.id.home_ass);
        mExam = (TextView)view.findViewById(R.id.home_exam);

        /*** null check ***/
        mNoSchedule = (TextView)view.findViewById(R.id.noschedule);
        mNoClass = (TextView)view.findViewById(R.id.noclass);
        mNoAssignment = (TextView)view.findViewById(R.id.noassignment);
        mNoExam = (TextView)view.findViewById(R.id.noexam);

        /*** listview ***/
        mScheduleList = (ListView)view.findViewById(R.id.home_schedulelistview);
        mClassList = (ListView)view.findViewById(R.id.home_classlistview);
        mAssignList = (ListView)view.findViewById(R.id.home_asslistview);
        mTestList = (ListView)view.findViewById(R.id.home_examlistview);

        //종강 날짜 count
        datecount = new DateCount(Calendar.getInstance());
        datecount.dcalendar=user.getLastDay();
        restDay = datecount.calcDday();
        setDateView();

        //set list data
        initArrayLists();
        ListViewSetter listViewSetter = new ListViewSetter();


        /*** adapter set ***/
        scheduleAdapter = new HomeScheduleAdapter(schedules);
        mScheduleList.setAdapter(scheduleAdapter);
        listViewSetter.setListViewHeight(mScheduleList);

        homeClassAdapter = new HomeClassAdapter(homeTimeTables);
        mClassList.setAdapter(homeClassAdapter);
        listViewSetter.setListViewHeight(mClassList);

        assignmentAdapter = new HomeAssignmentAdapter(ass);
        mAssignList.setAdapter(assignmentAdapter);
        listViewSetter.setListViewHeight(mAssignList);

        examAdapter = new HomeExamAdapter(tests);
        mTestList.setAdapter(examAdapter);
        listViewSetter.setListViewHeight(mTestList);

        setListView();
        setExist();



        /***** toolbar *****/
        main.setActionBarTitle(" 홈");
        main.centerToolbarTitle.setText("");
        main.invalidateOptionsMenu();




        /***** navigationing  *****/
        mScheduleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.navBar.setSelectedItemId(R.id.navigation_calendar);
            }
        });
        mScheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.navBar.setSelectedItemId(R.id.navigation_calendar);
            }
        });
        mClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.navBar.setSelectedItemId(R.id.navigation_timetable);
            }
        });
        mClassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.navBar.setSelectedItemId(R.id.navigation_timetable);
            }
        });
        mAssignmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.navBar.setSelectedItemId(R.id.navigation_todo);
            }
        });
        mAssignList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.navBar.setSelectedItemId(R.id.navigation_todo);
            }
        });
        mExamLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.navBar.setSelectedItemId(R.id.navigation_exam);
            }
        });
        mTestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.navBar.setSelectedItemId(R.id.navigation_exam);
            }
        });




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
                },datecount.dcalendar.get(Calendar.YEAR), datecount.dcalendar.get(Calendar.MONTH), datecount.dcalendar.get(Calendar.DATE));
                datepick.show();
            }
        }); //Dday set onclicklistener



        return view;
    } //OnCreateView

    /***** main에서 arraylist 불러옴 *****/
    private void initArrayLists() {

        Calendar today = Calendar.getInstance();

        //모두 불러옴
        schedules = main.schedules;
        classes = main.timeTables;

        if(mSetAssignment==true){
            ass = main.assignment;
        }
        else{
            ass = main.important;
        } // setting에 설정한 중요한 과제만 표시할지 전부 표시할지에 따라 다르게 불러옴

        tests = main.testSub;


        //schedule
        int position=0;
        int size = schedules.size();
        int rest = 0;

        for(int i=0; i<size; i++){
            datecount.dcalendar.set(schedules.get(i).getDate().get(Calendar.YEAR),schedules.get(i).getDate().get(Calendar.MONTH),schedules.get(i).getDate().get(Calendar.DAY_OF_MONTH));
            rest = datecount.calcDday();
            if(rest>=0){
                position = i;
                break;
            }
        } //오늘 날짜 이후 일정 제외
        schedules = new ArrayList(schedules.subList(position, size));
        if(schedules.size()>4){
            schedules = new ArrayList(schedules.subList(0,4));
        }


        //classes
        position=0;
        size=classes.size();
        homeTimeTables = new ArrayList<>();
        for(Timetable tmp : classes){
            for(int i=0; i<tmp.startTime.size(); i++){
                if(tmp.getStartTime().get(i).get(Calendar.DAY_OF_WEEK)==today.get(Calendar.DAY_OF_WEEK)){
                    HomeTimeTable htt = new HomeTimeTable(tmp.getClassTitle(), tmp.startTime.get(i), tmp.endTime.get(i), tmp.classPlace.get(i));
                    homeTimeTables.add(htt);
                }
            }
        }



        //assignment


        if(main.getUser().homeAssignementViewCheck==R.id.ImpOnly){ // just improtant
            position=0;
            ArrayList<Assignment> tmp = main.assignment;
            size = tmp.size();
            ass = new ArrayList<>();

            for(int i=0; i<size; i++){
                if(tmp.get(i).flag==true){
                    ass.add(tmp.get(i));
                }
            }
            size = ass.size();
            for(int i=0; i<size; i++){
                datecount.dcalendar.set(ass.get(i).getPeriod().get(Calendar.YEAR), ass.get(i).getPeriod().get(Calendar.MONTH), ass.get(i).getPeriod().get(Calendar.DAY_OF_MONTH));
                rest = datecount.calcDday();
                if(rest>=0){
                    position = i;
                    break;
                }
            }
            ass = new ArrayList(ass.subList(position, size));
        }
        else{
            position=0;
            size = ass.size();
            for(int i=0; i<size; i++){
                datecount.dcalendar.set(ass.get(i).getPeriod().get(Calendar.YEAR), ass.get(i).getPeriod().get(Calendar.MONTH), ass.get(i).getPeriod().get(Calendar.DAY_OF_MONTH));
                rest = datecount.calcDday();
                if(rest>=0){
                    position = i;
                    break;
                }
            }
            ass = new ArrayList(ass.subList(position, size));
        }



        //exam
        position = 0;
        size = tests.size();
        for(int i=0; i<size; i++){
            datecount.dcalendar.set(tests.get(i).getTestDate().get(Calendar.YEAR), tests.get(i).getTestDate().get(Calendar.MONTH), tests.get(i).getTestDate().get(Calendar.DAY_OF_MONTH));
            rest = datecount.calcDday();
            if(rest>=0){
                position = i;
                break;
            }
        }
        tests = new ArrayList(tests.subList(position, size));


    } //initArraylist

    /***** arraylist size check => visibility set *****/
    private void setExist() {
        if(layoutset[0]==true){
            if(schedules.size()>0){
                mNoSchedule.setVisibility(View.GONE);
            }
            else{
                mNoSchedule.setVisibility(View.VISIBLE);
            }
        }
        if(layoutset[1]==true){
            if(homeTimeTables.size()>0){
                mNoClass.setVisibility(View.GONE);
            }
            else{
                mNoClass.setVisibility(View.VISIBLE);
            }
        }
        if(layoutset[2]==true){
            if(ass.size()>0){
                mNoAssignment.setVisibility(View.GONE);
            }
            else{
                mNoAssignment.setVisibility(View.VISIBLE);
            }
        }
        if(layoutset[3]==true){
            if(tests.size()>0){
                mNoExam.setVisibility(View.GONE);
            }
            else{
                mNoExam.setVisibility(View.VISIBLE);
            }
        }
    }


    /***** 설정에 따라 체크한 리스트만 visible *****/
    public void setListView() { //설정한 레이아웃 적용

        MainActivity main = (MainActivity)getActivity();
        layoutset[0]=main.getUser().isHomeScheduleCheck();
        layoutset[1]=main.getUser().isHomeClassCheck();
        layoutset[2]=main.getUser().isHomeAssignmentCheck();
        layoutset[3]=main.getUser().isHomeExamCheck();


        if(layoutset[0]==true){
            mSchedule.setVisibility(View.VISIBLE);
            mScheduleList.setVisibility(View.VISIBLE);
        }
        else{
            mSchedule.setVisibility(View.GONE);
            mNoSchedule.setVisibility(View.GONE);
            mScheduleList.setVisibility(View.GONE);
        }
        if(layoutset[1]==true){
            mClass.setVisibility(View.VISIBLE);
            mClassList.setVisibility(View.VISIBLE);
        }
        else{
            mClass.setVisibility(View.GONE);
            mNoClass.setVisibility(View.GONE);
            mClassList.setVisibility(View.GONE);
        }
        if(layoutset[2]==true){
            mAssignment.setVisibility(View.VISIBLE);
            mAssignList.setVisibility(View.VISIBLE);
        }
        else{
            mAssignment.setVisibility(View.GONE);
            mNoAssignment.setVisibility(View.GONE);
            mAssignList.setVisibility(View.GONE);
        }
        if(layoutset[3]==true){
            mExam.setVisibility(View.VISIBLE);
            mTestList.setVisibility(View.VISIBLE);
        }
        else{
            mExam.setVisibility(View.GONE);
            mNoExam.setVisibility(View.GONE);
            mTestList.setVisibility(View.GONE);
        }

    } //setListView


    @Override
    public void onStart() {
        super.onStart();
        assignmentAdapter.notifyDataSetChanged();
        examAdapter.notifyDataSetChanged();
    }


    /***** 종강 D-day 날짜에 따라 textview set *****/
    public void setDateView(){
        //D-day textview set
        today.setText(String.format("종강 %d월 %d일",(datecount.dcalendar.get(Calendar.MONTH)+1),datecount.dcalendar.get(Calendar.DAY_OF_MONTH)));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MainActivity main = (MainActivity)getActivity();
        if (id == R.id.setting_icon) {
            main.FragmentAdd(main.homeFragmentSetup);
            //main.FragmentAdd(main.homeFragmentSetup);
            main.homeFragment.layoutset = main.homeFragmentSetup.select;
            main.homeFragment.setListView();
            return true;
        }
        else if(id == R.id.school_map){
            if(main.getUser().name.equals("DEFAULT"))
                schoolNameDialog();
            else
                main.FragmentAdd(main.schoolMap);
            return true;
        }

        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home,menu);
    }

    public void schoolNameDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if(BAD_SCHOOL_NAME) {
            builder.setTitle("유효하지 않은 학교 이름입니다.\n다시 입력해 주세요");
            BAD_SCHOOL_NAME = false;
        }
        else builder.setTitle("학교 이름을 입력해주세요");

        // Set up the input
        final EditText input = new EditText(getContext());

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                main.getUser().name = input.getText().toString();
                main.getUserDataBox().put(main.getUser());
                main.FragmentAdd(main.schoolMap);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        builder.show();
    } //schoolnamedialog

}