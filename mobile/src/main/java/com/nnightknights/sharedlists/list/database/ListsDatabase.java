package com.nnightknights.sharedlists.list.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.nnightknights.sharedlists.list.content.database.entities.TextElementToListEntity;
import com.nnightknights.sharedlists.list.content.database.entities.TextListElementEntity;
import com.nnightknights.sharedlists.list.database.DAOs.ListsDAO;
import com.nnightknights.sharedlists.list.database.entities.ListsEntity;

@Database(entities = {ListsEntity.class,
        TextElementToListEntity.class, TextListElementEntity.class},
        version = 1)
public abstract class ListsDatabase extends RoomDatabase{
    public abstract ListsDAO getListsDAO();
}
