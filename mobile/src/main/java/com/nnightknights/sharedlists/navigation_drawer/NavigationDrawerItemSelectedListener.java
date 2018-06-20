package com.nnightknights.sharedlists.navigation_drawer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.nnightknights.sharedlists.R;

public class NavigationDrawerItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {

    private Activity activity;

    public NavigationDrawerItemSelectedListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
