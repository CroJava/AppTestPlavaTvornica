package com.example.buby.apptestplavatvornica.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.buby.apptestplavatvornica.R;

/**
 * Created by Sanja on 2.9.2015..
 */
public class HotelGalleryFragment extends android.support.v4.app.Fragment {
    public ImageView galerija;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hotel_gallery,
                container, false);
        Log.e("TEST","******************UPALJEN FRAGMENT*******************");
        Bundle WebData = getArguments();
        galerija = (ImageView)rootView.findViewById(R.id.galleryImage);
        galerija.setImageResource(WebData.getInt("galerija_image"));

        return rootView;
    }
}