package com.example.forstudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.BoxHelperClass.AssignmentHelper;
import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.ListViewAdapter.TodoListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class TodoFragment extends Fragment {
    /*** main ***/
    MainActivity main;

    /*** tmp ***/
    String title = "남은 과제 수";

    /*** view ***/
    View view;
    public ListView mlistView;
    public ListView mImportant;
    TodoListAdapter adapter;
    TodoListAdapter ImportantAdapter;
    TextView mTitle;
    TextView mAssSec; //section header
    TextView mImpSec;
    TextView mIhide; //더보기 줄이기
    TextView mAhide;
    public ListViewSetter setter = new ListViewSetter();

    /*** flag ***/
    Boolean mIvisible=true; //중요 표시 과제
    Boolean mDvisible=true; //과제

    /*** storage ***/
    public ArrayList<Assignment> AssList = new ArrayList<Assignment>();
    public ArrayList<Assignment> ImpList;
    ArrayList<String> toolbarButtonStatus = new ArrayList<>();

    /*** thread ***/
    loadData load;
    saveData save;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = (MainActivity)getActivity();
        load = new loadData();
        load.start();
        save = new saveData();
        setHasOptionsMenu(true);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_assignment,menu);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /***** view load *****/
        view  = inflater.inflate(R.layout.fragment_todo, container, false);
        mTitle = (TextView)view.findViewById(R.id.restDo);
        mlistView = (ListView)view.findViewById(R.id.assignmentList);
        mImportant = (ListView)view.findViewById(R.id.importantAssignment);
        mImpSec = (TextView)view.findViewById(R.id.SectionHeader2);
        mAssSec = (TextView)view.findViewById(R.id.SectionHeader3);
        mIhide = (TextView)view.findViewById(R.id.hide2);
        mAhide = (TextView)view.findViewById(R.id.hide3);
        adapter = new TodoListAdapter(AssList);
        ImportantAdapter = new TodoListAdapter(ImpList);
        Handler handler = new Handler();

        load.run();
        adapter.notifyDataSetChanged();
        ImportantAdapter.notifyDataSetChanged();

        /***** toolbar *****/
        main.setActionBarTitle(" 과제");
        main.centerToolbarTitle.setText("");
        main.invalidateOptionsMenu();

        /***** listview adapter set *****/
        Thread setView = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("TodoFragment", "Set View");

                        mTitle.setText(title);

                        mlistView.setAdapter(adapter);
                        mImportant.setAdapter(ImportantAdapter);

                        setter.setListViewHeight(mlistView);
                        setter.setListViewHeight(mImportant);
                    }
                });
            }
        });
        setView.start();


        /***** listview item 클릭 이벤트 *****/
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        main.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("TodoFragment", "Item Clicked");
                                try{
                                    String name = AssList.get(position).Name;
                                    String[] menu = {"중요도 표시","수정", "삭제"};

                                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                                    dialog.setTitle(name);
                                    dialog.setItems(menu, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case 0:
                                                    Toast toast = Toast.makeText(getContext(),"중요도를 설정합니다.", Toast.LENGTH_SHORT);
                                                    setImportance(AssList.get(pos));
                                                    setter.setListViewHeight(mImportant);
                                                    break;
                                                case 1:
                                                    dialog.dismiss();
                                                    ModifyAss(AssList.get(pos));
                                                    setView();
                                                    break;
                                                case 2:
                                                    AlertDialog.Builder remove = new AlertDialog.Builder(getContext());
                                                    remove.setTitle("삭제");
                                                    remove.setMessage("할 일을 삭제 합니다.");
                                                    remove.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            main.alarmDelete(AssList.get(pos));
                                                            RemoveAss(AssList.get(pos));
                                                            mTitle.setText(title);
                                                            dialog.dismiss();
                                                            setView();
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
                                catch (NullPointerException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).start();

            }
        }); //mlistview.setOnItemClickListener


        /***** 중요 과목 listview item 클릭 이벤트 *****/
        mImportant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        main.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("TodoFragment", "Remove important");
                                final int pos = position;
                                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                                dialog.setTitle("해제");
                                dialog.setMessage("중요도 표시를 해제합니다.");
                                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        for(int i=0; i<AssList.size(); i++){
                                            if(AssList.get(i).equals(ImpList.get(position))){
                                                AssList.get(i).flag=false;
                                            }
                                        }
                                        ImpList.remove(ImpList.get(position));
                                        ImportantAdapter.notifyDataSetChanged();
                                        setter.setListViewHeight(mImportant);
                                    }
                                });
                                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.create();
                                dialog.show();
                            }
                        });
                    }
                }).start();

            }
        }); //mImportant.setOnItemClickListener


        /***** 더보기 줄이기 *****/
        mIhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIvisible==true){
                    mImportant.setVisibility(View.GONE);
                    mIvisible=false;
                    mIhide.setText("더보기");
                }
               else{
                    mImportant.setVisibility(View.VISIBLE);
                    mIvisible=true;
                    mIhide.setText("줄이기");
                }
            }
        });

        mAhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDvisible==true){
                    mlistView.setVisibility(View.GONE);
                    mDvisible=false;
                    mAhide.setText("더보기");
                }
                else{
                    mlistView.setVisibility(View.VISIBLE);
                    mDvisible=true;
                    mAhide.setText("줄이기");
                }
            }
        });



        return view;
    }



    @Override
    public void onStop() {
        super.onStop();
        save.run();
    }

    public void AddNewAss(){
        AddNewAssignment fragment = AddNewAssignment.newInstance();
        main.FragmentAdd(fragment);

        Collections.sort(AssList);
        adapter.notifyDataSetChanged();

        title = String.format("남은 과제 : %d",AssList.size());
    }

    public void RemoveAss(Assignment a){
        AssList.remove(a);
        ImpList.remove(a);
        Collections.sort(AssList);
        Collections.sort(ImpList);
        if(AssList.size()==0){
            title = "남은 과제가 없습니다.";
        }
        else {
            title = String.format("남은 과제 : %d", AssList.size());
        }
        if(ImpList.size()==0){
            mImportant.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
        ImportantAdapter.notifyDataSetChanged();
    }

    public void ModifyAss(Assignment a){
        AddNewAssignment fragment = AddNewAssignment.newInstance();
        fragment.ass = a;
        fragment.MOD=true;
        fragment.Name=a.Name;
        fragment.range = a.Memo;
        fragment.period = a.getPeriod();
        fragment.Date = String.format((a.Period.get(Calendar.MONTH)+1)+"월 "+a.Period.get(Calendar.DAY_OF_MONTH)+"일");
        main.FragmentAdd(fragment);
        adapter.notifyDataSetChanged();

    }


    public void setImportance(Assignment a){
        a.flag =true; //flag = 중요표시
        ImpList.add(a);
        Collections.sort(ImpList);
        mImportant.setVisibility(View.VISIBLE);
        ImportantAdapter.notifyDataSetChanged();
    }


    /***** title textview set *****/
    public void setView(){
        title = String.format("남은 과제 : %d", AssList.size());
        mTitle.setText(title);
        if(ImpList.size()>0){
            mImportant.setVisibility(View.VISIBLE);
        }
        else{
            mImportant.setVisibility(View.GONE);
        }
        if(AssList.size()==0){
            title = "남은 과제가 없습니다.";
        }
        else {
            title = String.format("남은 과제 : %d", AssList.size());
        }
    }


    private class saveData extends Thread{
        public saveData(){

        }

        public void run(){
            try{
                Collections.sort(AssList);
                Collections.sort(ImpList);
                Log.v("TodoFragment", "Saved Data");
                main.getAssignmentBox().removeAll();
                int i=0;
                if(AssList.size()!=0){
                    for(Assignment tmp : AssList){
                        AssignmentHelper helper = new AssignmentHelper((long)i+1, tmp.Name, tmp.Period, tmp.Memo, tmp.flag);

                        AssignmentHelper.putAssignment(helper);
                        i++;
                    }
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
                Log.v("TodoFragment", "Loaded Data");

                AssList = main.assignment;
                ImpList = new ArrayList<>();

                //중요한 과목은 Implist에 추가
                for(Assignment tmp :AssList) {
                    if (tmp.flag == true) {
                        ImpList.add(tmp);
                    }
                }

                //textview set
                if(AssList.size()==0){
                    title = "남은 과제가 없습니다.";
                }
                else {
                    title = String.format("남은 과제 : %d", AssList.size());
                }
                Collections.sort(AssList);
                Collections.sort(ImpList);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_assignment:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("TodoFragment", "Add Assignment");
                        main.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AddNewAss();
                            }
                        });
                    }
                }).start();

        }





        return super.onOptionsItemSelected(item);
    }
}