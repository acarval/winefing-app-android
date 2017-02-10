package fr.dawin.winefing.winefing.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.classes.Location;

/**
 * Created by vmorreel on 02/02/2017.
 */

public class LocationFromPropertyAdapter extends ArrayAdapter<Location> {

    public LocationFromPropertyAdapter(Context context, List<Location> locations) {
        super(context, 0, locations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_location,parent, false);
        }
        LocationViewHolder viewHolder = (LocationViewHolder) convertView.getTag();

        if(viewHolder == null){
            viewHolder = new LocationViewHolder();
            viewHolder.locationMainImage = (ImageView) convertView.findViewById(R.id.main_image_location);

            viewHolder.locationName = (TextView) convertView.findViewById(R.id.location_name);
            viewHolder.nomDomaineLocation = (TextView) convertView.findViewById(R.id.location_domain_name);
            viewHolder.priceLabel = (TextView) convertView.findViewById(R.id.location_price);
            viewHolder.nbPeopleLocation = (TextView) convertView.findViewById(R.id.nb_people_location);

            convertView.setTag(viewHolder);
        }


        Location location = getItem(position);

        //Picasso.with(this.getContext()).load(location.getUrlImage()).fit().into(viewHolder.locationMainImage);
        viewHolder.locationName.setText(location.getNomChambre());
        viewHolder.nomDomaineLocation.setText(location.getNomDomaine());
        viewHolder.nbPeopleLocation.setText(String.valueOf(location.getNbPersonnes()));
        viewHolder.priceLabel.setText(String.valueOf(location.getPrixChambre()) + "€/nuit");

        return convertView;
    }


    private class LocationViewHolder {
        public ImageView locationMainImage;
        public TextView priceLabel;
        public TextView locationName;
        public TextView nbPeopleLocation;
        public TextView nomDomaineLocation;
    }

}

