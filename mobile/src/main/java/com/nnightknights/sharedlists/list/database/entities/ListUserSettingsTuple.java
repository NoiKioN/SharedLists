package com.nnightknights.sharedlists.list.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.nnightknights.sharedlists.list.database.ListI;

@Entity(tableName = "lists_user_settings")
public class ListUserSettingsTuple implements ListI {
    @PrimaryKey
    @ForeignKey(entity = ListTuple.class,
            parentColumns = {"listID"},
            childColumns = {"list_id"},
            onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "list_id")
    private int id;

    @ColumnInfo(name = "favorite", index = true)
    private boolean isFavorite;

    @ColumnInfo(name = "pinned", index = true)
    private boolean isPinned;

    @ColumnInfo(name = "new", index = true)
    private boolean isNew;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int listID) {
        this.id = listID;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ListUserSettingsTuple){
            ListUserSettingsTuple listUserSettingsTuple = (ListUserSettingsTuple) object;
            return listUserSettingsTuple.getId() == getId()
                    && listUserSettingsTuple.isFavorite() == isFavorite()
                    && listUserSettingsTuple.isPinned() == isPinned()
                    && listUserSettingsTuple.isNew() == isNew();
        }
        return false;
    }
}
