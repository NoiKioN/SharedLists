package com.nnightknights.sharedlists.list.interfaces;

import java.util.Date;

public interface List {
    int getId();
    String getTitle();
    void setTitle();
    void setTitle(String name);
    Date getDateCreated();
    Date getDateUpdated();
    ListObject getListObjectAt(int index);
    ListObject[] getListObjectsAt(int startIndex, int endIndex);
    void addListObject(ListObject listObject);
    void addListObjects(ListObject[] listObjects);
    ListObject[] getListObjects();
    void setListObjects(ListObject[] listObjects);
}
