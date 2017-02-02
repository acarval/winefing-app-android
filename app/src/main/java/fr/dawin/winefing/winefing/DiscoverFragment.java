package fr.dawin.winefing.winefing;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmorreel on 01/02/2017.
 */

public class DiscoverFragment extends Fragment {

    ListView mListView;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_discover, container, false);
        mListView = (ListView) myView.findViewById(R.id.listView);

        List<Property> properties = testProperties();

        PropertyAdapter adapter = new PropertyAdapter(getActivity(), properties);
        mListView.setAdapter(adapter);

        return myView;
    }

        private List<Property> testProperties(){
            List<Property> properties = new ArrayList<Property>();
            properties.add(new Property(1, "Château de Carmasac, Le Clos du Prince", "Bordelais", true , true, true, true, true));
            properties.add(new Property(2, "Château Grand Callamand", "Provence", false , true, false, true, false));
            properties.add(new Property(3, "Château Capion", "Languedoc", false , false, false, false, false));
            return properties;
        }
    }