package fr.dawin.winefing.winefing.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.classes.Property;

/**
 * Created by vmorreel on 02/02/2017.
 */

public class PropertyAdapter extends ArrayAdapter<Property> {

    private static final int NO_WIDTH = 0;
    //public Controller monController;

    public PropertyAdapter(Context context, List<Property> properties) {
        super(context, 0, properties);

        //Pour récupérer l'URL où sont stockées les image depuis le webservice
        /*monController = new Controller();
        image_path = monController.getPropertyImagesPath();
        image_path = image_path.replace("\\","");
        image_path = image_path.substring(1, image_path.length() - 3);
        */
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_property,parent, false);
        }
        PropertyViewHolder viewHolder = (PropertyViewHolder) convertView.getTag();

        if(viewHolder == null){
            viewHolder = new PropertyViewHolder();
            viewHolder.mainImage = (ImageView) convertView.findViewById(R.id.main_image);

            viewHolder.domainName = (TextView) convertView.findViewById(R.id.domain_name);
            viewHolder.regionName = (TextView) convertView.findViewById(R.id.region_name);
            viewHolder.priceLabel = (TextView) convertView.findViewById(R.id.price_label);

            viewHolder.winePic1 = (ImageView) convertView.findViewById(R.id.wine_pic_1);
            viewHolder.winePic2 = (ImageView) convertView.findViewById(R.id.wine_pic_2);
            viewHolder.winePic3 = (ImageView) convertView.findViewById(R.id.wine_pic_3);
            viewHolder.winePic4 = (ImageView) convertView.findViewById(R.id.wine_pic_4);
            viewHolder.winePic5 = (ImageView) convertView.findViewById(R.id.wine_pic_5);
            convertView.setTag(viewHolder);
        }

        Property property = getItem(position);

        //il ne reste plus qu'à remplir notre vue

        viewHolder.domainName.setText(property.getDomainName());
        viewHolder.regionName.setText(property.getRegionName());
        viewHolder.priceLabel.setText("à partir de " + String.valueOf(Math.round(property.getPrixMin())) + "€/nuit");

        Picasso.with(this.getContext()).load(property.getUrlImage()).into(viewHolder.mainImage);

        if (property.getVinRouge())
            viewHolder.winePic1.setImageResource(R.drawable.ic_glassredwine);
        else
            viewHolder.winePic1.setMaxWidth(NO_WIDTH);

        if (property.getVinBlanc())
            viewHolder.winePic2.setImageResource(R.drawable.ic_glasswhitewine);
        else
            viewHolder.winePic2.setMaxWidth(NO_WIDTH);

        if (property.getVinRose())
            viewHolder.winePic3.setImageResource(R.drawable.ic_glassrosewine);
        else
            viewHolder.winePic3.setMaxWidth(NO_WIDTH);

        if (property.getVinSpiritueux())
            viewHolder.winePic4.setImageResource(R.drawable.ic_glasssweetwine);
        else
            viewHolder.winePic4.setMaxWidth(NO_WIDTH);
        if (property.getVinBulles())
            //TODO: Changer par la bonne icône, pb de chargement avec l'ancienne
            viewHolder.winePic5.setImageResource(R.drawable.ic_glasswhitewine);
        else
            viewHolder.winePic5.setMaxWidth(NO_WIDTH);

        return convertView;
    }


    private class PropertyViewHolder {
        public ImageView mainImage;

        public TextView domainName;
        public TextView regionName;
        public TextView priceLabel;

        public ImageView winePic1;
        public ImageView winePic2;
        public ImageView winePic3;
        public ImageView winePic4;
        public ImageView winePic5;
    }

}

