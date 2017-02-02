package fr.dawin.winefing.winefing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.text.TextUtils.isDigitsOnly;

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
                    System.out.println(json_data);
                    //properties.add(new Property(json_data.getInt("id"), json_data.getString("nom"), json_data.getInt("nbConcours"), json_data.getString("urlBanniere")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}