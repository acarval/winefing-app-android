package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.dawin.winefing.winefing.R;

/**
 * Created by vmorreel on 01/02/2017.
 */

public class LocationFragment extends Fragment {

    protected View myView;
    private static final String TAG = "LocationFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_location, container, false);
        return myView;
    }
}

