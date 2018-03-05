package com.nnightknights.sharedlists.list.content.interfaces;

import java.util.Date;

public interface ListObjectI {

    int getId();

    String getTitle();

    ListObjectI setTitle(String title);

    String getDescription();

    ListObjectI setDescription(String description);

    String getType();

    ListObjectI setType(String type);

    String getContent();

    ListObjectI setContent(String content);

    Date getDateCreated();

    Date getDateUpdated();
}
