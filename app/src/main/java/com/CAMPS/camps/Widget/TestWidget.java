package com.CAMPS.camps.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.CAMPS.camps.DataClass.TestSub;
import com.CAMPS.camps.MainActivity;
import com.CAMPS.camps.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class TestWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.test_widget);
        views.setTextViewText(R.string.appwidget_text, widgetText);
        setText(views);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        if(action.equals("android.appwidget.action.APPWIDGET_UPDATE")){
            //setText();
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    public static void setText(RemoteViews views){
        ArrayList<TestSub> data = MainActivity.testSub;

        switch (data.size()){
            case 0:
                views.setViewVisibility(R.id.test_layout1, View.GONE);
                views.setViewVisibility(R.id.test_layout2, View.GONE);
                views.setViewVisibility(R.id.test_layout3, View.GONE);
                views.setViewVisibility(R.id.test_layout4, View.GONE);
            case 1:
                views.setViewVisibility(R.id.test_layout1, View.VISIBLE);
                views.setTextViewText(R.id.test_date1, String.format("%d.%d", data.get(0).getTestDate().get(Calendar.MONTH)+1, data.get(0).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time1, String.format("%d:%02d", data.get(0).startHour, data.get(0).startMinute));
                views.setTextViewText(R.id.test_name1, data.get(0).Name);

                views.setViewVisibility(R.id.test_layout2, View.GONE);
                views.setViewVisibility(R.id.test_layout3, View.GONE);
                views.setViewVisibility(R.id.test_layout4, View.GONE);
                break;
            case 2:
                views.setViewVisibility(R.id.test_layout1, View.VISIBLE);
                views.setTextViewText(R.id.test_date1, String.format("%d.%d", data.get(0).getTestDate().get(Calendar.MONTH)+1, data.get(0).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time1, String.format("%d:%02d", data.get(0).startHour, data.get(0).startMinute));
                views.setTextViewText(R.id.test_name1, data.get(0).Name);

                views.setViewVisibility(R.id.test_layout2, View.VISIBLE);
                views.setTextViewText(R.id.test_date2, String.format("%d.%d", data.get(1).getTestDate().get(Calendar.MONTH)+1, data.get(1).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time2, String.format("%d:%02d", data.get(1).startHour, data.get(1).startMinute));
                views.setTextViewText(R.id.test_name2, data.get(1).Name);

                views.setViewVisibility(R.id.test_layout3, View.GONE);
                views.setViewVisibility(R.id.test_layout4, View.GONE);
                break;
            case 3:
                views.setViewVisibility(R.id.test_layout1, View.VISIBLE);
                views.setTextViewText(R.id.test_date1, String.format("%d.%d", data.get(0).getTestDate().get(Calendar.MONTH)+1, data.get(0).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time1, String.format("%d:%02d", data.get(0).startHour, data.get(0).startMinute));
                views.setTextViewText(R.id.test_name1, data.get(0).Name);

                views.setViewVisibility(R.id.test_layout2, View.VISIBLE);
                views.setTextViewText(R.id.test_date2, String.format("%d.%d", data.get(1).getTestDate().get(Calendar.MONTH)+1, data.get(1).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time2, String.format("%d:%02d", data.get(1).startHour, data.get(1).startMinute));
                views.setTextViewText(R.id.test_name2, data.get(1).Name);

                views.setViewVisibility(R.id.test_layout3, View.VISIBLE);
                views.setTextViewText(R.id.test_date3, String.format("%d.%d", data.get(2).getTestDate().get(Calendar.MONTH)+1, data.get(2).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time3, String.format("%d:%02d", data.get(2).startHour, data.get(0).startMinute));
                views.setTextViewText(R.id.test_name3, data.get(2).Name);

                views.setViewVisibility(R.id.test_layout4, View.GONE);
                break;
            case 4:
                views.setViewVisibility(R.id.test_layout4, View.VISIBLE);
                views.setTextViewText(R.id.test_date4, String.format("%d.%d", data.get(3).getTestDate().get(Calendar.MONTH)+1, data.get(0).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time4, String.format("%d:%02d", data.get(3).startHour, data.get(3).startMinute));
                views.setTextViewText(R.id.test_name4, data.get(3).Name);

                views.setViewVisibility(R.id.test_layout3, View.VISIBLE);
                views.setTextViewText(R.id.test_date3, String.format("%d.%d", data.get(2).getTestDate().get(Calendar.MONTH)+1, data.get(2).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time3, String.format("%d:%02d", data.get(2).startHour, data.get(0).startMinute));
                views.setTextViewText(R.id.test_name3, data.get(2).Name);

                views.setViewVisibility(R.id.test_layout2, View.VISIBLE);
                views.setTextViewText(R.id.test_date2, String.format("%d.%d", data.get(1).getTestDate().get(Calendar.MONTH)+1, data.get(1).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time2, String.format("%d:%02d", data.get(1).startHour, data.get(1).startMinute));
                views.setTextViewText(R.id.test_name2, data.get(1).Name);

                views.setViewVisibility(R.id.test_layout1, View.VISIBLE);
                views.setTextViewText(R.id.test_date1, String.format("%d.%d", data.get(0).getTestDate().get(Calendar.MONTH)+1, data.get(0).getTestDate().get(Calendar.DAY_OF_MONTH)));
                views.setTextViewText(R.id.test_time1, String.format("%d:%02d", data.get(0).startHour, data.get(0).startMinute));
                views.setTextViewText(R.id.test_name1, data.get(0).Name);
                break;

        }
    }
}

