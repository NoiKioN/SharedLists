package com.nnightknights.sharedlists.list.database.entities;

import android.arch.persistence.room.ColumnInfo;

import com.nnightknights.sharedlists.list.database.ListI;

public class ListTitleFavoritePinnedTuple implements ListI {
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "icon")
    private String iconPath;

    @ColumnInfo(name = "cover")
    private String coverPath;

    @ColumnInfo(name = "favorite")
    private boolean favorite;

    @ColumnInfo(name = "pinned")
    private boolean pinned;

    @Override
    public int getId(){
        return id;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
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

    @Override
    public boolean equals(Object object){
        if (object instanceof ListTuple){
            ListTuple listTuple = (ListTuple) object;

            return listTuple.getId() == getId()
                        && listTuple.getTitle().equals(title)
                        && listTuple.getIconPath().equals(getIconPath())
                        && listTuple.getCoverPath().equals(getCoverPath())
                        && listTuple.getListUserSettingsTuple().isPinned() == pinned
                        && listTuple.getListUserSettingsTuple().isFavorite() == favorite;
        }
        return false;
    }
}
