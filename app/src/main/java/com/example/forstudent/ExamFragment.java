package com.example.forstudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    /*** main ***/
    MainActivity main;

    /*** tmp ***/
    String titleDday = "D-day";
    DateCount count;

    /*** view ***/
    View view;
    ListView mlistView = null;
    ExamListAdapter adapter;
    ImageButton addSubject;
    TextView dday;
    ListViewSetter setter;

    /*** storage ***/
    ArrayList<TestSub> ExamList=new ArrayList<>();

    /*** thread ***/
    loadData load;
    saveData save;


    /***** instanciate *****/
    public static ExamFragment newInstance() {
        return new ExamFragment();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = (MainActivity)getActivity();
        load = new loadData();
        save = new saveData();
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_exam,menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /***** view load *****/
        view = (View) inflater.inflate(R.layout.fragment_exam, container, false);
        dday = (TextView)view.findViewById(R.id.examTitle);
        mlistView = (ListView)view.findViewById(R.id.examlistView);
        count = new DateCount();
        setter = new ListViewSetter();


        load.run();


        /***** toolbar *****/
        main.setActionBarTitle("");
        main.centerToolbarTitle.setText("시험");
        main.invalidateOptionsMenu();


        /***** listview item 클릭 이벤트 *****/
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        main.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final int pos = position;
                                String name = adapter.data.get(position).Name;
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
                                                        main.alarmDelete(adapter.data.get(pos));
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
                    }
                }).start();
            }
        }); //mlistview.setOnClickListener();



        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        save.run();
    }


    public void addNewsub(){ //nullcheck 필요? rangd null일 수 있음
        addNewExamSub add = addNewExamSub.newInstance();
        main.FragmentAdd(add);

        if (ExamList.size() > 0) {
            DateSet();
        }
        Collections.sort(ExamList);
        adapter.notifyDataSetChanged();
    }

    public void removeSub(TestSub sub){
        ExamList.remove(sub);
        Collections.sort(ExamList);

        if(ExamList.isEmpty()==true){
            titleDday="시험이 없습니다.";
        }
        else{
            DateSet(); //가장 빠른 시험 D-day 계산
        }

        adapter.notifyDataSetChanged();
    }

    public void modifySub(TestSub sub){
        addNewExamSub mod = addNewExamSub.newInstance();
        mod.subject = sub;
        mod.mYear = sub.TestDate.get(Calendar.YEAR);
        mod.mMonth = sub.TestDate.get(Calendar.MONTH);
        mod.mDay = sub.TestDate.get(Calendar.DAY_OF_MONTH);
        mod.place = sub.Place;
        mod.mSHour = sub.startHour;
        mod.mSMinute = sub.startMinute;
        mod.mEHour = sub.endHour;
        mod.mEMinute = sub.endMinute;
        mod.MOD = true;
        mod.range = sub.range;

        main.FragmentAdd(mod);

        Collections.sort(ExamList);
        DateSet();

        adapter.notifyDataSetChanged();

    }

    public void DateSet(){
        if(ExamList.size()<=0){
            titleDday = "시험이 없습니다.";
        }
        else{
            boolean flag = true; //모두 날짜가 지난 시험인지 체크
            for(TestSub tmp : ExamList){
                count.dcalendar = tmp.TestDate;
                count.calcDday();
                if(count.result>=0){ //나중에 D-day일 경우 시간 계산 추가해야함
                    flag = false;
                    if(count.result == 0){
                        titleDday = String.format("%s D-Day", tmp.Name);
                    }
                    else{
                        titleDday = String.format("%s D-%d", tmp.Name, count.result);
                    }
                    break;
                }
            }
            if(flag==true){
                titleDday = "시험이 없습니다.";
            }
        }
    }


    private class saveData extends Thread{
        public saveData(){

        }

        public void  run(){
            try{
                main.getTestSubBox().removeAll();
                int i=0;
                for(TestSub tmp  : ExamList){
                    TestSubHelper helper = new TestSubHelper((long)i+1, tmp.Name, tmp.TestDate, tmp.Place, tmp.startHour, tmp.startMinute, tmp.endHour, tmp.endMinute, tmp.range);
                    TestSubHelper.putTestSub(helper);
                    i++;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class loadData extends Thread{
        public loadData(){

        }

        public void run(){
            try{
                ExamList = main.testSub;
                adapter = new ExamListAdapter(ExamList);
                mlistView.setAdapter(adapter);
                DateSet();
                dday.setText(titleDday);
                Collections.sort(ExamList);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_exam:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        main.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
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
                    }
                }).start();
                break;
            case R.id.cal_Grade:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        main.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                CalcGradeFragment add = CalcGradeFragment.newInstance();
                                main.FragmentAdd(add);
                            }
                        });
                    }
                }).start();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}