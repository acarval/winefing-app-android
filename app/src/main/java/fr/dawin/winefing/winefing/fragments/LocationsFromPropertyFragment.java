package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.adapters.AndroidImageAdapter;
import fr.dawin.winefing.winefing.adapters.LocationFromPropertyAdapter;
import fr.dawin.winefing.winefing.adapters.PropertyAdapter;
import fr.dawin.winefing.winefing.classes.Location;
import fr.dawin.winefing.winefing.classes.Property;
import fr.dawin.winefing.winefing.tools.Controller;

import static android.text.TextUtils.isDigitsOnly;

/**
 * Created by vmorreel on 06/02/2017.
 */

public class LocationsFromPropertyFragment extends Fragment {
    View myView;
    private ListView mListView;
    Property property;
    private Controller monController;

    private static final String TAG = "LocationsPropertyFrag";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_locations_from_property, container, false);
        mListView = (ListView) myView.findViewById(R.id.locations_list);


        final ViewPager mViewPager = (ViewPager) myView.findViewById(R.id.property_slideshow);
        final AndroidImageAdapter adapterView = new AndroidImageAdapter(this.getActivity());

        mViewPager.setAdapter(adapterView);

        // Récupérer la propriété cliquée
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            property = bundle.getParcelable("property");
        }

        // Afficher les infos de cette propriété


        // Afficher la listview des locations de cette propriété


        ArrayList<Location> locations = new ArrayList<Location>();

        LocationFromPropertyAdapter adapter = new LocationFromPropertyAdapter(getActivity(), locations);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "id : " + id);
                Log.e(TAG, "View : " + view);
                Log.e(TAG, "Position : " + position);
                Log.e(TAG, "Parent : " + parent);

                Location clickedLocation =(Location) parent.getItemAtPosition(position);

                LocationFragment fragLocation = new LocationFragment();
                Bundle b = new Bundle();
                b.putParcelable("location", clickedLocation);
                fragLocation.setArguments(b);

                android.app.FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction tx = fragmentManager.beginTransaction();
                tx.replace(R.id.content_frame, fragLocation, TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        

        monController = new Controller();

        String result = monController.getProperties();
        if (result.equals("") || result == null || result.equals("error_server") || isDigitsOnly(result)) {
            Toast.makeText(getActivity(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
        } else {

            // TODO utiliser cette location pour afficher infos

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
        }

        return myView;
    }

}
