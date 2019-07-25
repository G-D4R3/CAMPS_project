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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class TodoFragment extends Fragment {
    ArrayList<Assignment> AssList = new ArrayList<Assignment>();
    TodoListAdapter adapter;
    TextView mTitle;
    ListView mlistView;
    String title = "남은 과제 수";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_todo, container, false);

        mTitle = (TextView)view.findViewById(R.id.restDo);
        mlistView = (ListView)view.findViewById(R.id.assignmentList);
        Button mAdd = (Button)view.findViewById(R.id.addAss);

        mTitle.setText(title);

        adapter = new TodoListAdapter(AssList);
        mlistView.setAdapter(adapter);



        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity)getActivity();
                main.FragmentAdd(new AddNewAssignment());
                mTitle.setText(title);
            }
        });

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity main = (MainActivity)getActivity();
                main.FragmentAdd(new AddNewAssignment());
            }
        });







        return view;
    }


    public void AddNewAss(Assignment a){
        AssList.add(a);
        Collections.sort(AssList);
        adapter.notifyDataSetChanged();
        title = String.format("남은 과제 : %d",AssList.size());
    }

    public void RemoveAss(Assignment a){
        AssList.remove(a);
        Collections.sort(AssList);
        adapter.notifyDataSetChanged();
        title = String.format("남은 과제 : %d",AssList.size());
    }

    public void ModifyAss(Assignment a){

    }
}