package com.example.forstudent;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewSetter {
    public void setListViewHeight(ListView listview){
        ListAdapter adapter = listview.getAdapter();

        if(adapter==null){
            return;
        }


        int totalheight=0;


        for(int i=0; i<adapter.getCount(); i++){
            View item = adapter.getView(i, null, listview);
            item.measure(0,0);
            totalheight+=item.getMeasuredHeight()+20;
        }

        int divider = listview.getDividerHeight() *( adapter.getCount()-1);

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalheight+divider;
        listview.setLayoutParams(params);
        listview.requestLayout();
    }

}
