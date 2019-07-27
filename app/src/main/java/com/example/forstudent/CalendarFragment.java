package com.example.forstudent;

import android.app.DatePickerDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.example.forstudent.BoxClass.Assignment;
import com.example.forstudent.BoxHelperClass.AssignmentHelper;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.objectbox.Box;

import static com.example.forstudent.ObjectBox.boxStore;

public class CalendarFragment extends Fragment {

    private TextView Dday;
    private TextView today;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//test
        MainActivity main = (MainActivity)getActivity();
        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.ic_launcher_foreground));
//or

//or if you want to specify event label color

        View view = (View) inflater.inflate(R.layout.fragment_calendar,container,false);

        AssignmentHelper testAssignmanet = new AssignmentHelper(0,"컴구","과제1 극혐;;;",Calendar.getInstance());
        AssignmentHelper.putAssignment(testAssignmanet);
        long id = testAssignmanet.getId();
        Assignment tmp = (Assignment) main.getAssignmentBox().get(id);
        System.out.println("ID : "+id+" 과목 : "+AssignmentHelper.getSubject(id)+" 메모 : "+AssignmentHelper.getMemo(id)+ " 기한 : " + AssignmentHelper.getPeriod(id));
       /* Box<UserData> userBox = boxStore.boxFor(UserData.class);
        MainActivity main = (MainActivity)getActivity();
        UserData testUser = new UserData(0,"TESTUSER",new Date(),999);
        main.getUserDataBox().put(testUser);
        long id = testUser.id;
        String name = testUser.name;

        System.out.println(id+" "+testUser.getName());
        testUser.setName("NEW TEST NAME");
        main.getUserDataBox().put(testUser);
        UserData getUser = (UserData) main.getUserDataBox().get(id);

        System.out.println( getUser.id+" "+getUser.getName());*/
        return view;
    }
}