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
    AddExamSubjectDialog dialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_exam, container, false);
        addSubject = (Button) view.findViewById(R.id.addSubject);
        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AddExamSubjectDialog(getContext(),"과목 추가", "새 과목 추가하기", "불러오기", leftListener, "직접추가",rightListener);
                dialog.show();
            }
        });
        return view;
    }

    private View.OnClickListener leftListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast toast = Toast.makeText(getContext(), "불러오기~", Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    private View.OnClickListener rightListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
            MainActivity main = (MainActivity)getActivity();
            main.Fragmentchange(1);
        }
    };

}