package com.nnightknights.sharedlists.floating_action_button;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nnightknights.sharedlists.list_creation.CreateList;
import com.nnightknights.sharedlists.R;
import com.nnightknights.sharedlists.view_lists.ViewLists;

public class FloatingActionButtonCreateListOnClickListener implements View.OnClickListener {
    FloatingActionButton floatingActionButton;
    AppCompatActivity activity;

    public FloatingActionButtonCreateListOnClickListener(FloatingActionButton floatingActionButton, AppCompatActivity activity){
        this.floatingActionButton = floatingActionButton;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        if (v == floatingActionButton){
            Fragment createListFragment = new CreateList();
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.remove(activity.getSupportFragmentManager().findFragmentByTag(ViewLists.TAG));
            transaction.replace(R.id.drawer_layout, createListFragment, CreateList.TAG);
            transaction.addToBackStack(CreateList.TAG).commit();
        }
    }
}