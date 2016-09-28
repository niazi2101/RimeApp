package com.example.rafi715.rimeapplication.Calling;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rafi715.rimeapplication.R;

public class CallLogActivity extends AppCompatActivity {

    DBhandler handler;

    int callID;
    TextView tv2;

    String data;

    String remoteID, dateTime, duration, endCause;
    Cursor cursor;

    TodoCursorAdaptor todoAdaptor;
    Context context = this;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);

        callID = 1;
        tv2 = (TextView) findViewById(R.id.textViewCallLogs);

        handler = new DBhandler(this);

        try {
            handler = new DBhandler(this);

            cursor = handler.getAllCallLogs();
            Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
            listView = (ListView) findViewById(R.id.listView);

            todoAdaptor = new TodoCursorAdaptor(context, cursor, 0);
            listView.setAdapter(todoAdaptor);



            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView listView, View view,
                                        int position, long id) {
                    Cursor itemCursor = (Cursor) CallLogActivity.this.listView.getItemAtPosition(position);
                    //int studentID = itemCursor.getInt(itemCursor.getColumnIndex(DBhandler.STUDENT_COLUMN_ID));



                    //Toast.makeText(getApplicationContext(),"Roll Num clicked: " + studentID,Toast.LENGTH_SHORT).show();
                    /*Intent intent = new Intent(getApplicationContext(), ShowDetail.class);
                    intent.putExtra(KEY_EXTRA_CONTACT_ID, studentID);
                    startActivity(intent);
*/
                }
            });
        }catch(Exception e)
        {
            Log.e("ERROR","UNABLE TO DISPLAY STUDENT LIST: "+e);
            Log.e("ERROR","UNABLE TO DISPLAY STUDENT LIST: "+cursor.getColumnNames().toString());
        }


/*
        try
    {
        handler = new DBhandler(this);

        rs = handler.getAllCallLogs();
        //rs.moveToFirst();


        if (rs.moveToFirst()) {
            do {
                // do what you need with the cursor here
                remoteID = rs.getString(rs.getColumnIndex(handler.CALLOG_COLUMN_CALLEE_NAME));
                dateTime = rs.getString(rs.getColumnIndex(handler.CALLOG_COLUMN_DATETIME));
                duration = rs.getString(rs.getColumnIndex(handler.CALLOG_COLUMN_DURATION));
                endCause = rs.getString(rs.getColumnIndex(handler.CALLOG_COLUMN_ENDCAUSE));

                data += "\n " + remoteID + "\n Time: " + dateTime + "\n Duration: " +duration
                +"\n End Cause:" + endCause;


            } while (rs.moveToNext());
        }
    }
    catch(Exception f)
    {
        Log.e("SQL", "ERROR INSERTING DATA: " + f.getMessage().toString());
    }finally {
        if (!rs.isClosed()) {
            rs.close();
        }
    }

        tv2.setText(data);
        */



    }
}

