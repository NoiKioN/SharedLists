package com.nnightknights.sharedlists.list.database;

import com.nnightknights.sharedlists.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
@Component(modules = {DatabaseAccessModule.class})
@Singleton
public interface DatabaseAccessComponent{
    ListsDatabase provideDatabase();

    void inject(DatabaseReader databaseReader);
}
