package com.example.forstudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class addNewExamSub extends Fragment {
    public String title;
    int index;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.add_new_exam_sub, container, false);
        TextView Title = (TextView)view.findViewById(R.id.fragTitle);
        TextView mSubname = (TextView)view.findViewById(R.id.subName);




        return view;

    }

}
