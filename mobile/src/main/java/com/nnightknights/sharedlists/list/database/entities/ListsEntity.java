package com.nnightknights.sharedlists.list.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Lists")
public class ListsEntity {
    @PrimaryKey
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

    @ColumnInfo(name = "date_created", index = true)
    private long dateCreated;

    @ColumnInfo(name = "date_updated", index = true)
    private long dateUpdated;

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

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(long dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
