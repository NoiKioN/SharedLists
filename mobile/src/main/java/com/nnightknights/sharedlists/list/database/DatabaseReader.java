package com.nnightknights.sharedlists.list.database;

import android.os.AsyncTask;

import com.nnightknights.sharedlists.list.database.DAOs.ListsDAO;
import com.nnightknights.sharedlists.list.database.entities.ListExtractionTuple;
import com.nnightknights.sharedlists.list.database.entities.ListTuple;

import javax.inject.Inject;

public class DatabaseReader extends AsyncTask<Object, Integer, Object[]> {
    public static final String TAG = "DatabaseReader";

    @Inject
    ListsDatabase listsDatabase;

    private ListsDAO listsDAO;

    public DatabaseReader() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listsDAO = listsDatabase.getListsDAO();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Object[] doInBackground(Object... dataIn) {
        switch ((DatabaseActions) dataIn[0]){
            case GET_LIST_TITLE_FAVORITE_PINNED_TUPLE:
                return listsDAO.getListTitleFavoritePinnedTuple(10);
            case GET_ALL_LISTS:
                return listsDAO.getAllLists();
            case GET_LIST_BY_INDEX:
                return new ListTuple[] {listsDAO.getListByIndex((int) dataIn[1])};
            case GET_LISTS_FOR_EXTRACTION:
                return new ListExtractionTuple[] {listsDAO.getListForExtraction((int) dataIn[1])};
            case GET_LISTS_FROM_INDEX:
                return listsDAO.getListsFromIndex((int) dataIn[1], (int) dataIn[2]);
            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(Object[] list) {
        super.onPostExecute(list);
    }
}
