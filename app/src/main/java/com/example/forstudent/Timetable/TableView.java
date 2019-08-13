package com.example.forstudent.Timetable;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.Context;
import android.widget.TableLayout;

import com.example.forstudent.R;
import com.github.tlaabs.timetableview.TimetableView;

public class TableView extends TimetableView {
    private static final int DEFAULT_HEADER_HIGHLIGHT_FONT_SIZE_DP = 15;
    TableLayout tableHeader;
    private Context context;
    private int headerHighlightColor;

    public TableView(Context context) {
        super(context, null);
        this.context = context;
        headerHighlightColor = context.getResources().getColor(R.color.sub_color);
    }

    @Override
    public void setHeaderHighlight(int idx) {
        super.setHeaderHighlight(idx);
        TableRow row = (TableRow) tableHeader.getChildAt(0);
        TextView tx = (TextView) row.getChildAt(idx);
        tx.setTextColor(Color.parseColor("#FFFFFF"));
        tx.setBackgroundColor(headerHighlightColor);
        tx.setTypeface(null, Typeface.BOLD);
        tx.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DEFAULT_HEADER_HIGHLIGHT_FONT_SIZE_DP);
    }


}