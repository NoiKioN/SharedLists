package com.nnightknights.sharedlists.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.nnightknights.sharedlists.database.uitility.ListTestUtil;
import com.nnightknights.sharedlists.list.database.DAOs.ListsDAO;
import com.nnightknights.sharedlists.list.database.ListsDatabase;
import com.nnightknights.sharedlists.list.database.entities.ListTuple;
import com.nnightknights.sharedlists.list.database.entities.ListExtractionTuple;
import com.nnightknights.sharedlists.list.database.entities.ListTitleFavoritePinnedTuple;

import junit.framework.AssertionFailedError;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ListsDbListTupleTableTest {
    private ListsDAO listsDAO;
    private ListsDatabase testListsDatabase;
    private ListTestUtil testUtil = new ListTestUtil();

    @Before
    public void createTestDatabase() {
        Context context = InstrumentationRegistry.getTargetContext();
        testListsDatabase = Room.inMemoryDatabaseBuilder(context, ListsDatabase.class).build();
        listsDAO = testListsDatabase.getListsDAO();
    }

    @Test
    public void writeList() {
        insertList();
    }

    @Test
    public void writeLists() {
        insertLists(10);
    }

    @Test
    public void updateList(){
        ListTuple listTupleOut = insertList();

        ListTuple listTupleUpdateIn = testUtil.createGenericTestList(1, 2);
        listsDAO.updateList(listTupleUpdateIn);
        ListTuple listTupleUpdateOut = listsDAO.getListByIndex(1);
        assertTrue(listTupleUpdateIn.equals(listTupleUpdateOut));

        assertFalse(listTupleUpdateOut.equals(listTupleOut));
    }

    @Test
    public void updateLists() {
        ListTuple[] listsOut = insertLists(10);

        ListTuple[] listsUpdateIn = testUtil.createGenericTestLists(1, 2, 10);
        listsDAO.updateLists(listsUpdateIn);
        ListTuple[] listsUpdateOut = listsDAO.getAllLists();
        assertTrue(testUtil.listsEqual(listsUpdateIn, listsUpdateOut, 0, 0));

        assertFalse(testUtil.listsEqual(listsUpdateOut, listsOut, 0, 0));
    }

    @Test
    public void deleteList() {
        ListTuple listTupleOut = insertList();

        listsDAO.deleteList(listTupleOut);
        ListTuple listTupleAfterDeletion = listsDAO.getListByIndex(1);
        assertTrue(listTupleAfterDeletion == null);
    }

    @Test
    public void deleteLists() {
        ListTuple[] listsOut = insertLists(10);

        listsDAO.deleteLists(listsOut);
        ListTuple[] listsAfterDeletion = listsDAO.getAllLists();
        assertTrue(testUtil.listsNull(listsAfterDeletion));
    }

    @Test
    public void insertListsAfterIndex(){
        ListTuple[] listsOut = insertLists(10);

        int index = listsDAO.getAllLists().length + 1;
        ListTuple[] listsInsertIn = testUtil.createGenericTestLists(index, 1, 10);
        listsDAO.insertLists(listsInsertIn);
        ListTuple[] listsInsertOut = listsDAO.getAllLists();
        assertTrue(testUtil.listsEqual(listsInsertIn, listsInsertOut, 0, index - 1));

        assertTrue(testUtil.listsEqual(listsOut, listsInsertOut, 0, 0));
    }

    @Test
    public void getListsAfterIndex(){
        ListTuple[] listsIn = insertLists(10);

        int index = 5;
        ListTuple[] listsOut = listsDAO.getListsFromIndex(5, 5);
        assertTrue(testUtil.listsEqual(listsOut, listsIn, 0, index - 1));
    }

    @Test
    public void getListTitlePinnedFavoriteTuple(){
        ListTuple[] listsIn = insertLists(10);

        ListTitleFavoritePinnedTuple[] listTitleFavoritePinnedTuples = listsDAO.getListTitleFavoritePinnedTuple(10);
        assertTrue(testUtil.listsPinnedFavoriteTupleEqualsLists(listTitleFavoritePinnedTuples, listsIn));
    }

    @Test
    public void getListExtractionTuple(){
        ListTuple[] listsIn = insertLists(10);

        ListExtractionTuple listExtractionTuple = listsDAO.getListForExtraction(1);
        assertTrue(listExtractionTuple.equals(listsIn[0]));
    }

    @After
    public void closeTestDatabase() {
        testListsDatabase.close();
    }

    private ListTuple insertList(){
        ListTuple listTupleIn = testUtil.createGenericTestList(1, 1);
        listsDAO.insertList(listTupleIn);
        ListTuple listTupleOut = listsDAO.getListByIndex(1);
        assertTrue(listTupleIn.equals(listTupleOut));
        return listTupleOut;
    }

    private ListTuple[] insertLists(int numberOfLists) throws AssertionFailedError{
        ListTuple[] listsIn = testUtil.createGenericTestLists(1, 1, numberOfLists);
        listsDAO.insertLists(listsIn);
        ListTuple[] listsOut = listsDAO.getAllLists();
        assertTrue(testUtil.listsEqual(listsOut, listsIn, 0, 0));
        return listsOut;
    }
}
