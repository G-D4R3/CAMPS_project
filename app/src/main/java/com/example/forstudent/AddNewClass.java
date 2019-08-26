package com.example.forstudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.DataClass.Assignment;

public class AddNewClass extends Fragment {

    /*** main ***/
    MainActivity main;
    InputMethodManager input;

    /*** tmp ***/
    String Lecture;
    String Professor;

    /*** view ***/
    EditText mLecture;
    EditText mProfessor;

    /*** flag ***/
    Boolean MOD = false;

    /*** Storage ***/




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity main = (MainActivity)getActivity();

        /*** view load ***/
        View view = (View)inflater.inflate(R.layout.add_new_class, container, false);
        mLecture = (EditText)view.findViewById(R.id.className);
        mProfessor = (EditText)view.findViewById(R.id.professor);

        /***** toolbar *****/
        main.BACK_STACK=true;
        main.centerToolbarTitle.setText("과목 추가");
       main.toolbar.setTitle("");
        main.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // hideKey();
                main.FragmentRemove(AddNewClass.this);
            }
        });
        return view;

    }

    /***** toolbar *****/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_add_assignment,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MainActivity main = (MainActivity)getActivity();
        if (id == R.id.check_icon) {

        }

        return true;
    }

    public static AddNewClass newInstance(){
        return new AddNewClass();
    }

}
