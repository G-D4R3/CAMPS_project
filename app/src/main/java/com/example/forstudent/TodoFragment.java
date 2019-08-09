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

import com.example.forstudent.BoxHelperClass.AssignmentHelper;
import com.example.forstudent.DataClass.Assignment;
import com.example.forstudent.ListViewAdapter.TodoListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class TodoFragment extends Fragment {
    public ArrayList<Assignment> AssList = new ArrayList<Assignment>();
    public ArrayList<Assignment> ImpList = new ArrayList<Assignment>();
    TodoListAdapter adapter;
    TodoListAdapter ImportantAdapter;
    TextView mTitle;
    ListView mlistView;
    ListView mImportant;
    TextView mAssSec;
    TextView mImpSec;
    TextView mIhide;
    TextView mAhide;
    String title = "남은 과제 수";
    Boolean mIvisible=true;
    Boolean mDvisible=true;
    long boxSize;
    protected static boolean MOD = false;
    static int mod_index;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_todo, container, false);
        MainActivity main = (MainActivity)getActivity();
        main.setActionBarTitle("과제");
        mTitle = (TextView)view.findViewById(R.id.restDo);
        mlistView = (ListView)view.findViewById(R.id.assignmentList);
        mImportant = (ListView)view.findViewById(R.id.importantAssignment);
        ImageButton mAdd = (ImageButton)view.findViewById(R.id.addAss);
        mImpSec = (TextView)view.findViewById(R.id.SectionHeader2);
        mAssSec = (TextView)view.findViewById(R.id.SectionHeader3);
        mIhide = (TextView)view.findViewById(R.id.hide2);
        mAhide = (TextView)view.findViewById(R.id.hide3);

        loadData();

        mTitle.setText(title);

        adapter = new TodoListAdapter(AssList);
        mlistView.setAdapter(adapter);

        ImportantAdapter = new TodoListAdapter(ImpList);
        mImportant.setAdapter(ImportantAdapter);

        ListViewSetter setter = new ListViewSetter();
        setter.setListViewHeight(mlistView);
        setter.setListViewHeight(mImportant);




        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewAss();
            }
        });

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int pos = position;
                try{
                    String name = adapter.data.get(position).getName();
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

        mImportant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("해제");
                dialog.setMessage("중요도 표시를 해제합니다.");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0; i<AssList.size(); i++){
                            if(AssList.get(i).equals(ImpList.get(position))){
                                AssList.get(i).setFlag(false);
                            }
                        }
                        ImpList.remove(ImportantAdapter.data.get(position));
                        ImportantAdapter.notifyDataSetChanged();
                        if(ImpList.size()==0){
                            mImportant.setVisibility(View.GONE);
                        }
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
    public void onStart() {
        super.onStart();
        Collections.sort(AssList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity main = (MainActivity)getActivity();
        main.getAssignmentBox().removeAll();
        if(AssList.size()!=0){
            for(int i=0; i<AssList.size(); i++){
                AssignmentHelper helper = new AssignmentHelper((long)i+1, AssList.get(i).getName(), AssList.get(i).getPeriod(), AssList.get(i).getMemo(),AssList.get(i).getFlag());
                AssignmentHelper.putAssignment(helper);
            }
        }
    }

    public void AddNewAss(){


        MainActivity main = (MainActivity)getActivity();
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

        MainActivity main = (MainActivity)getActivity();
        AddNewAssignment fragment = AddNewAssignment.newInstance();
        fragment.setAss(a);
        fragment.MOD=true;
        fragment.Name=a.getName();
        fragment.range = a.getMemo();
        fragment.Date = String.format((a.getPeriod().get(Calendar.MONTH)+1)+"월 "+a.getPeriod().get(Calendar.DAY_OF_MONTH)+"일");
        main.FragmentAdd(fragment);
        adapter.notifyDataSetChanged();

    }


    public void setImportance(Assignment a){
        a.setFlag(true);
        ImpList.add(a);
        ImportantAdapter.notifyDataSetChanged();
    }

    public void loadData(){
        MainActivity main = (MainActivity)getActivity();

        AssList = main.assignment;
        ImpList = main.important;
        Collections.sort(AssList);
        Collections.sort(ImpList);

        title = String.format("남은 과제 : %d", AssList.size());
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
}