package com.nnightknights.sharedlists.list.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Shared_Lists.db";

    public DatabaseManager(Context context) {
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
                        ListsDatabase.Lists.TABLE_NAME, ListsDatabase.Lists._ID, ListsDatabase.Lists.COLUMN_NAME_TITLE,
                        ListsDatabase.Lists.COLUMN_NAME_DESCRIPTION, ListsDatabase.Lists.COLUMN_NAME_DATE_CREATED,
                        ListsDatabase.Lists.COLUMN_NAME_DATE_UPDATED),

        CREATE_LIST_OBJECT_ENTRIES =
                String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER)",
                            ListsDatabase.ListObjects.TABLE_NAME, ListsDatabase.ListObjects._ID, ListsDatabase.ListObjects.COLUMN_NAME_TITLE,
                            ListsDatabase.ListObjects.COLUMN_NAME_DESCRIPTION, ListsDatabase.ListObjects.COLUMN_NAME_OBJECT_TYPE,
                            ListsDatabase.ListObjects.COLUMN_NAME_OBJECT_CONTENT, ListsDatabase.ListObjects.COLUMN_NAME_DATE_CREATED,
                            ListsDatabase.ListObjects.COLUMN_NAME_DATE_UPDATED),

        CREATE_LIST_CONTENTS_ENTRIES =
                String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER)",
                        ListsDatabase.ListContents.TABLE_NAME, ListsDatabase.ListContents._ID, ListsDatabase.ListContents.COLUMN_NAME_LIST_ID,
                        ListsDatabase.ListContents.COLUMN_NAME_LIST_OBJECT_ID),

        DELETE_ALL_ENTRIES = String.format("DROP TABLE IF EXISTS %s, %s, %s",
                ListsDatabase.ListContents.TABLE_NAME, ListsDatabase.ListObjects.TABLE_NAME,
                ListsDatabase.Lists.TABLE_NAME);
    }
}
