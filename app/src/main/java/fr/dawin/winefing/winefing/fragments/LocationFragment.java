package fr.dawin.winefing.winefing.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.classes.Location;

/**
 * Created by vmorreel on 01/02/2017.
 */

public class LocationFragment extends Fragment {

    protected View myView;
    private static final String TAG = "LocationFragment";
    Location location;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_location, container, false);
        Context myContext = getActivity().getApplicationContext();

        // Récupérer la location cliquée
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            location = bundle.getParcelable("location");
        }

        // Peuplement des éléments du fragment à partir de la location passée en paramètres

        TextView tv_roomName = (TextView) myView.findViewById(R.id.room_location_name);
        tv_roomName.setText(location.getNomChambre());

        TextView tv_domainName = (TextView) myView.findViewById(R.id.domain_location_name);
        tv_domainName.setText(location.getNomDomaine());

        ImageView image_location = (ImageView) myView.findViewById(R.id.main_image_location);
        if(location.getUrlImage().equals(""))
            image_location.setImageResource(R.drawable.default_image);
        else
            Picasso.with(myContext).load(location.getUrlImage()).fit().into(image_location);

        TextView tv_nbPeople = (TextView) myView.findViewById(R.id.nb_people_room);
        tv_nbPeople.setText(String.valueOf(location.getNbPersonnes()));

        TextView tv_price = (TextView) myView.findViewById(R.id.price_room);
        tv_price.setText(String.valueOf(location.getPrixChambre()) + getString(R.string.price_per_night));

        TextView tv_description = (TextView) myView.findViewById(R.id.desc_content);
        tv_description.setText(location.getDescription());

        return myView;
    }
}

