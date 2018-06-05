package com.nnightknights.sharedlists.list.navigation_drawer;

import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Menu;

import com.nnightknights.sharedlists.R;
import com.nnightknights.sharedlists.list.database.DatabaseActions;
import com.nnightknights.sharedlists.list.database.DatabaseReader;
import com.nnightknights.sharedlists.list.database.entities.ListTitleFavoritePinnedTuple;

public class NavigationMenuBuilder {

    private NavigationView navigationView;
    private DatabaseReader databaseReader;
    private ListTitleFavoritePinnedTuple[] listTitleFavoritePinnedTuples;

    public NavigationMenuBuilder(NavigationView navigationView, DatabaseReader databaseReader){
        this.navigationView = navigationView;
        this.databaseReader = databaseReader;
    }

    public void buildMenu(NavigationDrawerItemSelectedListener navigationDrawerItemSelectedListener){
        navigationView.setNavigationItemSelectedListener(navigationDrawerItemSelectedListener);
        navigationView.inflateMenu(R.menu.navigation_menu);
        Menu menu = navigationView.getMenu();

        getListTitlePinnedFavoriteTuplesFromDatabase();
        addPinnedListsToMenu(menu);
        addFavoriteListsToMenu(menu);
    }

    private void getListTitlePinnedFavoriteTuplesFromDatabase(){
        listTitleFavoritePinnedTuples = null;
        try {
            listTitleFavoritePinnedTuples =
                    (ListTitleFavoritePinnedTuple[]) databaseReader.execute(DatabaseActions.GET_LIST_TITLE_FAVORITE_PINNED_TUPLE, 10).get();
        }catch(Exception e){
            Log.e(DatabaseReader.TAG, e.getMessage());
            System.exit(1);
        }
    }

    private void addPinnedListsToMenu(Menu menu){
        if (hasPinned(listTitleFavoritePinnedTuples)) {
            for (ListTitleFavoritePinnedTuple listTitleFavoritePinnedTuple : listTitleFavoritePinnedTuples) {
                if (listTitleFavoritePinnedTuple.isPinned()) {
                    menu.add(R.id.group_pinned, menu.size() + 1, Menu.NONE, listTitleFavoritePinnedTuple.getTitle());
                }
            }
        }
    }

    private boolean hasPinned(ListTitleFavoritePinnedTuple[] listTitleFavoritePinnedTuples) {
        if (listTitleFavoritePinnedTuples.length > 0){
            for (ListTitleFavoritePinnedTuple listTitleFavoritePinnedTuple : listTitleFavoritePinnedTuples){
                if (listTitleFavoritePinnedTuple.isPinned()){
                    return true;
                }
            }
        }
        return false;
    }

    private void addFavoriteListsToMenu(Menu menu){
        if (hasFavorite(listTitleFavoritePinnedTuples)) {
            for (ListTitleFavoritePinnedTuple listTitleFavoritePinnedTuple : listTitleFavoritePinnedTuples) {
                if (listTitleFavoritePinnedTuple.isFavorite() && !listTitleFavoritePinnedTuple.isPinned()) {
                    menu.add(R.id.group_favorites, menu.size() + 1, Menu.NONE, listTitleFavoritePinnedTuple.getTitle());
                }
            }
        }
    }

    private boolean hasFavorite(ListTitleFavoritePinnedTuple[] listTitleFavoritePinnedTuples) {
        if (listTitleFavoritePinnedTuples.length > 0){
            for (ListTitleFavoritePinnedTuple listTitleFavoritePinnedTuple : listTitleFavoritePinnedTuples){
                if (listTitleFavoritePinnedTuple.isFavorite()){
                    return true;
                }
            }
        }
        return false;
    }
}
