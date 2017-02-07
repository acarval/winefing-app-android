package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.adapters.AndroidImageAdapter;

/**
 * Created by vmorreel on 06/02/2017.
 */

public class LocationsFromPropertyFragment extends Fragment {
    View myView;

    private static final String TAG = "LocationsPropertyFrag";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_locations_from_property, container, false);

        final ViewPager mViewPager = (ViewPager) myView.findViewById(R.id.property_slideshow);
        final AndroidImageAdapter adapterView = new AndroidImageAdapter(this.getActivity());



        mViewPager.setAdapter(adapterView);


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
