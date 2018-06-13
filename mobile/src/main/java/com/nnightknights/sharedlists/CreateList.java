package com.nnightknights.sharedlists;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcel;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.nnightknights.sharedlists.list.list_creation_form.ImageChooserDialog;
import com.nnightknights.sharedlists.list.list_creation_form.ImageChooserDialogOpener;
import com.nnightknights.sharedlists.list.list_creation_form.UserPreferenceFormOnClickListener;

import java.io.ByteArrayOutputStream;

public class CreateList extends Fragment {
    private AutoCompleteTextView titleAutoCompleteTextView;
    private MultiAutoCompleteTextView descriptionMultiAutoCompleteTextView;
    private ImageView iconImageView;
    private ImageView coverImageView;
    private TextView favoriteTextView;
    private TextView pinnedTextView;
    private Button createButton;

    private String title, description;
    private byte[] icon, coverImage;
    private boolean isPinned = false, isFavorite = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            savedInstanceState.clear();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            titleAutoCompleteTextView.setText(savedInstanceState.getString("title"));
            descriptionMultiAutoCompleteTextView.setText(savedInstanceState.getString("description"));
            iconImageView.setImageDrawable(new BitmapDrawable(getResources(),
                    BitmapFactory.decodeByteArray(savedInstanceState.getByteArray("icon"), 0, savedInstanceState.getByteArray("icon").length)));
            coverImageView.setImageDrawable(new BitmapDrawable(getResources(),
                    BitmapFactory.decodeByteArray(savedInstanceState.getByteArray("cover"), 0, savedInstanceState.getByteArray("cover").length)));
            if (savedInstanceState.getBoolean("favorite")) {
                favoriteTextView.setTextColor(Color.rgb(127, 127, 127));
            }
            if (savedInstanceState.getBoolean("pinned")) {
                pinnedTextView.setTextColor(Color.rgb(127, 127, 127));
            }
        }

        return inflater.inflate(R.layout.fragment_create_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        getViews();

        UserPreferenceFormOnClickListener userPreferenceFormOnClickListener =
                new UserPreferenceFormOnClickListener(favoriteTextView, pinnedTextView, new UserPreferenceFormOnClickListenerClickEventHandler());

        favoriteTextView.setClickable(true);
        favoriteTextView.setOnClickListener(userPreferenceFormOnClickListener);
        pinnedTextView.setClickable(true);
        pinnedTextView.setOnClickListener(userPreferenceFormOnClickListener);

        ImageChooserDialogOpener imageChooserDialogOpener =
                new ImageChooserDialogOpener(coverImageView, iconImageView, new ImageChooserDialogOpenerClickEventHandler());

        coverImageView.setClickable(true);
        coverImageView.setOnClickListener(imageChooserDialogOpener);
        iconImageView.setClickable(true);
        iconImageView.setOnClickListener(imageChooserDialogOpener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getInputValues();
        outState.putString("title", title);
        outState.putString("description", description);
        outState.putByteArray("cover", coverImage);
        outState.putByteArray("icon", icon);
        outState.putBoolean("favorite", isFavorite);
        outState.putBoolean("pinned", isPinned);
    }

    private void getViews(){
        titleAutoCompleteTextView = getActivity().findViewById(R.id.title_edit_text);
        descriptionMultiAutoCompleteTextView = getActivity().findViewById(R.id.description_edit_text);
        coverImageView = getActivity().findViewById(R.id.cover_image_view);
        iconImageView = getActivity().findViewById(R.id.icon_image_view);
        favoriteTextView = getActivity().findViewById(R.id.favorite_text_view);
        pinnedTextView = getActivity().findViewById(R.id.pinned_text_view);
    }

    private void getInputValues(){
        title = titleAutoCompleteTextView.getText().toString();
        description = descriptionMultiAutoCompleteTextView.getText().toString();
        coverImage = convertBitmapDrawableToByteArray(coverImageView.getDrawable());
        icon = convertBitmapDrawableToByteArray(iconImageView.getDrawable());
    }

    private byte[] convertBitmapDrawableToByteArray(Drawable bitmapDrawable){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap image = ((BitmapDrawable) bitmapDrawable).getBitmap();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private class UserPreferenceFormOnClickListenerClickEventHandler implements UserPreferenceFormOnClickListener.ClickEventHandler{
        @Override
        public void toggleFavorite() {
            isFavorite = !isFavorite;
            if (isFavorite) {
                favoriteTextView.setTextColor(Color.rgb(127, 127, 127));
            } else {
                favoriteTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
            }
        }

        @Override
        public void togglePinned() {
            isPinned = !isPinned;
            if (isPinned) {
                pinnedTextView.setTextColor(Color.rgb(127, 127, 127));
            } else {
                pinnedTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
            }
        }
    }

    private class ImageChooserDialogOpenerClickEventHandler implements ImageChooserDialogOpener.ClickEventHandler {
        @Override
        public void openCoverImageChooserDialog() {
            ImageChooserDialog coverImageChooserDialog = new ImageChooserDialog();
            Bundle coverArgs = new Bundle();
            coverArgs.putString(ImageChooserDialog.ArgumentNames.ImageType.name(), ImageChooserDialog.ImageType.COVER.name());
            coverImageChooserDialog.setArguments(coverArgs);
            coverImageChooserDialog.show(getFragmentManager(), "cover_chooser_dialog");
        }

        @Override
        public void openIconImageChooserDialog() {
            ImageChooserDialog iconImageChooserDialog = new ImageChooserDialog();
            Bundle iconArgs = new Bundle();
            iconArgs.putString(ImageChooserDialog.ArgumentNames.ImageType.name(), ImageChooserDialog.ImageType.ICON.name());
            iconImageChooserDialog.setArguments(iconArgs);
            iconImageChooserDialog.show(getFragmentManager(), "icon_chooser_dialog");
        }
    }

    private class ImageChooserDialogClickEventHandler implements ImageChooserDialog.ImageChooserEventHandler {
        

        @Override
        public void chooseImageFromGallery() {

        }

        @Override
        public void chooseIconFromBuiltInGallery() {

        }

        @Override
        public void chooseNone() {

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
}
