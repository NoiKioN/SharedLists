package com.nnightknights.sharedlists.database;

import com.nnightknights.sharedlists.list.database.entities.List;

import java.util.Date;
import java.util.Random;

/**
 * Created by nnigh on 3/7/2018.
 */

class ListTestUtil {
    List createGenericTestList(int id, int increment){
        Random random = new Random();
        List list = new List();

        list.setId(id);
        list.setTitle("Test List " + increment);
        list.setDescription("Test Description " + increment);
        list.setTags(String.format("test tag %d, test tag %d", increment, increment));
        list.setFavorite(random.nextBoolean());
        list.setPinned(random.nextBoolean());
        list.setDateCreated(new Date(System.currentTimeMillis()));
        list.setDateUpdated(new Date(System.currentTimeMillis()));
        list.setDateOpened(new Date(System.currentTimeMillis()));

        return list;
    }

    List[] createGenericTestLists(int startID, int startIncrement, int numberOfLists){
        int index = startID;
        int increment = startIncrement;
        List[] lists = new List[numberOfLists];

        for (int i = 0; i < numberOfLists; i++){
            lists[i] = createGenericTestList(index, increment);
            index++;
            increment++;
        }

        return lists;
    }

    boolean listsEqual(List[] lists1, List[] lists2, int startIndex1, int startIndex2){
        for (int i = 0; i > lists1.length; i++){
            if (!lists1[startIndex1 + i].equals(lists2[startIndex2 + i])){
                return false;
            }
        }
        return true;
    }
}
