package com.nnightknights.sharedlists.list.interfaces;

import com.nnightknights.sharedlists.list.content.interfaces.ListObjectI;

import java.util.Date;

public interface ListI {
    int getId();

    String getTitle();

    ListI setTitle(String title);

    String getDescription();

    ListI setDescription(String description);

    Date getDateCreated();

    Date getDateUpdated();
    
    ListObjectI getListObjectAt(int index);

    ListObjectI[] getListObjectsAt(int startIndex, int endIndex);

    ListI addListObject(ListObjectI listObject);

    ListI addListObjects(ListObjectI[] listObjects);

    ListObjectI[] getListObjects();

    ListI setListObjects(ListObjectI[] listObjects);
}
