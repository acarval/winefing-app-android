package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.adapters.AndroidImageAdapter;
import fr.dawin.winefing.winefing.classes.Property;

/**
 * Created by vmorreel on 06/02/2017.
 */

public class LocationsFromPropertyFragment extends Fragment {
    View myView;
    Property property;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_locations_from_property, container, false);

        ViewPager mViewPager = (ViewPager) myView.findViewById(R.id.property_slideshow);
        AndroidImageAdapter adapterView = new AndroidImageAdapter(this.getActivity());
        mViewPager.setAdapter(adapterView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            property = bundle.getParcelable("property");
        }
        System.out.println(property.getDomainName());
        return myView;
    }

}
