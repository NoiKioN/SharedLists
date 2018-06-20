package com.nnightknights.sharedlists.list.database.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nnightknights.sharedlists.list.database.entities.ListTuple;
import com.nnightknights.sharedlists.list.database.entities.ListTitleFavoritePinnedTuple;
import com.nnightknights.sharedlists.list.database.entities.ListExtractionTuple;

@Dao
public interface ListsDAO {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertLists(ListTuple... listTuple);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertList(ListTuple listTuple);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLists(ListTuple... listTuples);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateList(ListTuple listTuple);

    @Delete
    void deleteLists(ListTuple... listTuples);

    @Delete
    void deleteList(ListTuple listTuple);

    @Query(value = "SELECT * FROM lists WHERE id = :listId")
    ListTuple getListByIndex(int listId);

    @Query(value = "SELECT * FROM lists")
    ListTuple[] getAllLists();

    @Query(value = "SELECT * FROM lists WHERE id >= :listID LIMIT :limitValue")
    ListTuple[] getListsFromIndex(int listID, int limitValue);

    @Query(value = "SELECT lists.id, lists.title, lists.icon, lists.cover, lists_user_settings.favorite, lists_user_settings.pinned " +
            "FROM lists, lists_user_settings " +
            "ORDER BY lists_user_settings.pinned DESC," +
                " lists_user_settings.favorite DESC " +
            "LIMIT :limitValue")
    ListTitleFavoritePinnedTuple[] getListTitleFavoritePinnedTuple(int limitValue);

    @Query(value = "SELECT id, title, description, icon, cover, tags, date_created, date_updated FROM lists WHERE id = :id")
    ListExtractionTuple getListForExtraction(int id);
}
