package com.nnightknights.sharedlists.list_creation.event_handlers;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.TextView;

import com.nnightknights.sharedlists.R;
import com.nnightknights.sharedlists.list_creation.click_listeners.UserPreferenceFormOnClickListener;

public class UserPreferenceFormOnClickListenerClickEventHandler implements UserPreferenceFormOnClickListener.ClickEventHandler {

    boolean isFavorite, isPinned;
    TextView favoriteTextView, pinnedTextView;
    Fragment createListFragment;

    public UserPreferenceFormOnClickListenerClickEventHandler(boolean isFavorite, boolean isPinned, TextView favoriteTextView, TextView pinnedTextView, Fragment createListFragment){
        this.isFavorite = isFavorite;
        this.isPinned = isPinned;
        this.favoriteTextView = favoriteTextView;
        this.pinnedTextView = pinnedTextView;
        this.createListFragment = createListFragment;
    }

    @Override
    public void toggleFavorite() {
        isFavorite = !isFavorite;
        if (isFavorite) {
            favoriteTextView.setTextColor(Color.rgb(127, 127, 127));
        } else {
            favoriteTextView.setTextColor(ResourcesCompat.getColor(createListFragment.getResources(), R.color.colorPrimary, null));
        }
    }

    @Override
    public void togglePinned() {
        isPinned = !isPinned;
        if (isPinned) {
            pinnedTextView.setTextColor(Color.rgb(127, 127, 127));
        } else {
            pinnedTextView.setTextColor(ResourcesCompat.getColor(createListFragment.getResources(), R.color.colorPrimary, null));
        }
    }
}
