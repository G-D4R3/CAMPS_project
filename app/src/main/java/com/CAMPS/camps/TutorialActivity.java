package com.CAMPS.camps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TutorialActivity extends AppCompatActivity {
    public static boolean start = false;
    private ViewPager viewPager;
    private TutorialSlideAdapater adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        viewPager = findViewById(R.id.view_pager);
        adapter = new TutorialSlideAdapater(this);
        viewPager.setAdapter(adapter);

        View.OnClickListener start = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStart();
            }
        };
        adapter.listener = start;
    }

    private void getStart() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
