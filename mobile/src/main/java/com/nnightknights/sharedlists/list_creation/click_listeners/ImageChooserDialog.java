package com.nnightknights.sharedlists.list_creation.click_listeners;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ImageChooserDialog extends DialogFragment {
    private final String iconGalleryChoose = "Choose from gallery";
    private final String iconIconGalleryChoose = "Choose from built-in icons";
    private final String coverGalleryChoose = "Choose from gallery";
    private final String coverChooseNone = "No cover picture";
    private ClickEventHandler clickEventHandler;
    private ImageChooserEventHandler imageChooserEventHandler;

    public enum ImageType{
        ICON, COVER
    }

    public enum ArgumentNames{
        ImageType
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ImageType imageType = ImageType.valueOf(getArguments().getString(ArgumentNames.ImageType.name()));
        String[] listItems = null;
        if (imageType == ImageType.ICON){
            listItems = new String[] {iconGalleryChoose, iconIconGalleryChoose};
        }
        else if (imageType == ImageType.COVER){
            listItems = new String[] {coverGalleryChoose, coverChooseNone};
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        clickEventHandler = new ClickEventHandler(imageType);
        builder.setItems(listItems, clickEventHandler);
        return builder.create();
    }

    public void setImageChooserEventHandler(ImageChooserEventHandler imageChooserEventHandler) {
        this.imageChooserEventHandler = imageChooserEventHandler;
    }

    private class ClickEventHandler implements DialogInterface.OnClickListener{
        ImageType imageType;

        public ClickEventHandler(ImageType imageType){
            this.imageType = imageType;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (imageType == ImageType.ICON){
                if (which == 0){
                    imageChooserEventHandler.chooseImageFromGallery();
                }
                else if (which == 1){
                    imageChooserEventHandler.chooseIconFromBuiltInGallery();
                }
            }
            if (imageType == ImageType.COVER){
                if (which == 0){
                    imageChooserEventHandler.chooseImageFromGallery();
                }
                else if (which == 1){
                    imageChooserEventHandler.chooseNone();
                }
            }
        }
    }

    public interface ImageChooserEventHandler{
        void chooseImageFromGallery();

        void chooseIconFromBuiltInGallery();

        void chooseNone();
    }
}
