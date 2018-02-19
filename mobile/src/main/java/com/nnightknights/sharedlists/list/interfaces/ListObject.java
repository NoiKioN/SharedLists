package com.nnightknights.sharedlists.list.interfaces;

import java.util.Date;

public interface ListObject {
    int getId();
    String getTitle();
    void setTitle(String title);
    String getDescription();
    void setDescription(String description);
    String getType();
    void setType(String type);
    String getContent();
    void setContent(String content);
    Date getDateCreated();
    Date getDateUpdated();
}
