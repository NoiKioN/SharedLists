package com.nnightknights.sharedlists.list.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "lists")
public class List {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    private int id;

    @ColumnInfo(name = "title", index = true)
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "tags")
    private String tags;

    @ColumnInfo(name = "favorite", index = true)
    private boolean favorite;

    @ColumnInfo(name = "pinned", index = true)
    private boolean pinned;

    @ColumnInfo(name = "date_created", index = true)
    private Date dateCreated;

    @ColumnInfo(name = "date_updated", index = true)
    private Date dateUpdated;

    @ColumnInfo(name = "date_opened", index = true)
    private Date dateOpened;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof List) {
            List list = (List) object;
            return list.getId() == getId()
                    && list.getTitle().equals(getTitle())
                    && list.getDescription().equals(getDescription())
                    && list.getTags().equals(getTags())
                    && list.getDateCreated().equals(getDateCreated())
                    && list.getDateUpdated().equals(getDateUpdated())
                    && list.getDateOpened().equals(getDateOpened());
        }
        return false;
    }
}
