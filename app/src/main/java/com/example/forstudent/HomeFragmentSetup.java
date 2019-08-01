package com.example.forstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragmentSetup extends Fragment {
    public boolean[] select = {true, true, true, true};

    public static HomeFragmentSetup newInstance(){
        return new HomeFragmentSetup();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.home_setup, container, false);
        TextView Title = (TextView)view.findViewById(R.id.setupTitle);
        TextView mComplete = (TextView)view.findViewById(R.id.complete5);
        TextView mCancel = (TextView)view.findViewById(R.id.cancel5);
        TextView mLayout = (TextView)view.findViewById(R.id.layoutset);

        mComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity)getActivity();
                main.FragmentRemove(main.homeFragmentSetup);
                Toast.makeText(getContext(), "설정이 적용됩니다.", Toast.LENGTH_SHORT).show();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("설정이 완료되지 않았습니다.");
                dialog.setMessage("설정을 적용하지 않으시겠습니까?");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //설정 원래대로 초기화
                        MainActivity main = (MainActivity)getActivity();
                        main.FragmentRemove(main.homeFragmentSetup);
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



        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] menu = {"일정","수업","과제","시험"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("화면에 표시할 메뉴");
                dialog.setPositiveButton("적용", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setMultiChoiceItems(menu, select, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked==true){
                            select[which]=true;
                        }
                        else if(isChecked==false){
                            select[which]=false;
                        }
                        System.out.println(which);
                    }
                });
                dialog.create();
                dialog.show();
            }
        });






        return view;

    }

}
