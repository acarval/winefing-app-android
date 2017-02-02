package fr.dawin.winefing.winefing;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vmorreel on 02/02/2017.
 */

public class PropertyAdapter extends ArrayAdapter<fr.dawin.winefing.winefing.Property> {

    private static final int NO_WIDTH = 0;

    //tweets est la liste des models à afficher
    public PropertyAdapter(Context context, List<fr.dawin.winefing.winefing.Property> properties) {
        super(context, 0, properties);
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

            viewHolder.winePic1 = (ImageView) convertView.findViewById(R.id.wine_pic_1);
            viewHolder.winePic2 = (ImageView) convertView.findViewById(R.id.wine_pic_2);
            viewHolder.winePic3 = (ImageView) convertView.findViewById(R.id.wine_pic_3);
            viewHolder.winePic4 = (ImageView) convertView.findViewById(R.id.wine_pic_4);
            viewHolder.winePic5 = (ImageView) convertView.findViewById(R.id.wine_pic_5);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        fr.dawin.winefing.winefing.Property property = getItem(position);

        //il ne reste plus qu'à remplir notre vue

        viewHolder.domainName.setText(property.getDomainName());
        viewHolder.regionName.setText(property.getRegionName());

        //TODO:Gérer l'image principale du chateau
        //viewHolder.mainImage.setImageDrawable(new ColorDrawable(property.getColor()));

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

        public ImageView winePic1;
        public ImageView winePic2;
        public ImageView winePic3;
        public ImageView winePic4;
        public ImageView winePic5;
    }

}

