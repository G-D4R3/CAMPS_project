package com.example.forstudent;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ExamFragment extends Fragment{

    private Button addSubject;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_exam, container, false);
        addSubject = (Button) view.findViewById(R.id.addSubject);
        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final BasicDialog dialog = new BasicDialog();
                dialog.show(getFragmentManager(),"Basic Dialog");

                View.OnClickListener mMakeNew = new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Toast toast = Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT);
                        toast.show();
                        dialog.dismiss();
                    }
                };
                View.OnClickListener mSetDirectly = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        MainActivity main = (MainActivity)getActivity();
                        main.Fragmentchange(1);
                    }
                };
                dialog.setListener(mMakeNew, mSetDirectly);
            }
        });
        return view;
    }



}