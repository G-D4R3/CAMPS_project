package com.CAMPS.camps.Widget;

import android.appwidget.AppWidgetProvider;

/**
 * Implementation of App Widget functionality.
 */

public class TimeTableWidget extends AppWidgetProvider {
/*
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        //Now testing
        String folder = "Pictures";
        String filename = "screenshot.jpg";

        File sdCardPath = Environment.getExternalStorageDirectory();
        String pathName = sdCardPath.getPath() + "/" + folder + "/" + filename;
        Drawable d = Drawable.createFromPath(pathName);

        // Construct the RemoteViews object


        views.setImageViewResource(R.id.timetableView,d);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.timetable_app_widget);
        views.setImageViewUri(R.id.timetableView, MainActivity.timetableUri);
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
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
*/

}