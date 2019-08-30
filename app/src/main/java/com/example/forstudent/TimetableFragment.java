package com.example.forstudent;

import android.content.Context;
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

import com.example.forstudent.DataClass.Timetable;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;
import java.util.Calendar;

public class TimetableFragment extends Fragment implements View.OnClickListener{

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



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        MainActivity main = (MainActivity)getActivity();
        main.setActionBarTitle(" 시간표");
        main.centerToolbarTitle.setText("");

        init(view);
        timetable.add(main.stickers);


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
        addBtn = v.findViewById(R.id.add_btn);
        clearBtn = v.findViewById(R.id.clear_btn);
        saveBtn = v.findViewById(R.id.save_btn);
        loadBtn = v.findViewById(R.id.load_btn);
        captureBtn = v.findViewById(R.id.capture_btn);
        timetable = v.findViewById(R.id.timetable);
        if (DayofWeek() > 0 && DayofWeek() < 5){
            timetable.setHeaderHighlight(DayofWeek());}
        initView();
    }

    private void initView(){
        addBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);
        captureBtn.setOnClickListener(this);
        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<Schedule> schedules) {
                Intent i = new Intent(context, EditActivity.class);
                i.putExtra("mode",REQUEST_EDIT);
                i.putExtra("idx", idx);
                i.putExtra("schedules", schedules);
                startActivityForResult(i,REQUEST_EDIT);
            }
        });
    }

    @Override
    public void onClick(View v) {
        MainActivity main = (MainActivity)getActivity();
        switch (v.getId()){
            case R.id.add_btn:
                AddNewClass addFragment = AddNewClass.newInstance();
                main.FragmentAdd(addFragment);
                /*Intent i = new Intent(getActivity(),EditActivity.class);
                i.putExtra("mode",REQUEST_ADD);
                startActivityForResult(i,REQUEST_ADD);*/
                break;
            case R.id.clear_btn:
                timetable.removeAll();
                break;
            case R.id.save_btn:
                saveByPreference(timetable.createSaveData());
                break;
            case R.id.load_btn:
                loadSavedData();
                break;
            case R.id.capture_btn:
                main.mOnCaptureClick(timetable);
                break;
        }

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

        }
        if( id == R.id.capture_icon){

        }
        return true;
    }

    /*** make sticker ***/
    public void makeSticker(Timetable lecture){
        MainActivity main = (MainActivity)getActivity();
        for(Calendar start:lecture.getStartTime()){
            int idx = lecture.getStartTime().indexOf(start);
            Schedule schedule = new Schedule();
            schedule.setClassTitle(lecture.getClassTitle());
            schedule.setProfessorName(lecture.getProfessorName());
            schedule.setStartTime(new Time(start.get(Calendar.HOUR),start.get(Calendar.MINUTE)));
            schedule.setEndTime(new Time(lecture.getEndTime().get(idx).get(Calendar.HOUR),lecture.getEndTime().get(idx).get(Calendar.MINUTE)));
            schedule.setDay(start.get(Calendar.DAY_OF_WEEK)-2);
            schedule.setClassPlace(lecture.getClassPlace_1().get(idx));
            main.stickers.add(schedule);
        }
        timetable.add(main.stickers);

    }

}