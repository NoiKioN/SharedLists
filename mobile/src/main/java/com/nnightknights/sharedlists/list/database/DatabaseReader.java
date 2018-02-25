package com.nnightknights.sharedlists.list.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.nnightknights.sharedlists.list.interfaces.List;

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
        return null;

    }

    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
    }
}
