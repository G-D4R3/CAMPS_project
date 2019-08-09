package com.example.forstudent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class BasicDialogFragment extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_basic_dialog, container, false);
        TextView Title = (TextView)view.findViewById(R.id.fragTitle);

        return view;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity main = (MainActivity) getActivity();
    }
}
