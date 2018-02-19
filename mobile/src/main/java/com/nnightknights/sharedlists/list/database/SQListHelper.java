package com.nnightknights.sharedlists.list.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQListHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Shared_Lists.db";

    public SQListHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQLCommands.CREATE_LIST_ENTRIES);
        database.execSQL(SQLCommands.CREATE_LIST_OBJECT_ENTRIES);
        database.execSQL(SQLCommands.CREATE_LIST_CONTENTS_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(SQLCommands.DELETE_ALL_ENTRIES);
        onCreate(database);
    }

    private static final class SQLCommands{
        private SQLCommands(){}

        private static final String CREATE_LIST_ENTRIES =
                String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER)",
                        ListsContract.Lists.TABLE_NAME, ListsContract.Lists._ID, ListsContract.Lists.COLUMN_NAME_TITLE,
                        ListsContract.Lists.COLUMN_NAME_DESCRIPTION, ListsContract.Lists.COLUMN_NAME_DATE_CREATED,
                        ListsContract.Lists.COLUMN_NAME_DATE_UPDATED),

        CREATE_LIST_OBJECT_ENTRIES =
                String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER)",
                            ListsContract.ListObjects.TABLE_NAME, ListsContract.ListObjects._ID, ListsContract.ListObjects.COLUMN_NAME_TITLE,
                            ListsContract.ListObjects.COLUMN_NAME_DESCRIPTION, ListsContract.ListObjects.COLUMN_NAME_OBJECT_TYPE,
                            ListsContract.ListObjects.COLUMN_NAME_OBJECT_CONTENT, ListsContract.ListObjects.COLUMN_NAME_DATE_CREATED,
                            ListsContract.ListObjects.COLUMN_NAME_DATE_UPDATED),

        CREATE_LIST_CONTENTS_ENTRIES =
                String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER)",
                        ListsContract.ListContents.TABLE_NAME, ListsContract.ListContents._ID, ListsContract.ListContents.COLUMN_NAME_LIST_ID,
                        ListsContract.ListContents.COLUMN_NAME_LIST_OBJECT_ID),

        DELETE_ALL_ENTRIES = String.format("DROP TABLE IF EXISTS %s, %s, %s",
                ListsContract.ListContents.TABLE_NAME, ListsContract.ListObjects.TABLE_NAME,
                ListsContract.Lists.TABLE_NAME);
    }
}
