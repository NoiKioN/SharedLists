package com.nnightknights.sharedlists.database.uitility;

import com.nnightknights.sharedlists.list.database.entities.ListTuple;
import com.nnightknights.sharedlists.list.database.entities.ListTitleFavoritePinnedTuple;
import com.nnightknights.sharedlists.list.database.entities.ListUserSettingsTuple;

import java.util.Date;
import java.util.Random;

public class ListTestUtil {
    public ListTuple createGenericTestList(int id, int increment){
        Random random = new Random();
        ListTuple listTuple = new ListTuple();

        ListUserSettingsTuple listUserSettingsTuple = new ListUserSettingsTuple();
        listUserSettingsTuple.setId(id);
        listUserSettingsTuple.setPinned(random.nextBoolean());
        listUserSettingsTuple.setFavorite(random.nextBoolean());
        listUserSettingsTuple.setNew(random.nextBoolean());

        listTuple.setId(id);
        listTuple.setTitle("Test ListTuple " + increment);
        listTuple.setDescription("Test Description " + increment);
        listTuple.setIconPath("Test icon path " + increment);
        listTuple.setCoverPath("Test cover path" + increment);
        listTuple.setTags(String.format("test tag %d, test tag %d", increment, increment));
        listTuple.setListUserSettingsTuple(listUserSettingsTuple);
        listTuple.setDateCreated(new Date(System.currentTimeMillis()));
        listTuple.setDateUpdated(new Date(System.currentTimeMillis()));
        listTuple.setDateOpened(new Date(System.currentTimeMillis()));

        return listTuple;
    }

    public ListTuple[] createGenericTestLists(int startID, int startIncrement, int numberOfLists){
        int index = startID;
        int increment = startIncrement;
        ListTuple[] listTuples = new ListTuple[numberOfLists];

        for (int i = 0; i < numberOfLists; i++){
            listTuples[i] = createGenericTestList(index, increment);
            index++;
            increment++;
        }

        return listTuples;
    }

    public boolean listsEqual(ListTuple[] lists1, ListTuple[] lists2, int startIndex1, int startIndex2){
        for (int i = 0; i < lists1.length - startIndex1; i++){
            if (!lists1[startIndex1 + i].equals(lists2[startIndex2 + i])){
                return false;
            }
        }
        return true;
    }

    public boolean listsPinnedFavoriteTupleEqualsLists(ListTitleFavoritePinnedTuple[] listTitleFavoritePinnedTuple, ListTuple[] listTuple){
        for (int i = 0; i < listTitleFavoritePinnedTuple.length; i++){
            if (!listTitleFavoritePinnedTuple[i].equals(listTuple[i])){
                return false;
            }
        }
        return true;
    }

    public boolean listsNull(ListTuple[] listTuples){
        for (ListTuple listTuple : listTuples){
            if (listTuple != null){
                return false;
            }
        }
        return true;
    }
}
