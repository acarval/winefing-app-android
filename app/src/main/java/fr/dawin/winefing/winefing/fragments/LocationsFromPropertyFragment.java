package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.adapters.AndroidImageAdapter;
import fr.dawin.winefing.winefing.classes.Property;
import fr.dawin.winefing.winefing.tools.Controller;

/**
 * Created by vmorreel on 06/02/2017.
 */

public class LocationsFromPropertyFragment extends Fragment {
    View myView;
    Property property;
    private Controller monController;

    private static final String TAG = "LocationsPropertyFrag";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_locations_from_property, container, false);

        final ViewPager mViewPager = (ViewPager) myView.findViewById(R.id.property_slideshow);
        final AndroidImageAdapter adapterView = new AndroidImageAdapter(this.getActivity());

        mViewPager.setAdapter(adapterView);

        // Récupérer la propriété cliquée
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            property = bundle.getParcelable("property");
        }

        // Afficher les infos de cette propriété

        TextView domainName = (TextView)myView.findViewById(R.id.domain_name);
        domainName.setText(property.getDomainName());


        // Afficher la listview des locations de cette propriété



/*
        monController = new Controller();

        String result = monController.getProperties();
        if (result.equals("") || result == null || result.equals("error_server") || isDigitsOnly(result)) {
            Toast.makeText(getActivity(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
        } else {

        }

        */

        // Peuplement du slideshow à partir des images
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < adapterView.getCount(); i++) {
                    final int value = i;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(value, true);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();

        return myView;
    }

}
