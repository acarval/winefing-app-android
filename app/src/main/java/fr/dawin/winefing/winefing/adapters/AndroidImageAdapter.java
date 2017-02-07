package fr.dawin.winefing.winefing.adapters;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.fragments.LocationsFromPropertyFragment;

/**
 * Created by vmorreel on 06/02/2017.
 */

public class AndroidImageAdapter extends PagerAdapter {
    Context mContext;

    public AndroidImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    private int[] sliderImagesId = new int[]{
            R.drawable.camarsac, R.drawable.mesreservations_background, R.drawable.decouvrir_background,
            R.drawable.camarsac, R.drawable.mesreservations_background, R.drawable.decouvrir_background
    };

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(sliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}
