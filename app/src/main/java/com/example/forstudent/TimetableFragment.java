package com.example.forstudent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.tlaabs.timetableview.Schedule;
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

    private TimetableView timetable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        MainActivity main = (MainActivity)getActivity();
        main.setActionBarTitle("");
        main.centerToolbarTitle.setText("시간표");

        init(view);
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

        timetable = v.findViewById(R.id.timetable);
        if (DayofWeek() > 0 ){
        timetable.setHeaderHighlight(DayofWeek());}
        initView();
    }

    private void initView(){
        addBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);

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
        switch (v.getId()){
            case R.id.add_btn:
                Intent i = new Intent(getActivity(),EditActivity.class);
                i.putExtra("mode",REQUEST_ADD);
                startActivityForResult(i,REQUEST_ADD);
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

}