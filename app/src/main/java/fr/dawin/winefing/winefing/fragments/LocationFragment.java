package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.adapters.PropertyAdapter;
import fr.dawin.winefing.winefing.classes.Property;
import fr.dawin.winefing.winefing.tools.Controller;

import static android.text.TextUtils.isDigitsOnly;

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

