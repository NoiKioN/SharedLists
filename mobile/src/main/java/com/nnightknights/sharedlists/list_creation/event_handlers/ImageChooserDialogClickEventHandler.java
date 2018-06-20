package com.nnightknights.sharedlists.list_creation.event_handlers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.nnightknights.sharedlists.list_creation.CreateList;
import com.nnightknights.sharedlists.list_creation.click_listeners.ImageChooserDialog;

public class ImageChooserDialogClickEventHandler implements ImageChooserDialog.ImageChooserEventHandler {
    private ImageView imageView;
    private ImageView iconImageView, coverImageView;
    private Fragment createListFragment;


    ImageChooserDialogClickEventHandler(ImageView imageView, ImageView iconImageView, ImageView coverImageView, Fragment createListFragment) {
        this.imageView = imageView;
        this.iconImageView = iconImageView;
        this.coverImageView = coverImageView;
        this.createListFragment = createListFragment;
    }

    @Override
    public void chooseImageFromGallery() {
        if (ContextCompat.checkSelfPermission(createListFragment.getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            createListFragment.getActivity().requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (imageView == iconImageView){
            createListFragment.startActivityForResult(galleryIntent, CreateList.SELECTED_ICON);
        }
        else if (imageView == coverImageView){
            createListFragment.startActivityForResult(galleryIntent, CreateList.SELECTED_COVER);
        }
    }

    @Override
    public void chooseIconFromBuiltInGallery() {
        //TODO: Create built in image gallery
    }

    @Override
    public void chooseNone() {
        imageView.setImageDrawable(null);
    }
}
