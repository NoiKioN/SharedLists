package com.nnightknights.sharedlists.list.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.nnightknights.sharedlists.list.database.DAOs.ListsDAO;
import com.nnightknights.sharedlists.list.database.TypeConverters.ListTypeCoverter;
import com.nnightknights.sharedlists.list.database.entities.ListTuple;
import com.nnightknights.sharedlists.list.database.entities.ListUserSettingsTuple;

@Database(entities = {ListTuple.class, ListUserSettingsTuple.class},
        version = 1, exportSchema = false)
@TypeConverters(value = {ListTypeCoverter.class})
public abstract class ListsDatabase extends RoomDatabase{
    public abstract ListsDAO getListsDAO();
}
