package com.nnightknights.sharedlists.view_lists;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.nnightknights.sharedlists.R;
import com.nnightknights.sharedlists.floating_action_button.FloatingActionButtonCreateListOnClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewLists extends Fragment {
    public static final String TAG = "view_lists";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        instantiateFloatingActionButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_lists, container, false);
    }

    private void instantiateFloatingActionButton(){
        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab);
        floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccent, getActivity().getTheme()));
        floatingActionButton.setImageDrawable(new IconDrawable(getActivity().getApplicationContext(), FontAwesomeIcons.fa_plus).colorRes(R.color.colorPrimary));
        floatingActionButton.setOnClickListener(new FloatingActionButtonCreateListOnClickListener(floatingActionButton, (AppCompatActivity) getActivity()));
    }
}
