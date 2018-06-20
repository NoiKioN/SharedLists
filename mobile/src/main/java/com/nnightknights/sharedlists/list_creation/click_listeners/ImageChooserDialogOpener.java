package com.nnightknights.sharedlists.list_creation.click_listeners;

import android.view.View;
import android.widget.ImageView;

public class ImageChooserDialogOpener implements View.OnClickListener {

    private ImageView coverImageView;
    private ImageView iconImageView;
    private ClickEventHandler clickEventHandler;

    public ImageChooserDialogOpener(ImageView coverImageView, ImageView iconImageView, ClickEventHandler clickEventHandler){
        this.coverImageView = coverImageView;
        this.iconImageView = iconImageView;
        this.clickEventHandler = clickEventHandler;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == coverImageView.getId()){
            clickEventHandler.openCoverImageChooserDialog();
        }
        if (v.getId() == iconImageView.getId()){
            clickEventHandler.openIconImageChooserDialog();
        }
    }

    public interface ClickEventHandler{
        void openCoverImageChooserDialog();
        void openIconImageChooserDialog();
    }
}
