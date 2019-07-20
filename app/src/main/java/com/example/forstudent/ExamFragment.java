package com.example.forstudent;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ExamFragment extends Fragment{

    ArrayList<TestSub> ExamList=new ArrayList<TestSub>();

    ListView mlistView = null;
    ExamListAdapter adapter;
    private Button addSubject;
    View view;

    public static ExamFragment newInstance() {
        return new ExamFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment_exam, container, false);
        addSubject = (Button) view.findViewById(R.id.addSubject);

        //listvieww
        adapter = new ExamListAdapter(ExamList);
        mlistView = (ListView)view.findViewById(R.id.examlistView);
        mlistView.setAdapter(adapter);


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



        return view;
    }

    public void addNewsub(TestSub sub){ //nullcheck 필요? rangd null일 수 있음
        ExamList.add(sub);
        Collections.sort(ExamList);
        adapter.notifyDataSetChanged();
    }



}