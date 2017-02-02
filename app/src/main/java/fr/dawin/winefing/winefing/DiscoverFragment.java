package fr.dawin.winefing.winefing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vmorreel on 01/02/2017.
 */

public class DiscoverFragment extends Fragment {
    View myView;
    public Controller monController;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_discover, container, false);
        return myView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monController = new Controller();

        String result = monController.getProperties();
        System.out.println(result);
    }
}