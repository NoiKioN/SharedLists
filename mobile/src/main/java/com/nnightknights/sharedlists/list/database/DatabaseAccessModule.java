package com.nnightknights.sharedlists.list.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseAccessModule {
    private final ListsDatabase database;

    public DatabaseAccessModule(Context context){
        database = Room.databaseBuilder(context, ListsDatabase.class, "list_db").build();
    }

    @Provides
    @Singleton
    public ListsDatabase provideDatabase(){
        return database;
    }
}
