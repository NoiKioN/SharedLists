package com.nnightknights.sharedlists;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.nnightknights.sharedlists.list.database.ListsDatabase;
import com.nnightknights.sharedlists.list.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final TextView testText = findViewById(R.id.testText);
        testText.setText(getIntent().getStringExtra("TestMessage"));
        getIntent().removeExtra("TestMessage");

        new AsyncTask(){
            private DatabaseManager databaseHelper = new DatabaseManager(getApplicationContext());
            private SQLiteDatabase database;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                database = databaseHelper.getWritableDatabase();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                ContentValues values = new ContentValues();
                values.put(ListsDatabase.Lists.COLUMN_NAME_TITLE, "Test title");
                values.put(ListsDatabase.Lists.COLUMN_NAME_DESCRIPTION, "Test description.");
                values.put(ListsDatabase.Lists.COLUMN_NAME_DATE_CREATED, Calendar.getInstance().getTimeInMillis());
                values.put(ListsDatabase.Lists.COLUMN_NAME_DATE_UPDATED, Calendar.getInstance().getTimeInMillis());

                long newRowId = database.insert(ListsDatabase.Lists.TABLE_NAME, null, values);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                database.close();
                super.onPostExecute(o);
            }
        }.execute();

        try {
            testText.setText(String.valueOf(new AsyncTask() {
                private DatabaseManager databaseHelper = new DatabaseManager(getApplicationContext());
                private SQLiteDatabase database;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    database = databaseHelper.getReadableDatabase();
                }

                @Override
                protected Object doInBackground(Object[] objects) {
                    Cursor cursor = database.query(ListsDatabase.Lists.TABLE_NAME,
                            new String[]{ListsDatabase.Lists.COLUMN_NAME_TITLE},
                            ListsDatabase.Lists._ID + " = ?",
                            new String[]{"1"},
                            null,
                            null,
                            null);
                    List names = new ArrayList<String>();
                    while(cursor.moveToNext()){
                        names.add(cursor.getString(
                                cursor.getColumnIndexOrThrow(ListsDatabase.Lists.COLUMN_NAME_TITLE)
                        ));
                    }
                    cursor.close();
                    String rName = "none";
                    for (Object name : names.toArray()){
                        if(name != null){
                            rName = (String) name;
                            break;
                        }
                    }
                    return rName;
                }

                @Override
                protected void onPostExecute(Object o) {
                    database.close();
                    super.onPostExecute(o);
                }
            }.execute().get()));
        }
        catch (Exception e){
            Log.d("async#2", "Something went wrong.", e);
        }
    }
}
