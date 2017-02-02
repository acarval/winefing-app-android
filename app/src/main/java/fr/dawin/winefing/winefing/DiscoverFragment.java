package fr.dawin.winefing.winefing;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


import static android.text.TextUtils.isDigitsOnly;

/**
 * Created by vmorreel on 01/02/2017.
 */

public class DiscoverFragment extends Fragment {

    ListView mListView;
    View myView;
    public Controller monController;


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monController = new Controller();

        String result = monController.getProperties();
        if (result.equals("") || result == null || result.equals("error_server") || isDigitsOnly(result)) {
            Toast.makeText(getActivity(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
        } else {
            ArrayList<Property> properties = new ArrayList<Property>();
            JSONObject jObject = null;
            try {
                jObject = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                for(int i=0;i<jObject.length()-1;i++) {
                    JSONObject json_data = jObject.getJSONObject(String.valueOf(i));
                    //properties.add(new Property(json_data.getInt("id"), json_data.getString("nom"), json_data.getInt("nbConcours"), json_data.getString("urlBanniere")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO: à supprimer, utile que pour les tests
    private List<Property> testProperties(){
        List<Property> properties = new ArrayList<Property>();
        properties.add(new Property(1, "Château de Carmasac, Le Clos du Prince", "Bordelais", true , true, true, true, true));
        properties.add(new Property(2, "Château Grand Callamand", "Provence", false , true, false, true, false));
        properties.add(new Property(3, "Château Capion", "Languedoc", false , false, false, false, false));
        return properties;
    }
}
