package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.adapters.LocationFromPropertyAdapter;
import fr.dawin.winefing.winefing.classes.Location;
import fr.dawin.winefing.winefing.classes.Property;
import fr.dawin.winefing.winefing.utils.Controller;

import static android.text.TextUtils.isDigitsOnly;

/**
 * Created by vmorreel on 06/02/2017.
 */

public class LocationsFromPropertyFragment extends Fragment {
    View myView;
    private ListView mListView;
    Property property;
    private Controller monController;

    public static final String IMAGE_PATH = "https://dev.winefing.fr/winefing/web/Resources/pictures/rental/";

    private static final String TAG = "LocationsPropertyFrag";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_locations_from_property, container, false);
        mListView = (ListView) myView.findViewById(R.id.locations_list);


        // Récupérer la propriété cliquée
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            property = bundle.getParcelable("property");
        }

        // Afficher les infos de cette propriété
        TextView domainName = (TextView) myView.findViewById(R.id.domain_name);
        domainName.setText(property.getDomainName());


        Context mContext = getActivity().getApplicationContext();
        ImageView property_main_image = (ImageView) myView.findViewById(R.id.property_main_image);
        Picasso.with(mContext).load(property.getUrlImage()).fit().into(property_main_image);


        // Afficher la listview des locations de cette propriété
        monController = new Controller();
        String result = monController.getLocationsFromProperty(property.getId());
        if (result.equals("") || result == null || result.equals("error_server") || isDigitsOnly(result)) {
            Toast.makeText(getActivity(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
        } else {
            ArrayList<Location> locations = new ArrayList<Location>();
            JSONArray jArray = null;
            try {
                jArray = new JSONArray(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {

                for(int i=0; i<=jArray.length()-1; i++){
                    JSONObject json_data = jArray.getJSONObject(i);

                    int id = json_data.getInt("id");

                    float prixChambre;
                    if (json_data.has("price"))
                        prixChambre = BigDecimal.valueOf(json_data.getDouble("price")).floatValue();
                    else
                        prixChambre = 0;

                    String nomChambre;
                    if (json_data.has("name"))
                        nomChambre = json_data.getString("name");
                    else
                        nomChambre = "";

                    int nbPersonne;
                    if (json_data.has("people_number"))
                        nbPersonne = json_data.getInt("people_number");
                    else
                        nbPersonne = 0;

                    String url_image;
                    if (json_data.has("media_presentation"))
                        url_image = IMAGE_PATH + json_data.getString("media_presentation");
                    else
                        url_image = "";

                    String nomDomaine = property.getDomainName();


                    locations.add(new Location(id, prixChambre, nomChambre, url_image, nbPersonne, nomDomaine));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LocationFromPropertyAdapter adapter = new LocationFromPropertyAdapter(getActivity(), locations);
            mListView.setAdapter(adapter);


            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Location clickedLocation = (Location) parent.getItemAtPosition(position);

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

        }
        return myView;
    }
}