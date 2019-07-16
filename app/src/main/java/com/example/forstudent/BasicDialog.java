package com.example.forstudent;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/*
    과목을 직접 추가할 것인지 시간표에서 불러올 것인지
 */





public class BasicDialog extends DialogFragment{
    TextView t_title;
    TextView t_text;
    TextView mLeft;
    TextView mRight;
    private View.OnClickListener leftListener;
    private View.OnClickListener rightListener;

    public void setListener(View.OnClickListener left, View.OnClickListener right){
        this.leftListener = left;
        this.rightListener = right;
    }




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  (View)inflater.inflate(R.layout.basic_dialog, container, false);

        t_title = (TextView)view.findViewById(R.id.TitleText);
        t_text = (TextView)view.findViewById(R.id.ContentText);
        mLeft = (TextView)view.findViewById(R.id.leftText);
        mRight = (TextView)view.findViewById(R.id.rightText);

        Toast toast = Toast.makeText(getContext(), "asdf", Toast.LENGTH_SHORT);
        mLeft.setOnClickListener(leftListener);
        mRight.setOnClickListener(rightListener);
        return view;
    }











}
