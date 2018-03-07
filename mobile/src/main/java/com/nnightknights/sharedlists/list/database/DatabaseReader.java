package com.nnightknights.sharedlists.list.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.nnightknights.sharedlists.list.interfaces.ListI;

import java.util.ArrayList;

public class DatabaseReader extends AsyncTask<QueryData, Integer, ListI[]> {
    SQLiteDatabase listsDatabase;
    ProgressBar progressBar;

    public DatabaseReader(Context context){
        super();
        progressBar = null;
    }

    public DatabaseReader(Context context, ProgressBar progressBar){
        super();
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (progressBar != null) {
            progressBar.setProgress(values[0]);
        }
    }

    @Override
    protected ListI[] doInBackground(QueryData... dataIn) {
        QueryData queryData = dataIn[0];

        Cursor cursor = null;
        if(queryData.getQueryType() == QueryType.TYPE_NORMAL) {
            cursor = listsDatabase.query(
                    queryData.getDistinct(),
                    queryData.getTableName(),
                    queryData.getColumnNames(),
                    queryData.getSelection(),
                    queryData.getSelectionArguments(),
                    queryData.getGroupBy(),
                    queryData.getHaving(),
                    queryData.getOrderBy(),
                    queryData.getLimit());

            java.util.List<String[]> dataOut = new ArrayList<>();
            while(cursor.moveToNext()){
                String[] data = new String[queryData.getColumnNames().length];

                for (int index = 0; index < data.length; index++){
                    data[index] = cursor.getString(cursor.getColumnIndexOrThrow(queryData.getColumnNames()[index]));
                }

                dataOut.add(data);
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ListI[] listI) {
        listsDatabase.close();
        super.onPostExecute(listI);
    }
}
