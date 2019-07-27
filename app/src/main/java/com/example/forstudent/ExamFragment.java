package com.example.forstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forstudent.DataClass.TestSub;

import java.util.ArrayList;
import java.util.Collections;

public class ExamFragment extends Fragment{

    ArrayList<TestSub> ExamList=new ArrayList<TestSub>();
    public String titleDday="D-day";
    ListView mlistView = null;
    ExamListAdapter adapter;
    private Button addSubject;
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
        addSubject = (Button) view.findViewById(R.id.addSubject);
        dday = (TextView)view.findViewById(R.id.examTitle);

        count = new DateCount();

        //listvieww
        adapter = new ExamListAdapter(ExamList);
        mlistView = (ListView)view.findViewById(R.id.examlistView);
        mlistView.setAdapter(adapter);

        dday.setText(titleDday);




        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final BasicDialog dialog = new BasicDialog();
                dialog.show(getFragmentManager(),"BasicDialog");

                View.OnClickListener mCallTable = new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Toast toast = Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT);
                        toast.show();
                        //timetable 변수 가져와서 해야함
                        dialog.dismiss();
                    }
                };
                View.OnClickListener mSetDirectly = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        addNewExamSub add = addNewExamSub.newInstance();
                        MainActivity main = (MainActivity)getActivity();
                        main.FragmentAdd(add);

                    }
                };
                dialog.setListener(mCallTable, mSetDirectly);
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

    public void addNewsub(TestSub sub){ //nullcheck 필요? rangd null일 수 있음
        ExamList.add(sub);
        Collections.sort(ExamList);
        adapter.notifyDataSetChanged();
        count.dcalendar.set(sub.getYear(),sub.getMonth(),sub.getDay());
        int result = count.calcDday();
        titleDday=String.format("%s D-%d",ExamList.get(0).getName(),result);
    }

    public void removeSub(TestSub sub){
        ExamList.remove(sub);
        Collections.sort(ExamList);
        if(ExamList.isEmpty()==true){
            titleDday="D-day";
        }
        else{
            sub = ExamList.get(0);
            count.dcalendar.set(sub.getYear(),sub.getMonth(),sub.getDay());
            int result = count.calcDday();
            titleDday=String.format("%s D-%d",ExamList.get(0).getName(),result);
        }
        adapter.notifyDataSetChanged();
    }

    public void modifySub(TestSub sub){
        TestSub temp=null;
        if(sub.getRange().length()==0){
            temp = new TestSub(sub.getName(),sub.getYear(),sub.getMonth(),sub.getDay(),sub.getStartHour(),sub.getStartMinute(),sub.getEndHour(),sub.getEndMinute());
        }
        else{
            temp = new TestSub(sub.getName(),sub.getYear(),sub.getMonth(),sub.getDay(),sub.getStartHour(),sub.getStartMinute(),sub.getEndHour(),sub.getEndMinute(),sub.getRange());
        }
        addNewExamSub mod = addNewExamSub.newInstance();
        MainActivity main = (MainActivity)getActivity();
        removeSub(sub);
        mod.subject = temp;
        mod.mYear = temp.getYear();
        mod.mMonth = temp.getMonth();
        mod.mDay = temp.getDay();
        mod.mSHour = temp.getStartHour();
        mod.mSMinute = temp.getStartMinute();
        mod.mEHour = temp.getEndHour();
        mod.mEMinute = temp.getEndMinute();
        mod.MOD = true;
        main.FragmentAdd(mod);
        adapter.notifyDataSetChanged();
    }







}