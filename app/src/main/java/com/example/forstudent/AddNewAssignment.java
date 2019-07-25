package com.example.forstudent;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.Calendar;

public class AddNewAssignment extends Fragment {

    Calendar today = Calendar.getInstance();
    Calendar period = Calendar.getInstance();
    Assignment ass;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.add_new_assignment, container, false);
        TextView mTitle = (TextView)view.findViewById(R.id.assTitle);
        final EditText mName = (EditText)view.findViewById(R.id.assName);
        final TextView mDate = (TextView)view.findViewById(R.id.pdate2);
        TextView mCancel = (TextView)view.findViewById(R.id.cancle4);
        TextView mComplete = (TextView)view.findViewById(R.id.complete4);
        final EditText mRange = (EditText)view.findViewById(R.id.Range3);

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText((month+1)+"월 "+dayOfMonth+"일");
                        period.set(year,month,dayOfMonth);

                    }
                },today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE));
                dialog.show();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity)getActivity();
                main.FragmentRemove(AddNewAssignment.this);
            }
        });

        mComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRange.getText().length()==0){
                    ass = new Assignment(mName.getText().toString(),period);
                }
                else{
                    ass = new Assignment(mName.getText().toString(),period,mRange.getText().toString());
                }
                MainActivity main = (MainActivity)getActivity();
                main.todoFragment.AddNewAss(ass);
                main.FragmentRemove(AddNewAssignment.this);
            }
        });





        return view;

    }

}
