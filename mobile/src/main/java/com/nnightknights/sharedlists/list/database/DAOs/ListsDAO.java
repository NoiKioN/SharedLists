package com.nnightknights.sharedlists.list.database.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nnightknights.sharedlists.list.database.entities.List;
import com.nnightknights.sharedlists.list.database.entities.ListTitleFavoriteTuple;
import com.nnightknights.sharedlists.list.database.entities.ListExtractionTuple;

@Dao
public interface ListsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLists(List... list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List list);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLists(List... lists);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateList(List list);

    @Delete
    void deleteLists(List... lists);

    @Delete
    void deleteList(List list);

    @Query(value = "SELECT * FROM lists")
    List[] getAllLists();

    @Query(value = "SELECT * FROM lists WHERE id >= :listID LIMIT :limitValue")
    List[] getListsFromIndex(int listID, int limitValue);

    @Query(value = "SELECT title, favorite FROM lists ORDER BY favorite DESC LIMIT :limitValue")
    ListTitleFavoriteTuple[] getListNamesAndFavorite(int listID, int limitValue);

    @Query(value = "SELECT id, title, description, tags, date_created, date_updated FROM lists WHERE id >= :listID LIMIT :limitValue")
    ListExtractionTuple[] getListsForExtraction(int listID, int limitValue);
}
