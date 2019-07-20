package com.example.forstudent;

import android.app.DatePickerDialog;
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

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import io.objectbox.Box;

import static com.example.forstudent.ObjectBox.boxStore;

public class CalendarFragment extends Fragment {

    private TextView Dday;
    private TextView today;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_calendar,container,false);
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