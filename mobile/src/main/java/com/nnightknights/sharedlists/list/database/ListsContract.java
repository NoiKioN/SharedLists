package com.nnightknights.sharedlists.list.database;

import android.provider.BaseColumns;

public class ListsContract {
    private ListsContract(){}

    public static class Lists implements BaseColumns{
        public static final String TABLE_NAME = "lists",
                COLUMN_NAME_TITLE = "title",
                COLUMN_NAME_DESCRIPTION = "desc",
                COLUMN_NAME_DATE_CREATED = "date-c",
                COLUMN_NAME_DATE_UPDATED = "date-u";
    }
}
