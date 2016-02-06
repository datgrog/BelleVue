package com.bellevue.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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
        setContentView(R.layout.add_new_vue);

        /* Initialise slider */
        sliderShow = (SliderLayout) findViewById(R.id.slider);

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