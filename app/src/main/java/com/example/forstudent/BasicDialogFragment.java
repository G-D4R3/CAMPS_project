package com.example.forstudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class BasicDialogFragment extends Fragment {
    private String title;
    int index;

    public BasicDialogFragment(String Title){
        this.title = Title;
        index=1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_basic_dialog, container, false);
        switch(index){
            case 1:
                TextView Title = (TextView)view.findViewById(R.id.title);
                Title.setText(title);

        }








        return view;

    }

}
