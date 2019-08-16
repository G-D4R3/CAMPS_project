package com.example.forstudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

import com.example.forstudent.BoxHelperClass.AssignmentHelper;
import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.ListViewAdapter.TodoListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class TodoFragment extends Fragment {
    MainActivity main;
    Handler handler;
    public ArrayList<Assignment> AssList = new ArrayList<Assignment>();
    public ArrayList<Assignment> ImpList;
    TodoListAdapter adapter;
    TodoListAdapter ImportantAdapter;
    TextView mTitle;
    public ListView mlistView;
    public ListView mImportant;
    TextView mAssSec;
    TextView mImpSec;
    TextView mIhide;
    TextView mAhide;
    String title = "남은 과제 수";
    ImageButton  mAdd;
    Boolean mIvisible=true;
    Boolean mDvisible=true;
    View view;
    long boxSize;
    protected static boolean MOD = false;
    static int mod_index;
    public ListViewSetter setter = new ListViewSetter();
    ArrayList<String> toolbarButtonStatus = new ArrayList<>();

    loadData load;
    saveData save;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = (MainActivity)getActivity();
        load = new loadData();
        load.start();
        save = new saveData();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_todo, container, false);
        main.setActionBarTitle("과제");
        mTitle = (TextView)view.findViewById(R.id.restDo);
        mlistView = (ListView)view.findViewById(R.id.assignmentList);
        mImportant = (ListView)view.findViewById(R.id.importantAssignment);
        mAdd = (ImageButton)view.findViewById(R.id.addAss);
        mImpSec = (TextView)view.findViewById(R.id.SectionHeader2);
        mAssSec = (TextView)view.findViewById(R.id.SectionHeader3);
        mIhide = (TextView)view.findViewById(R.id.hide2);
        mAhide = (TextView)view.findViewById(R.id.hide3);
        adapter = new TodoListAdapter(AssList);
        ImportantAdapter = new TodoListAdapter(ImpList);





        handler = new Handler();


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


        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

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
                                    String name = adapter.data.get(position).Name;
                                    String[] menu = {"중요도 표시","수정", "삭제"};


                                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                                    dialog.setTitle(name);
                                    dialog.setItems(menu, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case 0:
                                                    Toast toast = Toast.makeText(getContext(),"중요도를 설정합니다.", Toast.LENGTH_SHORT);
                                                    mImportant.setVisibility(View.VISIBLE);
                                                    setImportance(adapter.data.get(pos));
                                                    setter.setListViewHeight(mImportant);
                                                    break;
                                                case 1:
                                                    dialog.dismiss();
                                                    ModifyAss(adapter.data.get(pos));
                                                    setView();
                                                    break;
                                                case 2:
                                                    AlertDialog.Builder remove = new AlertDialog.Builder(getContext());
                                                    remove.setTitle("삭제");
                                                    remove.setMessage("할 일을 삭제 합니다.");
                                                    remove.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            RemoveAss(adapter.data.get(pos));
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
        });

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
                                        ImpList.remove(ImportantAdapter.data.get(position));
                                        ImportantAdapter.notifyDataSetChanged();
                                        if(ImpList.size()==0){
                                            mImportant.setVisibility(View.GONE);
                                        }
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
        });

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
        if(AssList.size()==0){
            title = "남은 과제가 없습니다.";
        }
        else {
            title = String.format("남은 과제 : %d", AssList.size());
        }
        adapter.notifyDataSetChanged();
    }

    public void ModifyAss(Assignment a){
        AddNewAssignment fragment = AddNewAssignment.newInstance();
        fragment.ass = a;
        fragment.MOD=true;
        fragment.Name=a.Name;
        fragment.range = a.Memo;
        fragment.Date = String.format((a.Period.get(Calendar.MONTH)+1)+"월 "+a.Period.get(Calendar.DAY_OF_MONTH)+"일");
        main.FragmentAdd(fragment);
        adapter.notifyDataSetChanged();

    }


    public void setImportance(Assignment a){
        a.flag =true;
        ImpList.add(a);
        ImportantAdapter.notifyDataSetChanged();
        mImportant.setVisibility(View.VISIBLE);
    }


    public void setView(){
        title = String.format("남은 과제 : %d", AssList.size());
        mTitle.setText(title);
        if(ImpList.size()>0){
            mImportant.setVisibility(View.VISIBLE);
        }
        else{
            mImportant.setVisibility(View.GONE);
        }
    }


    private class saveData extends Thread{
        public saveData(){

        }

        public void run(){
            try{
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

                for(Assignment tmp :AssList) {
                    if (tmp.flag == true) {
                        ImpList.add(tmp);
                    }
                }

                if(AssList.size()==0){
                    title = "남은 과제가 없습니다.";
                }
                else {
                    title = String.format("남은 과제 : %d", AssList.size());
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        main.centerToolbarTitle.setText("");

    }
}