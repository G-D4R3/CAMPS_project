package com.example.forstudent;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Objects;

/*
    과목을 직접 추가할 것인지 시간표에서 불러올 것인지
 */





public class AddExamSubjectDialog extends Dialog{
    private Button fromTimeTable;
    private Button makeNewOne;

    private Context context;


    public AddExamSubjectDialog(@NonNull Context context){
        super(context);
        this.context = context;
    }



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //@SuppressLint("ResourceType") View view = (View)findViewById(R.layout.add_exam_sub_dialog);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.dimAmount = 0.6f;
        getWindow().setAttributes(lp);
        fromTimeTable = (Button) findViewById(R.id.button2);
        makeNewOne = (Button) findViewById(R.id.button3);
        TextView text = (TextView)findViewById(R.id.textView);
        TextView text2 = (TextView)findViewById(R.id.editText);

        setContentView(R.layout.add_exam_sub_dialog);



    }
}
