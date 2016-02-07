package com.bellevue.starter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

/**
 * Created by gorkum on 31.01.2016.
 */
public class VueTemplate extends AppCompatActivity {

    static SliderLayout sliderShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vue);

        /* Initialise toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliderShow.stopAutoCycle();
                finish();
            }
        });

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setTypeface(com.bellevue.starter.CustomFontsLoader.getTypeface(this, com.bellevue.starter.CustomFontsLoader.AlexBrush));

        toolbar.inflateMenu(R.menu.menu_add_view);

        /* Initialise slider */
        sliderShow = (SliderLayout) findViewById(R.id.slider);

        TextSliderView picture1 = new TextSliderView(this);
        TextSliderView picture2 = new TextSliderView(this);
        TextSliderView picture3 = new TextSliderView(this);
        picture1
                .description("C'est beau")
                .image("https://scontent-cdg2-1.cdninstagram.com/t51.2885-15/e35/918148_1547918848868274_666499907_n.jpg");
        picture2
                .description("C'est jolie")
                .image("https://scontent-cdg2-1.cdninstagram.com/t51.2885-15/e35/12479464_168519510188114_1077368796_n.jpg");
        picture3
                .description("C'est swag")
                .image("https://scontent-cdg2-1.cdninstagram.com/l/t51.2885-15/e35/12547337_542152145944458_826762616_n.jpg");
        sliderShow.addSlider(picture1);
        sliderShow.addSlider(picture2);
        sliderShow.addSlider(picture3);

        /* Initialise select categorie */
        Resources res = getResources();
        int flat_blu_app    = res.getColor(R.color.pri_dark);
        int flat_blu        = res.getColor(R.color.water_point);
        int flat_green      = res.getColor(R.color.garden_point);
        int flat_yellow     = res.getColor(R.color.landscape);
        int flat_grey       = res.getColor(R.color.place);


        /* Bubble help */
        final TextView categorie_info = (TextView) findViewById(R.id.info);
        Drawable[] bubble = categorie_info.getCompoundDrawables();
        if (bubble[0] != null) {
            bubble[0].setColorFilter(flat_blu_app, PorterDuff.Mode.MULTIPLY);
        }

        /* ImageView Color */
        ImageView cat1 = (ImageView) findViewById(R.id.water_point);
        ImageView cat2 = (ImageView) findViewById(R.id.garden_point);
        ImageView cat3 = (ImageView) findViewById(R.id.landscape);
        ImageView cat4 = (ImageView) findViewById(R.id.place);

        Drawable dr = ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_add_location_white_24dp);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();

        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
        Drawable clone = d.getConstantState().newDrawable();
        Drawable clone2 = clone.getConstantState().newDrawable();
        Drawable clone3 = clone2.getConstantState().newDrawable();

        cat1.setImageDrawable(d);
        cat1.setColorFilter(flat_blu);
        cat2.setImageDrawable(clone);
        cat2.setColorFilter(flat_green);
        cat3.setImageDrawable(clone2);
        cat3.setColorFilter(flat_yellow);
        cat4.setImageDrawable(clone3);
        cat4.setColorFilter(flat_grey);
    }

    @Override
    public void onBackPressed() {
        sliderShow.stopAutoCycle();
        super.onBackPressed();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }
}