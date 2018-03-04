package com.nnightknights.sharedlists.list;

import com.nnightknights.sharedlists.list.content.interfaces.ListObjectI;
import com.nnightknights.sharedlists.list.interfaces.ListI;

import java.util.Date;

public class List implements ListI {
    private int id;
    private String title;
    private String description;
    private Date dateCreated, dateUpdated;
    private ListObjectI[] listObjects;

    public List(int id, String title, String description, Date dateCreated, Date dateUpdated, ListObjectI[] listObjects){
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.listObjects = listObjects;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public ListI setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ListI setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public Date getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public ListObjectI getListObjectAt(int index) {
        return listObjects[index];
    }

    @Override
    public ListObjectI[] getListObjectsAt(int startIndex, int endIndex) {
        ListObjectI[] listObjects = new ListObjectI[endIndex - startIndex];

        System.arraycopy(this.listObjects, startIndex, listObjects, 0, endIndex - startIndex);
        return listObjects;
    }

    @Override
    public ListI addListObject(ListObjectI listObject) {
        ListObjectI[] listObjects = new ListObjectI[this.listObjects.length + 1];

        System.arraycopy(this.listObjects, 0, listObjects, 0, this.listObjects.length);

        listObjects[listObjects.length - 1] = listObject;
        return this;
    }

    @Override
    public ListI addListObjects(ListObjectI[] addedlistObjects) {
        ListObjectI[] listObjects = new ListObjectI[this.listObjects.length + addedlistObjects.length];

        System.arraycopy(this.listObjects, 0, listObjects, 0, this.listObjects.length);

        System.arraycopy(addedlistObjects, 0, listObjects, this.listObjects.length - 1, addedlistObjects.length);
        return null;
    }

    @Override
    public ListObjectI[] getListObjects() {
        return listObjects;
    }

    @Override
    public ListI setListObjects(ListObjectI[] listObjects) {
        this.listObjects = listObjects;
        return this;
    }
}
