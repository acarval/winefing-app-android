package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

import fr.dawin.winefing.winefing.Controller;
import fr.dawin.winefing.winefing.Property;
import fr.dawin.winefing.winefing.adapters.PropertyAdapter;
import fr.dawin.winefing.winefing.R;

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
                for (int i = 0; i < jObject.length() - 1; i++) {
                    JSONObject json_data = jObject.getJSONObject(String.valueOf(i));
                    properties.add(new Property(json_data.getInt("id"),
                            "nomDomaine",//json_data.getString("domain['name']"),
                            "nomRegion",//json_data.getString("domain[wine_region][name]"),
                            json_data.getString("media_presentation"),
                            BigDecimal.valueOf(json_data.getDouble("min_price")).floatValue(),
                            BigDecimal.valueOf(json_data.getDouble("max_price")).floatValue(),
                            true,//hasVin(json_data.getString("charachteristic_values[][characteristic][code]")),
                            true,//hasVin(json_data.getString("charachteristic_values[][characteristic][code]")),
                            false,//hasVin(json_data.getString("charachteristic_values[][characteristic][code]")),
                            false,//hasVin(json_data.getString("charachteristic_values[][characteristic][code]")),
                            true/*hasVin(json_data.getString("charachteristic_values[][characteristic][code]"))*/));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            PropertyAdapter adapter = new PropertyAdapter(getActivity(), properties);
            mListView.setAdapter(adapter);
        }
        return myView;
    }

    private boolean hasVin(String typeVin) {
        if(typeVin != null)
            return true;
        else
            return false;
    }
}

