package com.bellevue.starter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

/**
 * Created by asusss on 9.03.2016.
 */
public class InfoTab extends Fragment {

    int pictureId = 0;
    static SliderLayout sliderShow;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_vue_tab1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        /* Initialise slider */

        sliderShow = (SliderLayout) getActivity().findViewById(R.id.slider);

        if (pictureId == 0) {
            TextSliderView picture1 = new TextSliderView(getActivity().getBaseContext());
            picture1
                    .description("Take some pictures !")
                    .image("https://tedconfblog.files.wordpress.com/2014/12/8photography_tips.png");
            sliderShow.addSlider(picture1);
            sliderShow.stopAutoCycle();
        }



        /* Initialise select categorie */

        Resources res = getResources();
        int flat_blu_app = res.getColor(R.color.pri_dark);
        final int flat_blu = res.getColor(R.color.water_point);
        final int flat_green = res.getColor(R.color.garden_point);
        final int flat_yellow = res.getColor(R.color.landscape);
        final int flat_grey = res.getColor(R.color.place);



        Drawable dr = ContextCompat.getDrawable(getActivity().getBaseContext(), R.drawable.ic_add_location_white_24dp);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();

        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
        Drawable clone = d.getConstantState().newDrawable();
        Drawable clone2 = clone.getConstantState().newDrawable();
        Drawable clone3 = clone2.getConstantState().newDrawable();

    }

}
