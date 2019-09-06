package com.CAMPS.camps;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class LoadingMain extends Activity {
    boolean first_run;
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("FirstRun", MODE_PRIVATE);
        first_run = sp.getBoolean("FirstRun", true);
        startloading();
    }

    private void startloading() {
        try{
           Thread.sleep(1500);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        if(first_run==true){
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("FirstRun", false);
            editor.commit();
            startActivity(new Intent(this, TutorialActivity.class));
            finish();
        }
        else{
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }


    }
}
