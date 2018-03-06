package com.nnightknights.sharedlists.list.database.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nnightknights.sharedlists.list.database.entities.ListsEntity;

@Dao
public interface ListsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLists(ListsEntity... list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(ListsEntity list);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLists(ListsEntity... lists);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateList(ListsEntity list);

    @Delete
    void deleteLists(ListsEntity... lists);

    @Delete
    void deleteList(ListsEntity list);

    @Query()
    ListsEntity[] getAllLists()
}
