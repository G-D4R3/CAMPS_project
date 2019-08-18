package com.example.forstudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forstudent.DataClass.Grade;

import java.util.ArrayList;

public class CalcGradeFragment extends Fragment {

    //String[] spinner45 = {"A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F", "P"};
    //String[] spinner43 = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D+", "D0", "D-", "F", "P"};



    public enum Grade45 {
        APLUS("A+"), AZERO("A0"), BPLUS("B+"), BZERO("B0"), CPLUS("C+"), CZERO("C0"), DPLUS("D+"), DZERO("D0"), F("F"), P("P");
        String string;
        Grade45(String s) {
            string = s;
        }

        public double toDouble(){
            switch (this){
                case APLUS:
                    return 4.5;
                case AZERO:
                    return 4.0;
                case BPLUS:
                    return 3.5;
                case BZERO:
                    return 3.0;
                case CPLUS:
                    return 2.5;
                case CZERO:
                    return 2.0;
                case DPLUS:
                    return 1.5;
                case DZERO:
                    return 1.0;
                case F:
                    return 0;
                case P:
                    return -1;
            }
            return -1;
        }
    }

    ArrayList<Grade> data = new ArrayList<>();
    TextView mGrade;
    TextView mCredit;
    TableLayout mTable;
    RadioGroup mMethod;
    Spinner[] spinners = new Spinner[10];
    EditText[] credits = new EditText[10];
    EditText[] subjects = new EditText[10];
    View view;
    CalcGrade calcGrade;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View)inflater.inflate(R.layout.calculate_grade, container, false);
        mGrade = (TextView)view.findViewById(R.id.grade);
        mCredit = (TextView)view.findViewById(R.id.grade_credit);
        //mList = (ListView)view.findViewById(R.id.grade_list);
        mTable = (TableLayout)view.findViewById(R.id.grade_table);
        mMethod = (RadioGroup)view.findViewById(R.id.grade_radio);

        /******* grade list ********/

        /****spinner****/
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

        /****credit****/
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

        /****subject****/
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

        calc45();


        return view;
    }

    public void calc45(){
        int totalcredit=0;
        for(int i=0; i<credits.length; i++){
            double credit = (double)Double.parseDouble(credits[i].getText().toString());
            String grade = spinners[i].getSelectedItem().toString();
            String subject = subjects[i].getText().toString();
            if(credit==0) break;
            Grade45 grade45 = Grade45.valueOf(grade);
            if(grade45.toDouble()==-1) break;
            Grade g = new Grade(subject, grade45.toDouble(), credit);
            totalcredit += (int)credit;
            data.add(g);
        }
        calcGrade.totalCredit = totalcredit;
        calcGrade.Calculate();
        mGrade.setText("학점 : "+calcGrade.grade);
        mCredit.setText("이수 : "+totalcredit);

    }

    public void calc43(){

    }

}
