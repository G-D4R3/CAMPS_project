package com.example.forstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.BoxHelperClass.TestSubHelper;
import com.example.forstudent.DataClass.TestSub;
import com.example.forstudent.ListViewAdapter.ExamListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ExamFragment extends Fragment{

    ArrayList<TestSub> ExamList=new ArrayList<TestSub>();
    public String titleDday="D-day";
    ListView mlistView = null;
    ExamListAdapter adapter;
    private ImageButton addSubject;
    View view;
    DateCount count;
    public TextView dday;

    public static ExamFragment newInstance() {
        return new ExamFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment_exam, container, false);
        addSubject = (ImageButton) view.findViewById(R.id.addSubject);
        dday = (TextView)view.findViewById(R.id.examTitle);

        count = new DateCount();

        Collections.sort(ExamList);
        MainActivity main = (MainActivity)getActivity();
        ExamList = main.testSub;
        main.setActionBarTitle("시험");
        //listvieww
        adapter = new ExamListAdapter(ExamList);
        mlistView = (ListView)view.findViewById(R.id.examlistView);
        mlistView.setAdapter(adapter);

        DateSet();
        dday.setText(titleDday);




        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("과목 추가");
                dialog.setMessage("시험 추가");
                dialog.setNegativeButton("불러오기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("직접추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        addNewsub();
                    }
                });
                dialog.create();
                dialog.show();
            }
        });
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                String name = adapter.data.get(position).getName();
                String[] menu = {"수정", "삭제"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(name);
                dialog.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Toast toast = Toast.makeText(getContext(), "수정", Toast.LENGTH_SHORT);
                                toast.show();
                                modifySub(adapter.data.get(pos));
                                dialog.dismiss();
                                break;
                            case 1:
                                AlertDialog.Builder remove = new AlertDialog.Builder(getContext());
                                remove.setTitle("삭제");
                                remove.setMessage("과목을 삭제 합니다.");
                                remove.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        removeSub(adapter.data.get(pos));
                                        dday.setText(titleDday);
                                        dialog.dismiss();
                                    }
                                });
                                remove.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();

                                    }
                                });
                                remove.show();
                                break;

                        }
                    }
                });
                dialog.create();
                dialog.show();

            }
        });



        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity main = (MainActivity)getActivity();
        main.getTestSubBox().removeAll();
        for(int i=0; i<ExamList.size(); i++){
            TestSubHelper helper = new TestSubHelper((long)i+1, ExamList.get(i).getName(), ExamList.get(i).getTestDate(), ExamList.get(i).getStartHour(), ExamList.get(i).getStartMinute(), ExamList.get(i).getEndHour(), ExamList.get(i).getEndMinute(), ExamList.get(i).getRange());
            TestSubHelper.putTestSub(helper);
        }
    }

    public void addNewsub(){ //nullcheck 필요? rangd null일 수 있음

        addNewExamSub add = addNewExamSub.newInstance();
        MainActivity main = (MainActivity)getActivity();
        main.FragmentAdd(add);
        Collections.sort(ExamList);

        if (ExamList.size() > 0) {
            DateSet();
        }
    }

    public void removeSub(TestSub sub){
        ExamList.remove(sub);
        Collections.sort(ExamList);
        if(ExamList.isEmpty()==true){
            titleDday="D-day";
        }
        else{
            DateSet();
        }
        adapter.notifyDataSetChanged();
    }

    public void modifySub(TestSub sub){

        addNewExamSub mod = addNewExamSub.newInstance();
        MainActivity main = (MainActivity)getActivity();
        mod.subject = sub;
        mod.mYear = sub.getTestDate().get(Calendar.YEAR);
        mod.mMonth = sub.getTestDate().get(Calendar.MONTH);
        mod.mDay = sub.getTestDate().get(Calendar.DAY_OF_MONTH);
        mod.mSHour = sub.getStartHour();
        mod.mSMinute = sub.getStartMinute();
        mod.mEHour = sub.getEndHour();
        mod.mEMinute = sub.getEndMinute();
        mod.MOD = true;
        mod.range = sub.getRange();
        main.FragmentAdd(mod);
        Collections.sort(ExamList);

        adapter.notifyDataSetChanged();
        DateSet();
    }

    public void DateSet(){
        if(ExamList.size()<=0){
            titleDday = "디데이";
        }
        else{
            TestSub e=null;
            boolean flag = true; //모두 날짜가 지난 시험인지 체크
            for(int i=0; i<ExamList.size(); i++){
                e = ExamList.get(i);
                count.dcalendar = e.getTestDate();
                count.calcDday();
                if(count.result>=0){ //나중에 D-day일 경우 시간 계산 추가해야함
                    flag = false;
                    break;

                }
            }
            if(flag==false){
                if(count.result == 0){
                    titleDday = String.format("%s D-Day", e.getName());
                }
                else{
                    titleDday = String.format("%s D-%d", e.getName(), count.result);
                }
            }
            else{
                titleDday = "모든 시험이 끝났습니다.";
            }
        }
    }





}