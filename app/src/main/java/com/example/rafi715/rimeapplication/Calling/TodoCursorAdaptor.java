package com.example.rafi715.rimeapplication.Calling;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.rafi715.rimeapplication.R;


/**
 * Created by Hamza Khan Niaz on 4/7/2016.
 */
public class TodoCursorAdaptor extends CursorAdapter {



    public TodoCursorAdaptor(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_items_three, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {


    //    handler = new DBhandler();

        TextView TextCallid = (TextView) view.findViewById(R.id.call_Id);
        TextView TextName = (TextView) view.findViewById(R.id.textView_name);
        TextView TextDuration = (TextView) view.findViewById(R.id.textView_duration);
        TextView TextDateTime = (TextView) view.findViewById(R.id.textViewDateTime);
        TextView TextEndCause = (TextView) view.findViewById(R.id.textViewEndCause);

        // Extract properties from cursor
        int callID = cursor.getInt(cursor.getColumnIndexOrThrow(DBhandler.CALLOG_COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DBhandler.CALLOG_COLUMN_CALLEE_NAME));
        String duration = cursor.getString(cursor.getColumnIndexOrThrow(DBhandler.CALLOG_COLUMN_DURATION));
        String datetime = cursor.getString(cursor.getColumnIndexOrThrow(DBhandler.CALLOG_COLUMN_DATETIME));
        String endCause = cursor.getString(cursor.getColumnIndexOrThrow(DBhandler.CALLOG_COLUMN_ENDCAUSE));


        // Populate fields with extracted properties
        TextCallid.setText(String.valueOf(callID));
        TextName.setText(name);
        TextDuration.setText(duration);
        TextDateTime.setText(datetime);
        TextEndCause.setText(endCause);


    }
}