package com.nnightknights.sharedlists.list_creation.event_handlers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.nnightknights.sharedlists.list_creation.click_listeners.ImageChooserDialog;
import com.nnightknights.sharedlists.list_creation.click_listeners.ImageChooserDialogOpener;

public class ImageChooserDialogOpenerClickEventHandler implements ImageChooserDialogOpener.ClickEventHandler {
    private ImageView coverImageView, iconImageView;
    private Fragment createListFragment;

    public ImageChooserDialogOpenerClickEventHandler(ImageView coverImageView, ImageView iconImageView, Fragment createListFragment) {
        this.coverImageView = coverImageView;
        this.iconImageView = iconImageView;
        this.createListFragment = createListFragment;
    }

    @Override
    public void openCoverImageChooserDialog() {
        ImageChooserDialog coverImageChooserDialog = new ImageChooserDialog();
        Bundle coverArgs = new Bundle();
        coverArgs.putString(ImageChooserDialog.ArgumentNames.ImageType.name(), ImageChooserDialog.ImageType.COVER.name());
        coverImageChooserDialog.setArguments(coverArgs);
        coverImageChooserDialog.setImageChooserEventHandler(new ImageChooserDialogClickEventHandler(coverImageView, iconImageView, coverImageView, createListFragment));
        coverImageChooserDialog.show(createListFragment.getFragmentManager(), "cover_chooser_dialog");
    }

    @Override
    public void openIconImageChooserDialog() {
        ImageChooserDialog iconImageChooserDialog = new ImageChooserDialog();
        Bundle iconArgs = new Bundle();
        iconArgs.putString(ImageChooserDialog.ArgumentNames.ImageType.name(), ImageChooserDialog.ImageType.ICON.name());
        iconImageChooserDialog.setArguments(iconArgs);
        iconImageChooserDialog.setImageChooserEventHandler(new ImageChooserDialogClickEventHandler(iconImageView, iconImageView, coverImageView, createListFragment));
        iconImageChooserDialog.show(createListFragment.getFragmentManager(), "icon_chooser_dialog");
    }
}
