package com.example.forstudent;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/*
    과목을 직접 추가할 것인지 시간표에서 불러올 것인지
 */





public class AddExamSubjectDialog extends DialogFragment {
    private Button leftButton;
    private Button rightButton;
    private String leftText;
    private String rightText;
    private Context context;
    private int buttonset; //button 개수
    private View.OnClickListener leftListener;
    private View.OnClickListener rightListener;
    private String Title;
    private String Content;


    public AddExamSubjectDialog(@NonNull Context context, String 과목_추가, String title, String content, View.OnClickListener listener, String leftButton, View.OnClickListener leftListener){
        super(context);
        this.Title = title;
        this.Content = content;
        this.context = context;
        this.leftText = leftButton;
        this.leftListener =leftListener;
        buttonset=1;
    }

    public AddExamSubjectDialog(@NonNull Context context, String title, String content,  String leftButton, String rightButton,  View.OnClickListener leftListener, View.OnClickListener rightListener){
        super(context);
        this.Title = title;
        this.Content = content;
        this.context = context;
        this.leftText = leftButton;
        this.leftListener = leftListener;
        this.rightText = rightButton;
        this.rightListener = rightListener;
        buttonset = 2;
    }



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //@SuppressLint("ResourceType") View view = (View)findViewById(R.layout.add_exam_sub_dialog);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.dimAmount = 0.6f;
        getWindow().setAttributes(lp);

        switch(buttonset){
            case 1:
                leftButton = (Button) findViewById(R.id.button2);
                leftButton.setOnClickListener(leftListener);
            case 2:
                leftButton = (Button) findViewById(R.id.button2);
                rightButton = (Button) findViewById(R.id.button3);
                leftButton.setOnClickListener(leftListener);
                rightButton.setOnClickListener(rightListener);

        }

        TextView t_title = (TextView)findViewById(R.id.dtitle);
        TextView t_text = (TextView)findViewById(R.id.dcontent);

        t_title.setText(Title);
        t_text.setText(Content);

        setContentView(R.layout.add_exam_sub_dialog);





    }
}
