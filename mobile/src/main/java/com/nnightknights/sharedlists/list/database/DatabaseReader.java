package com.nnightknights.sharedlists.list.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.nnightknights.sharedlists.list.interfaces.List;

import java.util.ArrayList;

public class DatabaseReader extends AsyncTask<String, Integer, List> {
    DatabaseManager databaseManager;
    SQLiteDatabase listsDatabase;
    ProgressBar progressBar;

    public DatabaseReader(Context context){
        super();
        databaseManager = new DatabaseManager(context);
        progressBar = null;
    }

    public DatabaseReader(Context context, ProgressBar progressBar){
        super();
        databaseManager = new DatabaseManager(context);
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listsDatabase = databaseManager.getReadableDatabase();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (progressBar != null) {
            progressBar.setProgress(values[0]);
        }
    }

    @Override
    protected List doInBackground(String... strings) {
        Cursor cursor = listsDatabase.query(ListsContract.Lists.TABLE_NAME,
                new String[]{ListsContract.Lists.COLUMN_NAME_TITLE},
                ListsContract.Lists._ID + " = ?",
                new String[]{"1"},
                null,
                null,
                null);
        java.util.List names = new ArrayList<String>();
        while(cursor.moveToNext()){
            names.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(ListsContract.Lists.COLUMN_NAME_TITLE)
            ));
        }
        cursor.close();
        return null;
    }

    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
    }
}
