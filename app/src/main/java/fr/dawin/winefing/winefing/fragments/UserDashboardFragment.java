package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fr.dawin.winefing.winefing.R;

/**
 * Created by vmorreel on 01/02/2017.
 */

public class UserDashboardFragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_user_dashboard, container, false);
        return myView;
    }
}