package com.nnightknights.sharedlists.list_creation;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.TypiconsIcons;
import com.nnightknights.sharedlists.R;
import com.nnightknights.sharedlists.list_creation.click_listeners.ImageChooserDialogOpener;
import com.nnightknights.sharedlists.list_creation.event_handlers.ImageChooserDialogOpenerClickEventHandler;

import static android.app.Activity.RESULT_OK;

public class CreateList extends DialogFragment {
    public static final String TAG = "create_list_fragment";
    public static final int SELECTED_ICON = 1;
    public static final int SELECTED_COVER = 2;

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String ICON = "icon";
    private static final String COVER = "cover";
    private static final String FAVORITE = "favorite";
    private static final String PINNED = "pinned";

    private AutoCompleteTextView titleAutoCompleteTextView;
    private MultiAutoCompleteTextView descriptionMultiAutoCompleteTextView;
    private ImageView iconImageView;
    private ImageView coverImageView;

    private String title, description;
    private Uri iconPath, coverImagePath;
    private boolean isPinned = false, isFavorite = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getViews();

        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            titleAutoCompleteTextView.setText(savedInstanceState.getString(TITLE));
            descriptionMultiAutoCompleteTextView.setText(savedInstanceState.getString(DESCRIPTION));
            if (savedInstanceState.getParcelable(ICON) != null) {
                Glide.with(this).load(savedInstanceState.getParcelable(ICON)).into(iconImageView);
                iconPath = savedInstanceState.getParcelable(ICON);
            }
            if (savedInstanceState.getParcelable(COVER) != null) {
                Glide.with(this).load(savedInstanceState.getParcelable(COVER)).into(coverImageView);
                coverImagePath = savedInstanceState.getParcelable(COVER);
            }
            isFavorite = savedInstanceState.getBoolean(FAVORITE);
            isPinned = savedInstanceState.getBoolean(PINNED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_create_list, container, false);

        Toolbar createListToolbar = rootView.findViewById(R.id.create_list_toolbar);
        createListToolbar.setTitle("Create List");

        ((AppCompatActivity) getActivity()).setSupportActionBar(createListToolbar);

        ActionBar createListActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (createListActionBar != null){
            createListActionBar.setDisplayHomeAsUpEnabled(true);
            createListActionBar.setHomeButtonEnabled(true);
            createListActionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.create_list_menu, menu);
        if (isFavorite) {
            menu.findItem(R.id.create_list_action_favorite).setIcon(new IconDrawable(getContext(), TypiconsIcons.typcn_star).actionBarSize().colorRes(R.color.colorAccent));
        }
        else {
            menu.findItem(R.id.create_list_action_favorite).setIcon(new IconDrawable(getContext(), TypiconsIcons.typcn_star_outline).actionBarSize().colorRes(R.color.colorAccent));
        }
        if (isPinned) {
            menu.findItem(R.id.create_list_action_pinned).setIcon(new IconDrawable(getContext(), TypiconsIcons.typcn_pin).actionBarSize().colorRes(R.color.colorAccent));
        }
        else {
            menu.findItem(R.id.create_list_action_pinned).setIcon(new IconDrawable(getContext(), TypiconsIcons.typcn_pin_outline).actionBarSize().colorRes(R.color.colorAccent));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.create_list_action_favorite:
                if (!isFavorite) {
                    item.setIcon(new IconDrawable(getContext(), TypiconsIcons.typcn_star).actionBarSize().colorRes(R.color.colorAccent));
                }
                else {
                    item.setIcon(new IconDrawable(getContext(), TypiconsIcons.typcn_star_outline).actionBarSize().colorRes(R.color.colorAccent));
                }
                isFavorite = !isFavorite;
                return true;

            case R.id.create_list_action_pinned:
                if (!isPinned) {
                    item.setIcon(new IconDrawable(getContext(), TypiconsIcons.typcn_pin).actionBarSize().colorRes(R.color.colorAccent));
                }
                else {
                    item.setIcon(new IconDrawable(getContext(), TypiconsIcons.typcn_pin_outline).actionBarSize().colorRes(R.color.colorAccent));
                }
                isPinned = !isPinned;
                return true;

            case R.id.create_list_action_save:
                dismiss();
                return true;

            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                dismiss();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        ImageChooserDialogOpener imageChooserDialogOpener =
                new ImageChooserDialogOpener(coverImageView, iconImageView, new ImageChooserDialogOpenerClickEventHandler(coverImageView, iconImageView, this));

        setClickable(imageChooserDialogOpener, coverImageView, iconImageView);
    }

    private void setClickable(View.OnClickListener onClickListener, View... views){
        for (View view : views){
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getInputValues();
        outState.putString(TITLE, title);
        outState.putString(DESCRIPTION, description);
        outState.putParcelable(COVER, coverImagePath);
        outState.putParcelable(ICON, iconPath);
        outState.putBoolean(FAVORITE, isFavorite);
        outState.putBoolean(PINNED, isPinned);
    }

    private void getViews(){
        titleAutoCompleteTextView = getActivity().findViewById(R.id.title_edit_text);
        descriptionMultiAutoCompleteTextView = getActivity().findViewById(R.id.description_edit_text);
        coverImageView = getActivity().findViewById(R.id.cover_image_view);
        iconImageView = getActivity().findViewById(R.id.icon_image_view);
    }

    private void getInputValues(){
        title = titleAutoCompleteTextView.getText().toString();
        description = descriptionMultiAutoCompleteTextView.getText().toString();
        if (coverImageView.getDrawable() == null){
            coverImagePath = Uri.EMPTY;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case SELECTED_ICON:
                if (resultCode == RESULT_OK){
                    Glide.with(this).load(data.getData()).into(iconImageView);
                    iconPath = data.getData();
                }
                break;
            case SELECTED_COVER:
                if (resultCode == RESULT_OK){
                    Glide.with(this).load(data.getData()).into(coverImageView);
                    coverImagePath = data.getData();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
