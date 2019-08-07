package com.example.forstudent;

import android.content.Context;

import androidx.fragment.app.Fragment;

public class TransparentFragment extends Fragment {
    public static TransparentFragment newInstance(){
        return new TransparentFragment();
    }

    @Override
    public void onAttach(Context context) {
        MainActivity main = (MainActivity)getActivity();
        super.onAttach(context);
        main.FragmentRemove(TransparentFragment.this);
    }
}
