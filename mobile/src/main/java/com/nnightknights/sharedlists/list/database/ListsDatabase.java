package com.nnightknights.sharedlists.list.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.provider.BaseColumns;

@Database()
public abstract class ListsDatabase extends RoomDatabase{
    private ListsDatabase(){}

    public class Lists implements BaseColumns{
        public static final String TABLE_NAME = "lists",
        COLUMN_NAME_TITLE = "title",
        COLUMN_NAME_DESCRIPTION = "description",
        COLUMN_NAME_DATE_CREATED = "date_created",
        COLUMN_NAME_DATE_UPDATED = "date_updated";
    }

    public class ListObjects implements BaseColumns{
        public static final String TABLE_NAME = "list_objects",
        COLUMN_NAME_TITLE = "title",
        COLUMN_NAME_DESCRIPTION = "description",
        COLUMN_NAME_OBJECT_TYPE = "type",
        COLUMN_NAME_OBJECT_CONTENT = "content",
        COLUMN_NAME_DATE_CREATED = "date_created",
        COLUMN_NAME_DATE_UPDATED = "date_updated";
    }

    public class ListContents implements BaseColumns{
        public static final String TABLE_NAME = "list_contents",
        COLUMN_NAME_LIST_ID = "list_id",
        COLUMN_NAME_LIST_OBJECT_ID = "object_id";
    }
}
