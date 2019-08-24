package com.example.forstudent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.BoxHelperClass.GradeHelper;
import com.example.forstudent.DataClass.Grade;

import java.util.ArrayList;

public class CalcGradeFragment extends Fragment{

    /*** main ***/
    MainActivity main;

    /*** tmp ***/

    /*** view ***/
    View view;
    TableLayout mTable;
    String[] spinner45 = {"A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F", "P"};
    String[] spinner43 = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D+", "D0", "D-", "F", "P"};
    Spinner[] spinners = new Spinner[10];
    EditText[] credits = new EditText[10];
    EditText[] subjects = new EditText[10];
    TextView mGrade;
    TextView mCredit;
    RadioGroup mMethod;

    /*** flag ***/
    int radio;

    /*** storage ***/
    ArrayList<Grade> data;
    CalcGrade calcGrade;

    /*** thread ***/
    loadData load;
    saveData save;


    /***** instanciate *****/
    public static CalcGradeFragment newInstance() {
        return new CalcGradeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main  = (MainActivity)getActivity();
        radio = main.getUser().getCalcGradeCheck(); //radio button id
        load = new loadData();
        save = new saveData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /***** view load *****/
        view = (View)inflater.inflate(R.layout.calculate_grade, container, false);
        mGrade = (TextView)view.findViewById(R.id.grade);
        mCredit = (TextView)view.findViewById(R.id.grade_credit);
        mTable = (TableLayout)view.findViewById(R.id.grade_table);
        mMethod = (RadioGroup)view.findViewById(R.id.grade_radio);
        ImageButton calcnow = (ImageButton)view.findViewById(R.id.button2);
        InputMethodManager imm = main.keypad;

        /***** toolbar *****/
        main.setActionBarTitle("");
        main.centerToolbarTitle.setText("학점 계산기");
        main.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.FragmentRemove(CalcGradeFragment.this);
            }
        });


        /***** radio check. 4.3만점인지 4.5만점인지 *****/
        mMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton: //4.3만점
                        for(int i=0; i<10; i++){
                            spinners[i].setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinner43));
                            radio = R.id.radioButton;
                        }
                        break;
                    case R.id.radioButton2: //4.5만점
                        for(int i=0; i<10; i++){
                            spinners[i].setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinner45));
                            radio = R.id.radioButton2;
                        }
                }
            }
        });

        /***** grade list *****/

        /*** spinner ***/
        spinners[0] = view.findViewById(R.id.grade_spinner1);
        spinners[1] = view.findViewById(R.id.grade_spinner2);
        spinners[2] = view.findViewById(R.id.grade_spinner3);
        spinners[3] = view.findViewById(R.id.grade_spinner4);
        spinners[4] = view.findViewById(R.id.grade_spinner5);
        spinners[5] = view.findViewById(R.id.grade_spinner6);
        spinners[6] = view.findViewById(R.id.grade_spinner7);
        spinners[7] = view.findViewById(R.id.grade_spinner8);
        spinners[8] = view.findViewById(R.id.grade_spinner9);
        spinners[9] = view.findViewById(R.id.grade_spinner10);

        /*** credit ***/
        credits[0] = view.findViewById(R.id.grade_grade1);
        credits[1] = view.findViewById(R.id.grade_grade2);
        credits[2] = view.findViewById(R.id.grade_grade3);
        credits[3] = view.findViewById(R.id.grade_grade4);
        credits[4] = view.findViewById(R.id.grade_grade5);
        credits[5] = view.findViewById(R.id.grade_grade6);
        credits[6] = view.findViewById(R.id.grade_grade7);
        credits[7] = view.findViewById(R.id.grade_grade8);
        credits[8] = view.findViewById(R.id.grade_grade9);
        credits[9] = view.findViewById(R.id.grade_grade10);

        /*** subject ***/
        subjects[0] = view.findViewById(R.id.grade_sub1);
        subjects[1] = view.findViewById(R.id.grade_sub2);
        subjects[2] = view.findViewById(R.id.grade_sub3);
        subjects[3] = view.findViewById(R.id.grade_sub4);
        subjects[4] = view.findViewById(R.id.grade_sub5);
        subjects[5] = view.findViewById(R.id.grade_sub6);
        subjects[6] = view.findViewById(R.id.grade_sub7);
        subjects[7] = view.findViewById(R.id.grade_sub8);
        subjects[8] = view.findViewById(R.id.grade_sub9);
        subjects[9] = view.findViewById(R.id.grade_sub10);

        load.run();


        /***** 학점 계산 & 저장 *****/
        calcnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                if(radio==R.id.radioButton2){
                    calc45();
                }
                else{
                    calc43();
                }
                save.run();
            }
        });


        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        main.getSupportActionBar().setDisplayShowTitleEnabled(false);
        main.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        main.navBar.setVisibility(View.VISIBLE);
    }

    /***** 4.5학점 학점 계산 *****/
    public void calc45(){
        int totalcredit=0; //P학점 제외 이수 학점
        int ptotalcredit=0; //P학점 이수 학점

        data = new ArrayList<>(); //data refresh

        for(int i=0; i<credits.length; i++){

            int credit = Integer.parseInt(credits[i].getText().toString()); //이수 학점
            double grade = toDouble45(spinners[i].getSelectedItem().toString()); //학점
            String subject = subjects[i].getText().toString(); //과목 이름

            //이수 학점 0점이라면 무시하고 다음 칸
            if(credit==0) continue;

            //P학점이면 grade -1, 즉, P학점 이수학점에 이수학점만 더하고 다음 칸
            if(grade==-1) {
                ptotalcredit+= credit;
                continue;
            }

            //P학점 아니라면 그대로 진행, 총 이수 학점에 현재 과목 이수학점 더함
            totalcredit += credit;

            //data에 추가
            Grade g = new Grade(subject, credit, grade);
            data.add(g);
        } //for loop

        //총 이수학점이 0점이라면 종료
        if(totalcredit==0) return;

        //계산
        calcGrade = new CalcGrade(data);
        calcGrade.totalCredit = totalcredit;
        calcGrade.Calculate();

        //view set
        mGrade.setText(String.format("학점 : %.2f",calcGrade.grade));
        mCredit.setText(String.format("이수 : %d",totalcredit+ptotalcredit));

    }//calc45


    /***** spinner에서 가져온 grade(string)로 학점(double) 리턴 *****/
    public double toDouble45(String s){
        switch (s){
            case "A+":
                return 4.5;
            case "A0":
                return 4.0;
            case "B+":
                return 3.5;
            case "B0":
                return 3.0;
            case "C+":
                return 2.5;
            case "C0":
                return 2.0;
            case "D+":
                return 1.5;
            case "D0":
                return 1.0;
            case "F":
                return 0;
            case "P":
                return -1;
        }
        return -1; //나중에 수정해야함. P학점이랑 겹칠 우려
    }//to45


    /***** 4.3학점 학점 계산 *****/
    public void calc43(){

        int totalcredit=0;
        int ptotalcredit=0;

        data = new ArrayList<>();

        for(int i=0; i<credits.length; i++){

            int credit = Integer.parseInt(credits[i].getText().toString());
            double grade = toDouble43(spinners[i].getSelectedItem().toString());
            String subject = subjects[i].getText().toString();

            if(credit==0) continue;
            if(grade==-1) {
                ptotalcredit+= (int)credit;
                continue;
            }

            totalcredit += (int)credit;

            Grade g = new Grade(subject, credit, grade);
            data.add(g);
        } //for loop

        if(totalcredit==0) return;

        calcGrade = new CalcGrade(data);
        calcGrade.totalCredit = totalcredit;
        calcGrade.Calculate();

        mGrade.setText(String.format("학점 : %.2f",calcGrade.grade));
        mCredit.setText(String.format("이수 : %d",totalcredit+ptotalcredit));
    } //calc43


    public double toDouble43(String s){
        switch (s){
            case "A+":
                return 4.3;
            case "A0":
                return 4.0;
            case "A-":
                return 3.7;
            case "B+":
                return 3.3;
            case "B0":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.3;
            case "C0":
                return 2.0;
            case "C-":
                return 1.7;
            case "D+":
                return 1.3;
            case "D0":
                return 1.0;
            case "D-":
                return 0.7;
            case "F":
                return 0;
            case "P":
                return -1;
        }
        return -1;
    } //to43


    /***** 불러오기 *****/
    public class loadData extends Thread{
        public loadData(){

        }

        public void run(){
            data =  main.grades;
            radio = main.getUser().calcGradeCheck;
            mMethod.check(radio);

            //저장된 라디오 버튼대로 스피너 세팅
            switch (radio){
                case R.id.radioButton:
                    for(int i=0; i<10; i++){
                        spinners[i].setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinner43));
                    }
                    break;
                case R.id.radioButton2:
                    for(int i=0; i<10; i++){
                        spinners[i].setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinner45));
                    }
            }

            for(int i=0; i<data.size(); i++){
                if(radio==R.id.radioButton){
                    spinners[i].setSelection(setSelect43(data.get(i))); //spinner 아이템 세팅
                    String credit = Integer.toString(data.get(i).credit);
                    credits[i].setText(credit);
                    subjects[i].setText(data.get(i).subject);
                }
                else{
                    spinners[i].setSelection(setSelect45(data.get(i)));
                    String credit = Integer.toString(data.get(i).credit);
                    credits[i].setText(credit);
                    subjects[i].setText(data.get(i).subject);
                }
            }
            if(radio==R.id.radioButton){
                calc43();
            }
            else{
                calc45();
            }
        }//run

        private int setSelect45(Grade g) { //학점 보고 스피너 인덱스 리턴
            if(g.grade==4.5){
                return 0;
            }
            else if(g.grade==4.0){
                return 1;
            }
            else if(g.grade==3.5){
                return 2;
            }
            else if(g.grade==3.0){
                return 4;
            }
            else if(g.grade==2.5){
                return 5;
            }
            else if(g.grade==2.0){
                return 6;
            }
            else if(g.grade==1.5){
                return 7;
            }
            else if(g.grade==1.0){
                return 8;
            }
            else if(g.grade==-1){
                return 9;
            }
            return 0; //저장된 데이터 없을 시 자동으로 0번
        } //45

        public int setSelect43(Grade g){
            if(g.grade==4.3){
                return 0;
            }
            else if(g.grade==4.0){
                return 1;
            }
            else if(g.grade==3.7){
                return 2;
            }
            else if(g.grade==3.3){
                return 3;
            }
            else if(g.grade==3.0){
                return 4;
            }
            else if(g.grade==2.7){
                return 5;
            }
            else if(g.grade==2.3){
                return 6;
            }
            else if(g.grade==2.0){
                return 7;
            }
            else if(g.grade==1.7){
                return 8;
            }
            else if(g.grade==1.3){
                return 9;
            }
            else if(g.grade==1.0){
                return 10;
            }
            else if(g.grade==0.7){
                return 11;
            }
            else if(g.grade==0){
                return 12;
            }
            return 0;
        } //43


    } //loadData


    /***** 저장하기. 계산할때마다 저장 *****/
    public class saveData extends Thread{
        public saveData(){

        }

        public void run(){
            main.getGradeBox().removeAll();
            for(int i=0; i<data.size(); i++){
                GradeHelper gradeHelper = new GradeHelper((long)i+1, data.get(i).getSubject(),data.get(i).getCredit(),data.get(i).getGrade());
                GradeHelper.putGrade(gradeHelper);
            }
            main.getUser().setCalcGradeCheck(radio);
            main.getUserDataBox().put(main.getUser());
        }
    }



}
