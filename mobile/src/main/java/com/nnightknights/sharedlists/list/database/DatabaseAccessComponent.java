package com.nnightknights.sharedlists.list.database;

import javax.inject.Singleton;

import dagger.Component;
@Component(modules = {DatabaseAccessModule.class})
@Singleton
public interface DatabaseAccessComponent{
    ListsDatabase provideDatabase();

    void inject(DatabaseReader databaseReader);
}
