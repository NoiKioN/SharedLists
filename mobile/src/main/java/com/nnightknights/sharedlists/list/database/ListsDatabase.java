package com.nnightknights.sharedlists.list.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.nnightknights.sharedlists.list.content.database.entities.TextElementToList;
import com.nnightknights.sharedlists.list.content.database.entities.TextListElement;
import com.nnightknights.sharedlists.list.database.DAOs.ListsDAO;
import com.nnightknights.sharedlists.list.database.entities.List;

@Database(entities = {List.class,
        TextElementToList.class, TextListElement.class},
        version = 1)
public abstract class ListsDatabase extends RoomDatabase{
    public abstract ListsDAO getListsDAO();
}
