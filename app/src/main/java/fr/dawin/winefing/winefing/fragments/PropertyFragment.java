package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.adapters.PropertyAdapter;
import fr.dawin.winefing.winefing.classes.Property;
import fr.dawin.winefing.winefing.utils.Controller;

import static android.text.TextUtils.isDigitsOnly;

/**
 * Created by vmorreel on 01/02/2017.
 */

public class PropertyFragment extends Fragment {

    private ListView mListView;
    protected View myView;
    private Controller monController;

    public static final String IMAGE_PATH = "https://dev.winefing.fr/winefing/web/Resources/pictures/property/";
    private static final String TAG = "PropertyFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_property, container, false);
        mListView = (ListView) myView.findViewById(R.id.properties_list);



        monController = new Controller();

        // Récupération des propriétés
        String result = monController.getProperties();
        if (result.equals("") || result == null || result.equals("error_server") || isDigitsOnly(result)) {
            Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_LONG).show();
        } else {
            ArrayList<Property> properties = new ArrayList<Property>();
            JSONObject jObject = null;
            try {
                jObject = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {

                Iterator<String> iter = jObject.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    JSONObject json_data = (JSONObject) jObject.get(key);

                    int id = json_data.getInt("id");

                    String domain_name;
                    if(json_data.getJSONObject("domain").has("name"))
                        domain_name = json_data.getJSONObject("domain").getString("name");
                    else
                        domain_name = "";

                    String region_name;
                    if(json_data.getJSONObject("domain").getJSONObject("wine_region").has("name"))
                        region_name = json_data.getJSONObject("domain").getJSONObject("wine_region").getString("name");
                    else
                        region_name = "";

                    String url_image;
                    if(json_data.has("media_presentation"))
                        url_image = IMAGE_PATH + json_data.getString("media_presentation");
                    else
                        url_image = "";

                    float min_price;
                    if(json_data.has("min_price"))
                        min_price = BigDecimal.valueOf(json_data.getDouble("min_price")).floatValue();
                    else
                        min_price = 0;

                    float max_price;
                    if(json_data.has("max_price"))
                        max_price = BigDecimal.valueOf(json_data.getDouble("max_price")).floatValue();
                    else
                        max_price = 0;

                    String caracts = "";
                    String name = null;
                    String value = null;
                    JSONArray caractsProperty = json_data.getJSONArray("characteristic_values");
                    for(int i=0; i<=caractsProperty.length()-1; i++){
                        if(!caractsProperty.getJSONObject(i).getString("value").equals("0")){
                            if(caractsProperty.getJSONObject(i).getJSONObject("characteristic").getJSONObject("format").getString("name").equals("BOOLEAN")){
                                 name = caractsProperty.getJSONObject(i).getJSONObject("characteristic").getString("name");
                                caracts += name + "\n";
                            }

                            if(caractsProperty.getJSONObject(i).getJSONObject("characteristic").getJSONObject("format").getString("name").equals("MONNAIE")){
                                 name = caractsProperty.getJSONObject(i).getJSONObject("characteristic").getString("name");
                                 value = caractsProperty.getJSONObject(i).getString("value") + "€";
                                caracts += name + " " + value + "\n";


                            }

                            if(caractsProperty.getJSONObject(i).getJSONObject("characteristic").getJSONObject("format").getString("name").equals("TIME")){
                                 name = caractsProperty.getJSONObject(i).getJSONObject("characteristic").getString("name");
                                 value = caractsProperty.getJSONObject(i).getString("value")+ "h";
                                caracts += name + " " + value + "\n";

                            }
                        }
                    }

                    boolean vinRouge = false, vinBlanc= false, vinRose =false, vinSpiritueux = false;
                    JSONArray caractsVin = json_data.getJSONObject("domain").getJSONArray("characteristic_values");
                    for(int i=0; i<=caractsVin.length()-1; i++) {
                        String wine_characts = caractsVin.getJSONObject(i).getJSONObject("characteristic").getString("code");
                        switch (wine_characts){
                            case "RED_WINE": vinRouge = true;
                            case "WHITE_WINE": vinBlanc = true;
                            case "ROSE_WINE": vinRose = true;
                            case "LIQUEUR_WINE": vinSpiritueux = true;
                        }

                    }

                    // Ajout au tableau de propriétés
                    properties.add(new Property(id, domain_name, region_name, url_image, min_price, max_price, caracts.toString(), vinRouge, vinBlanc, vinRose, vinSpiritueux));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Appel à l'adapter de propriétés pour afficher dynamiquement chacune
            PropertyAdapter adapter = new PropertyAdapter(getActivity(), properties);
            mListView.setAdapter(adapter);


            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Property clickedProperty =(Property) parent.getItemAtPosition(position);

                    LocationsFromPropertyFragment fragLocations = new LocationsFromPropertyFragment();
                    Bundle b = new Bundle();
                    b.putParcelable("property", clickedProperty);
                    fragLocations.setArguments(b);

                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction tx = fragmentManager.beginTransaction();
                    tx.replace(R.id.content_frame, fragLocations, TAG)
                        .addToBackStack(TAG)
                        .commit();

                }
            });
        }
        return myView;
    }
}

