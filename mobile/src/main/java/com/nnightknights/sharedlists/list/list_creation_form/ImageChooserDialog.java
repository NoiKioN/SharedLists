package com.nnightknights.sharedlists.list.list_creation_form;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class ImageChooserDialog extends DialogFragment{
    private final String iconGalleryChoose = "Choose from gallery";
    private final String iconIconGalleryChoose = "Choose from built-in icons";
    private final String coverGalleryChoose = "Choose from gallery";
    private final String coverChooseNone = "No cover picture";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ImageType imageType = (ImageType) getArguments().get("image_type");
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
        public ClickEventHandler(ImageType imageType){
            this.imageType = imageType;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (imageType == ImageType.ICON){
                if (which == 0){

                }
                else if (which == 1){

                }
            }
            if (imageType == ImageType.COVER){
                if (which == 0){

                }
                else if (which == 1){

                }
            }
        }
    }

    public enum ImageType{
        ICON, COVER
    }
}
