package com.nnightknights.sharedlists.list.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.nnightknights.sharedlists.list.content.database.entities.TextElementToList;
import com.nnightknights.sharedlists.list.content.database.entities.TextListElement;
import com.nnightknights.sharedlists.list.database.DAOs.ListDAO;
import com.nnightknights.sharedlists.list.database.TypeConverters.ListTypeCoverter;
import com.nnightknights.sharedlists.list.database.entities.List;

@Database(entities = {List.class},
        version = 1)
@TypeConverters(value = {ListTypeCoverter.class})
public abstract class ListsDatabase extends RoomDatabase{
    public abstract ListDAO getListDAO();
}
