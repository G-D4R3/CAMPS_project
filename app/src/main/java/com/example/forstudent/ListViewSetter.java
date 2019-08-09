package com.example.forstudent;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewSetter {
    int divider=0;
    public boolean Flag=false;


    public void setListViewHeight(ListView listview){
        ListAdapter adapter = listview.getAdapter();

        if(adapter==null){
            return;
        }


        int totalheight=0;


        for(int i=0; i<adapter.getCount(); i++){
            View item = adapter.getView(i, null, listview);
            item.measure(0,0);
            totalheight+=item.getMeasuredHeight()+25;
        }

        divider = listview.getDividerHeight() * adapter.getCount();


        ViewGroup.LayoutParams params = listview.getLayoutParams();
        if(divider==0){
            params.height = totalheight;
        }
        else{
            params.height = totalheight+divider+(30*adapter.getCount());
        }
        listview.setLayoutParams(params);
        listview.requestLayout();
    }

}
