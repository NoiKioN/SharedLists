package com.nnightknights.sharedlists;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nnightknights.sharedlists.list.database.ListsContract;
import com.nnightknights.sharedlists.list.database.SQListHelper;

import java.sql.Date;
import java.util.Calendar;

public class TestActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        new AsyncTask(){
            private SQListHelper databaseHelper = new SQListHelper(getApplicationContext());
            private SQLiteDatabase database;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                database = databaseHelper.getWritableDatabase();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                ContentValues values = new ContentValues();
                values.put(ListsContract.Lists.COLUMN_NAME_TITLE, "Test");
                values.put(ListsContract.Lists.COLUMN_NAME_DESCRIPTION, "Test");
                values.put(ListsContract.Lists.COLUMN_NAME_DATE_CREATED, Calendar.getInstance().getTimeInMillis());
                values.put(ListsContract.Lists.COLUMN_NAME_DATE_UPDATED, Calendar.getInstance().getTimeInMillis());

                long newRowId = database.insert(ListsContract.Lists.TABLE_NAME, null, values);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                database.close();
                super.onPostExecute(o);
            }
        };

        new AsyncTask(){
            private SQListHelper databaseHelper = new SQListHelper(getApplicationContext());
            private SQLiteDatabase database;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                database = databaseHelper.getReadableDatabase();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                database.close();
                super.onPostExecute(o);
            }
        };
    }
}
