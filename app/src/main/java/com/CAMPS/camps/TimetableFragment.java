package com.CAMPS.camps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.CAMPS.camps.BoxHelperClass.TimetableHelper;
import com.CAMPS.camps.DataClass.Timetable;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;
import java.util.Calendar;

public class TimetableFragment extends Fragment{

    private Context context;
    public static final int REQUEST_ADD = 1;
    public static final int REQUEST_EDIT = 2;

    private Button addBtn;
    private Button clearBtn;
    private Button saveBtn;
    private Button loadBtn;
    private Button captureBtn;
    public TimetableView timetable;

    //Sticker Class (NOT Schedule fragment class)

    saveData save;
    int lectureIdx=-1;
    int internalIdx=0;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        save = new saveData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity main = (MainActivity)getActivity();
        View view;
        if(isSaturday(main.timeTables))  view = inflater.inflate(R.layout.fragment_timetable_sat, container, false);
        else view = inflater.inflate(R.layout.fragment_timetable, container, false);


        main.setActionBarTitle(" 시간표");
        main.centerToolbarTitle.setText("");

        init(view);

            for (Object lecture : main.timeTables) {
                Timetable lec = (Timetable) lecture;
                makeSticker(lec);
            }
            timetable.add(main.stickers);
            main.stickers.clear();



        return view;
    }

    private int DayofWeek(){
        Calendar cal = Calendar.getInstance();
        int nWeek= cal.get(Calendar.DAY_OF_WEEK);
        int n = 0;

        if (nWeek == 2) n = 1;
        else if (nWeek == 3) n = 2;
        else if (nWeek == 4) n = 3;
        else if (nWeek == 5) n = 4;
        else if (nWeek == 6) n = 5;
        else if (nWeek == 7) n = 6;
        return n;
    }


    private void init(View v){
        this.context = getActivity();
        timetable = v.findViewById(R.id.timetable);
        if (DayofWeek() > 0 && DayofWeek() < 5){
            timetable.setHeaderHighlight(DayofWeek());}
        initView();
    }

    private void initView(){

        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<Schedule> schedules) {
                MainActivity main = (MainActivity)getActivity();
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                ArrayList<Schedule> schedule = timetable.getAllSchedulesInStickers();
                //System.out.println( idx + " : "+schedule.get(idx).getClassTitle());
                ArrayList<Timetable> time = main.timeTables;
                for(Timetable tmp: time){
                    System.out.println(tmp.classPlace);
                }
/*
                //dialog.setTitle(schedule.get(idx).getClassTitle());

                lectureIdx=-1;
                internalIdx=0;
                for (int i =0;i<main.timeTables.size();i++){
                    lectureIdx += ((Timetable)main.timeTables.get(i)).startTime.size();
                    if(lectureIdx>=idx){
                        lectureIdx = i;
                        internalIdx=((Timetable)main.timeTables.get(i)).startTime.size()-2-(lectureIdx-idx);
                        if(i==0) internalIdx++;
                        if(i==main.timeTables.size()-1) internalIdx--;
                        break;
                    }
                }
                System.out.println("IDX:"+idx+"lec:"+lectureIdx);*/
                Timetable timetable_tmp = (Timetable) main.timeTables.get(idx);
                //System.out.println(timetable_tmp.classPlace.get(internalIdx));
/*
                for(int i =0;i<main.timeTables.size();i++){
                    Timetable timetable_temp= (Timetable) main.timeTables.get(i);
                    System.out.println(i+" : "+timetable_temp.classPlace);
                }*/
                dialog.setTitle(timetable_tmp.getClassTitle());
                String[] menu = {"수정", "삭제"};
                dialog.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0: //modify
                                modifyLecture(idx);
                                break;
                            case 1: //remove

                                AlertDialog.Builder removeCheck = new AlertDialog.Builder(getContext());
                                removeCheck.setMessage("삭제하시겠습니까?");
                                removeCheck.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        main.timeTables.remove(idx);
                                        addTransparent();
                                    }
                                });
                                removeCheck.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                removeCheck.create().show();
                                break;
                        }
                    }
                }).create().show();


                /*
                Intent i = new Intent(context, EditActivity.class);

                i.putExtra("mode",REQUEST_EDIT);
                i.putExtra("idx", idx);
                i.putExtra("schedules", schedules);
                startActivityForResult(i,REQUEST_EDIT);*/
            }
        });
    }


    public void addTransparent(){
        TransparentFragment addFragment = TransparentFragment.newInstance();
        MainActivity main = (MainActivity)getActivity();
        main.FragmentAdd(addFragment);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case REQUEST_ADD:
                if(resultCode == EditActivity.RESULT_OK_ADD){
                    ArrayList<Schedule> item = (ArrayList<Schedule>)data.getSerializableExtra("schedules");
                    timetable.add(item);
                }
                break;
            case REQUEST_EDIT:
                /* Edit -> Submit */
                if(resultCode == EditActivity.RESULT_OK_EDIT){
                    int idx = data.getIntExtra("idx",-1);
                    ArrayList<Schedule> item = (ArrayList<Schedule>)data.getSerializableExtra("schedules");
                    timetable.edit(idx,item);

                }
                /* Edit -> Delete */
                else if(resultCode == EditActivity.RESULT_OK_DELETE){
                    int idx = data.getIntExtra("idx",-1);
                    timetable.remove(idx);
                }
                break;
        }
    }


    private void saveByPreference(String data){
        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("timetable_demo",data);
        editor.commit();
        Toast.makeText(getActivity(),"저장 완료",Toast.LENGTH_SHORT).show();
    }

    private void loadSavedData(){
        timetable.removeAll();
        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String savedData = mPref.getString("timetable_demo","");
        if(savedData == null && savedData.equals("")) return;
        timetable.load(savedData);
        Toast.makeText(getActivity(),"불러오기 완료",Toast.LENGTH_SHORT).show();
    }

    /***** toolbar *****/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.timetable_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MainActivity main = (MainActivity)getActivity();
        if (id == R.id.add_icon) {
            AddNewClass addFragment = AddNewClass.newInstance();
            main.FragmentAdd(addFragment);
        }
        if( id == R.id.capture_icon){
            main.mOnCaptureClick(timetable);
        }
        return true;
    }
    public TimetableFragment getInstance(){
        return this;
    }
    /*** check sticker overlap ***/
    public boolean checkOverlap(Schedule target){
        ArrayList<Schedule> stickers = timetable.getAllSchedulesInStickers();
        int tar_start_hour = target.getStartTime().getHour();
        int tar_start_minute = target.getStartTime().getMinute();
        int tar_start = tar_start_hour*60+tar_start_minute;
        int tar_end_hour = target.getEndTime().getHour();
        int tar_end_minute = target.getEndTime().getMinute();
        int tar_end = tar_end_hour*60+tar_end_minute;
        int tar_day_of_week = target.getDay();
        for(Schedule tmp:stickers){
            int ori_start_hour = tmp.getStartTime().getHour();
            int ori_start_minute = tmp.getStartTime().getMinute();
            int ori_start = ori_start_hour*60+ori_start_minute;
            int ori_end_hour = tmp.getEndTime().getHour();
            int ori_end_minute = tmp.getEndTime().getMinute();
            int ori_end = ori_end_hour*60+ori_end_minute;
            int ori_day_of_week = tmp.getDay();
            if(tar_day_of_week == ori_day_of_week){
                if((ori_end > tar_start && ori_start < tar_start) || (ori_end > tar_end && ori_start < tar_end) || (ori_end == tar_end) || (ori_start == tar_start)){
                    return true;
                }
            }

        }
        return false;
    }

    /*** make sticker ***/
    public void makeSticker(Timetable lecture){
        MainActivity main = (MainActivity)getActivity();
        for(Calendar start:lecture.getStartTime()){
            int idx = lecture.getStartTime().indexOf(start);
            Schedule schedule = new Schedule();
            schedule.setClassTitle(lecture.getClassTitle());
            schedule.setProfessorName(lecture.getProfessorName());
            schedule.setStartTime(new Time(start.get(Calendar.HOUR_OF_DAY),start.get(Calendar.MINUTE)));
            schedule.setEndTime(new Time(lecture.getEndTime().get(idx).get(Calendar.HOUR_OF_DAY),lecture.getEndTime().get(idx).get(Calendar.MINUTE)));
            schedule.setDay(start.get(Calendar.DAY_OF_WEEK)-2);
            schedule.setClassPlace(lecture.getClassPlace_1().get(idx));

            if(!checkOverlap(schedule)) {
                main.stickers.add(schedule);

            }

        }

        timetable.add(main.stickers);
        main.stickers.clear();
    }
    @Override
    public void onStop() {
        super.onStop();
        MainActivity main = (MainActivity)getActivity();
        save.run();
    }




    private class saveData extends Thread{
        public saveData(){

        }

        public void run(){
            try{
                MainActivity main = (MainActivity)getActivity();
                main.getTimetableBox().removeAll();
                int count =1 ;
                for(int i=0; i<main.timeTables.size(); i++){
                    Timetable lecture = (Timetable)main.timeTables.get(i);

                        TimetableHelper helper = new TimetableHelper(0,lecture.getClassTitle(),lecture.getProfessorName(),lecture.classPlace,lecture.startTime,lecture.endTime);
                        TimetableHelper.putLecture(helper);
                        count ++;


                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public void modifyLecture(int lectureIdx){
        MainActivity main = (MainActivity)getActivity();
        AddNewClass addFragment = AddNewClass.newInstance();

        Timetable target = (Timetable) main.timeTables.get(lectureIdx);
        addFragment.lectureRooms = target.classPlace;
        addFragment.startTimes = target.startTime;
        addFragment.endTimes = target.endTime;
        addFragment.Professor = target.getProfessorName();
        addFragment.Lecture = target.getClassTitle();
        addFragment.MOD=true;
        main.FragmentAdd(addFragment);

        //

    }
    public boolean isSaturday(ArrayList<Timetable> timetables){
        for(Timetable timetable:timetables){
            for(Calendar date:timetable.startTime){
                if(date.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) return true;
            }
        }
        return false;
    }

}