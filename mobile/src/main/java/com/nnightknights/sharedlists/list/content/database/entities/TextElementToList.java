package com.nnightknights.sharedlists.list.content.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.nnightknights.sharedlists.list.database.entities.List;

@Entity(tableName = "text_element_to_list")
public class TextElementToList {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ForeignKey(
            entity = List.class,
            parentColumns = "id",
            childColumns = "list_id",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)
    @ColumnInfo(name = "list_id")
    private int listID;

    @ForeignKey(
            entity = TextListElement.class,
            parentColumns = "id",
            childColumns = "list_element_id",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)
    @ColumnInfo(name = "list_element_id")
    private int listElementID;
}
