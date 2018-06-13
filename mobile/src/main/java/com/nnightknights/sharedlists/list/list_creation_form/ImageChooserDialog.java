package com.nnightknights.sharedlists.list.list_creation_form;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;

public class ImageChooserDialog extends DialogFragment{
    private final String iconGalleryChoose = "Choose from gallery";
    private final String iconIconGalleryChoose = "Choose from built-in icons";
    private final String coverGalleryChoose = "Choose from gallery";
    private final String coverChooseNone = "No cover picture";

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
        builder.setItems(listItems, new ClickEventHandler(imageType));
        return builder.create();
    }

    private class ClickEventHandler implements DialogInterface.OnClickListener{
        ImageType imageType;
        ImageChooserEventHandler imageChooserEventHandler;

        public ClickEventHandler(ImageType imageType, ImageChooserEventHandler imageChooserEventHandler){
            this.imageType = imageType;
            this.imageChooserEventHandler = imageChooserEventHandler;
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

    public interface ImageChooserEventHandler extends Parcelable{
        void chooseImageFromGallery();

        void chooseIconFromBuiltInGallery();

        void chooseNone();
    }
}
