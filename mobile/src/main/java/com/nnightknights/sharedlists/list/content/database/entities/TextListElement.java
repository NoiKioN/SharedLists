package com.nnightknights.sharedlists.list.content.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "text_list_elements", indices = {})
public class TextListElement {
    @PrimaryKey
    @ColumnInfo(name = "id", index = true)
    private int id;

    @ColumnInfo(name = "title", index = true)
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "tags")
    private String tags;

    @ColumnInfo(name = "date_created")
    private long dateCreated;

    @ColumnInfo(name = "date_updated")
    private long dateUpdated;
}
