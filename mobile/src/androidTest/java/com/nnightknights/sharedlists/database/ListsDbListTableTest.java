package com.nnightknights.sharedlists.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.nnightknights.sharedlists.list.database.DAOs.ListDAO;
import com.nnightknights.sharedlists.list.database.ListsDatabase;
import com.nnightknights.sharedlists.list.database.entities.List;

import junit.framework.AssertionFailedError;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by nnigh on 3/7/2018.
 */

@RunWith(AndroidJUnit4.class)
public class ListsDbListTableTest {
    private ListDAO listDAO;
    private ListsDatabase testListsDatabase;
    private ListTestUtil testUtil = new ListTestUtil();

    @Before
    public void createTestDatabase() {
        Context context = InstrumentationRegistry.getTargetContext();
        testListsDatabase = Room.inMemoryDatabaseBuilder(context, ListsDatabase.class).build();
        listDAO = testListsDatabase.getListDAO();
    }

    @Test
    public void writeList() throws Exception {
        List listIn = testUtil.createGenericTestList(1, 1);
        listDAO.insertList(listIn);
        List listOut = listDAO.getListByIndex(1);
        assertTrue(listIn.equals(listOut));
    }

    @Test
    public void writeLists() throws Exception {
        List[] listsIn = testUtil.createGenericTestLists(1, 1, 10);
        listDAO.insertLists(listsIn);
        List[] listsOut = listDAO.getAllLists();
        assertTrue(testUtil.listsEqual(listsIn, listsOut, 0, 0));
    }

    @Test
    public void updateList() throws Exception {
        List listIn = testUtil.createGenericTestList(1, 1);
        listDAO.insertLists(listIn);
        List listOut = listDAO.getListByIndex(1);
        assertTrue(listIn.equals(listOut));

        List listUpdateIn = testUtil.createGenericTestList(1, 2);
        listDAO.updateList(listUpdateIn);
        List listUpdateOut = listDAO.getListByIndex(1);
        assertTrue(listUpdateIn.equals(listUpdateOut));

        assertTrue(!listUpdateOut.equals(listIn));
    }

    @Test
    public void updateLists() throws Exception {
        List[] listsIn = testUtil.createGenericTestLists(1, 1, 10);
        listDAO.insertLists(listsIn);
        List[] listsOut = listDAO.getAllLists();

    }

    @After
    public void closeTestDatabase() throws IOException {
        testListsDatabase.close();
    }
}
