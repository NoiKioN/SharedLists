package com.nnightknights.sharedlists.list.content.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.nnightknights.sharedlists.list.database.entities.ListsEntity;

@Entity(tableName = "text_element_to_list")
public class TextElementToListEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ForeignKey(
            entity = ListsEntity.class,
            parentColumns = "id",
            childColumns = "list_id",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)
    @ColumnInfo(name = "list_id")
    private int listID;

    @ForeignKey(
            entity = TextListElementEntity.class,
            parentColumns = "id",
            childColumns = "list_element_id",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)
    @ColumnInfo(name = "list_element_id")
    private int listElementID;
}
